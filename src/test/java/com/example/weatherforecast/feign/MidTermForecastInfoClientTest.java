package com.example.weatherforecast.feign;

import com.example.weatherforecast.dto.ApiResponse;
import com.example.weatherforecast.dto.MidTermForecastDto;
import com.example.weatherforecast.dto.MidTermLandForecastDto;
import com.example.weatherforecast.dto.MidTermSeaForecastDto;
import com.example.weatherforecast.dto.MidTermTemperatureDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureWireMock(port = 0)
class MidTermForecastInfoClientTest {
    @Autowired
    private MidTermForecastInfoClient client;

    @Test
    void getMidTermForecastNormalService() {
        ApiResponse<MidTermForecastDto> response = client.getMidTermForecast(1, 10, "105", "202304070600");
        assertEquals(ApiResponse.Result.NORMAL_SERVICE, response.result);
    }

    @Test
    void getMidTermForecastNoData() {
        ApiResponse<MidTermForecastDto> response = client.getMidTermForecast(1, 10, "108", "202304070600");
        assertEquals(ApiResponse.Result.NODATA_ERROR, response.result);
    }

    @Test
    void getMidTermLandForecastNormalService() {
        ApiResponse<MidTermLandForecastDto> response = client.getMidTermLandForecast(1, 10, "11B00000", "202304070600");
        assertEquals(ApiResponse.Result.NORMAL_SERVICE, response.result);
    }

    @Test
    void getMidTermLandForecastNoData() {
        ApiResponse<MidTermLandForecastDto> response = client.getMidTermLandForecast(1, 10, "11D10000", "202304070600");
        assertEquals(ApiResponse.Result.NODATA_ERROR, response.result);
    }

    @Test
    void getMidTermTemperatureNormalService() {
        ApiResponse<MidTermTemperatureDto> response = client.getMidTermTemperature(1, 10, "11A00101", "202304070600");
        assertEquals(ApiResponse.Result.NORMAL_SERVICE, response.result);
    }

    @Test
    void getMidTermTemperatureNoData() {
        ApiResponse<MidTermTemperatureDto> response = client.getMidTermTemperature(1, 10, "11B10101", "202304070600");
        assertEquals(ApiResponse.Result.NODATA_ERROR, response.result);
    }

    @Test
    void getMidTermSeaForecastNormalService() {
        ApiResponse<MidTermSeaForecastDto> response = client.getMidTermSeaForecast(1, 10, "12A20000", "202304070600");
        assertEquals(ApiResponse.Result.NORMAL_SERVICE, response.result);
    }

    @Test
    void getMidTermSeaForecastNoData() {
        ApiResponse<MidTermSeaForecastDto> response = client.getMidTermSeaForecast(1, 10, "12A30000", "202304070600");
        assertEquals(ApiResponse.Result.NODATA_ERROR, response.result);
    }
}