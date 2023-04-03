package com.example.weatherforecast.feign;

import com.example.weatherforecast.dto.ApiResponse;
import com.example.weatherforecast.dto.UltraShortForecastDto;
import com.example.weatherforecast.dto.UltraShortNowcastDto;
import com.example.weatherforecast.dto.VillageForecastDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureWireMock(port = 0)
class VillageForecastInfoClientTest {
    @Autowired
    private VillageForecastInfoClient client;

    @Test
    void getUltraShortNowcastNormalService() {
        ApiResponse<UltraShortNowcastDto> response = client.getUltraShortNowcast(1, 10, "20230407", "1000", 60, 127);
        assertEquals(ApiResponse.Result.NORMAL_SERVICE, response.result);
    }

    @Test
    void getUltraShortNowcastNoData() {
        ApiResponse<UltraShortNowcastDto> response = client.getUltraShortNowcast(1, 10, "20230407", "1000", 55, 127);
        assertEquals(ApiResponse.Result.NODATA_ERROR, response.result);
    }

    @Test
    void getUltraShortForecastNormalService() {
        ApiResponse<UltraShortForecastDto> response = client.getUltraShortForecast(1, 10, "20230407", "1030", 60, 127);
        assertEquals(ApiResponse.Result.NORMAL_SERVICE, response.result);
    }

    @Test
    void getUltraShortForecastNoData() {
        ApiResponse<UltraShortForecastDto> response = client.getUltraShortForecast(1, 10, "20230407", "1030", 55, 127);
        assertEquals(ApiResponse.Result.NODATA_ERROR, response.result);
    }

    @Test
    void getVillageForecastNormalService() {
        ApiResponse<VillageForecastDto> response = client.getVillageForecast(1, 10, "20230407", "0800", 60, 127);
        assertEquals(ApiResponse.Result.NORMAL_SERVICE, response.result);
    }

    @Test
    void getVillageForecastNoData() {
        ApiResponse<VillageForecastDto> response = client.getVillageForecast(1, 10, "20230407", "0800", 55, 127);
        assertEquals(ApiResponse.Result.NODATA_ERROR, response.result);
    }
}