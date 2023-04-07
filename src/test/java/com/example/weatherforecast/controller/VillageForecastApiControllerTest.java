package com.example.weatherforecast.controller;

import com.example.weatherforecast.TestSetup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestSetup
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class VillageForecastApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getUltraShortNowcast() throws Exception {
        mockMvc.perform(get("/api/villageForecast/ultraShortNowcast")
                        .queryParam("firstLevel", "서울특별시")
                        .queryParam("secondLevel", "종로구")
                        .queryParam("thirdLevel", "청운효자동"))
                .andExpect(status().isOk())
                .andDo(document("village-forecast-api-ultra-short-nowcast",
                        requestParameters(parameterWithName("firstLevel").description("1단계 행정구역"),
                                parameterWithName("secondLevel").description("2단계 행정구역").optional(),
                                parameterWithName("thirdLevel").description("3단계 행정구역").optional()),
                        responseFields(fieldWithPath("[]").description("초단기실황"),
                                fieldWithPath("[].baseDate").description("발표일자"),
                                fieldWithPath("[].baseTime").description("발표시간"),
                                fieldWithPath("[].nx").description("예보지점 X 좌표"),
                                fieldWithPath("[].ny").description("예보지점 Y 좌표"),
                                fieldWithPath("[].category").description("자료구분코드"),
                                fieldWithPath("[].obsrValue").description("실황 값"))));
    }

    @Test
    void getUltraShortForecast() throws Exception {
        mockMvc.perform(get("/api/villageForecast/ultraShortForecast")
                        .queryParam("firstLevel", "서울특별시")
                        .queryParam("secondLevel", "종로구")
                        .queryParam("thirdLevel", "청운효자동"))
                .andExpect(status().isOk())
                .andDo(document("village-forecast-api-ultra-short-forecast",
                        requestParameters(parameterWithName("firstLevel").description("1단계 행정구역"),
                                parameterWithName("secondLevel").description("2단계 행정구역").optional(),
                                parameterWithName("thirdLevel").description("3단계 행정구역").optional()),
                        responseFields(fieldWithPath("[]").description("초단기예보"),
                                fieldWithPath("[].baseDate").description("발표일자"),
                                fieldWithPath("[].baseTime").description("발표시간"),
                                fieldWithPath("[].nx").description("예보지점 X 좌표"),
                                fieldWithPath("[].ny").description("예보지점 Y 좌표"),
                                fieldWithPath("[].category").description("자료구분코드"),
                                fieldWithPath("[].fcstDate").description("예측일자"),
                                fieldWithPath("[].fcstTime").description("예측시간"),
                                fieldWithPath("[].fcstValue").description("예보 값"))));
    }

    @Test
    void getVillageForecast() throws Exception {
        mockMvc.perform(get("/api/villageForecast/villageForecast")
                        .queryParam("firstLevel", "서울특별시")
                        .queryParam("secondLevel", "종로구")
                        .queryParam("thirdLevel", "청운효자동"))
                .andExpect(status().isOk())
                .andDo(document("village-forecast-api-village-forecast",
                        requestParameters(parameterWithName("firstLevel").description("1단계 행정구역"),
                                parameterWithName("secondLevel").description("2단계 행정구역").optional(),
                                parameterWithName("thirdLevel").description("3단계 행정구역").optional()),
                        responseFields(fieldWithPath("[]").description("초단기예보"),
                                fieldWithPath("[].baseDate").description("발표일자"),
                                fieldWithPath("[].baseTime").description("발표시간"),
                                fieldWithPath("[].nx").description("예보지점 X 좌표"),
                                fieldWithPath("[].ny").description("예보지점 Y 좌표"),
                                fieldWithPath("[].category").description("자료구분코드"),
                                fieldWithPath("[].fcstDate").description("예측일자"),
                                fieldWithPath("[].fcstTime").description("예측시간"),
                                fieldWithPath("[].fcstValue").description("예보 값"))));
    }
}