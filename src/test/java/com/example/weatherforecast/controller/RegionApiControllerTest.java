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
class RegionApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetSecondLevels() throws Exception {
        mockMvc.perform(get("/api/region/getSecondLevels").queryParam("firstLevel", "서울특별시"))
                .andExpect(status().isOk())
                .andDo(document("region-api-get-second-levels",
                        requestParameters(parameterWithName("firstLevel").description("1단계 행정구역")),
                        responseFields(fieldWithPath("[]").description("2단계 행정구역"))));
    }

    @Test
    void testGetThirdLevels() throws Exception {
        mockMvc.perform(get("/api/region/getThirdLevels")
                        .queryParam("firstLevel", "서울특별시")
                        .queryParam("secondLevel", "종로구"))
                .andExpect(status().isOk())
                .andDo(document("region-api-get-third-levels",
                        requestParameters(parameterWithName("firstLevel").description("1단계 행정구역"),
                                parameterWithName("secondLevel").description("2단계 행정구역")),
                        responseFields(fieldWithPath("[]").description("3단계 행정구역"))));
    }

    @Test
    void testGetMidTermRegions() throws Exception {
        mockMvc.perform(get("/api/region/getMidTermRegions").queryParam("type", "forecast"))
                .andExpect(status().isOk())
                .andDo(document("region-api-get-mid-term-regions",
                        requestParameters(parameterWithName("type").description("종류 (forecast, land, sea, temperature)")),
                        responseFields(fieldWithPath("[]").description("구역"))));
    }
}