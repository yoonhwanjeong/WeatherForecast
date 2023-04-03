package com.example.weatherforecast.dto;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@JsonSerialize(using = ApiResponse.Serializer.class)
@JsonDeserialize(using = ApiResponse.Deserializer.class)
public class ApiResponse<T> {
    public final Result result;
    public final String resultMsg;
    public final List<T> items;
    public final int pageNo;
    public final int numOfRows;
    public final int totalCount;

    public ApiResponse(Result result, String resultMsg, List<T> items, int pageNo, int numOfRows, int totalCount) {
        this.result = result;
        this.resultMsg = resultMsg;
        this.items = items;
        this.pageNo = pageNo;
        this.numOfRows = numOfRows;
        this.totalCount = totalCount;
    }

    public enum Result {
        NORMAL_SERVICE("00"),
        APPLICATION_ERROR("01"),
        DB_ERROR("02"),
        NODATA_ERROR("03"),
        HTTP_ERROR("04"),
        SERVICETIME_OUT("05"),
        INVALID_REQUEST_PARAMETER_ERROR("10"),
        NO_MANDATORY_REQUEST_PARAMETERS_ERROR("11"),
        NO_OPENAPI_SERVICE_ERROR("12"),
        SERVICE_ACCESS_DENIED_ERROR("20"),
        TEMPORARILY_DISABLE_THE_SERVICEKEY_ERROR("21"),
        LIMITED_NUMBER_OF_SERVICE_REQUESTS_EXCEEDS_ERROR("22"),
        SERVICE_KEY_IS_NOT_REGISTERED_ERROR("30"),
        DEADLINE_HAS_EXPIRED_ERROR("31"),
        UNREGISTERED_IP_ERROR("32"),
        UNSIGNED_CALL_ERROR("33"),
        UNKNOWN_ERROR("99");

        public final String code;

        Result(String code) {
            this.code = code;
        }

        static Result byCode(String code) {
            for (Result result : Result.values()) {
                if (result.code.equals(code)) {
                    return result;
                }
            }
            return null;
        }
    }

    static class Serializer extends JsonSerializer<ApiResponse<?>> {
        @Override
        public void serialize(ApiResponse<?> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeStartObject();
            gen.writeObjectFieldStart("response");
            gen.writeObjectFieldStart("header");
            gen.writeStringField("resultCode", value.result.code);
            gen.writeStringField("resultMsg", value.resultMsg);
            gen.writeEndObject();
            if (!value.items.isEmpty()) {
                gen.writeObjectFieldStart("body");
                gen.writeStringField("dataType", "JSON");
                gen.writeObjectFieldStart("items");
                gen.writeArrayFieldStart("item");
                for (Object item : value.items) {
                    gen.writeObject(item);
                }
                gen.writeEndArray();
                gen.writeEndObject();
                gen.writeNumberField("pageNo", value.pageNo);
                gen.writeNumberField("numOfRows", value.numOfRows);
                gen.writeNumberField("totalCount", value.totalCount);
                gen.writeEndObject();
            }
            gen.writeEndObject();
            gen.writeEndObject();
        }
    }

    static class Deserializer extends JsonDeserializer<ApiResponse<?>> implements ContextualDeserializer {
        private JavaType type;

        @Override
        public ApiResponse<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            ObjectCodec codec = p.getCodec();
            JsonNode response = codec.<JsonNode>readTree(p).get("response");
            JsonNode header = response.get("header");
            Result result = Result.byCode(header.get("resultCode").textValue());
            String resultMsg = header.get("resultMsg").textValue();
            JsonNode body = response.get("body");
            if (body != null) {
                List<?> items = codec.readValue(codec.treeAsTokens(body.get("items").get("item")), type);
                int pageNo = body.get("pageNo").intValue();
                int numOfRows = body.get("numOfRows").intValue();
                int totalCount = body.get("totalCount").intValue();
                return new ApiResponse<>(result, resultMsg, items, pageNo, numOfRows, totalCount);
            } else {
                return new ApiResponse<>(result, resultMsg, Collections.emptyList(), 0, 0, 0);
            }
        }

        @Override
        public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) {
            type = TypeFactory.defaultInstance().constructCollectionType(List.class, ctxt.getContextualType().containedType(0));
            return this;
        }
    }
}
