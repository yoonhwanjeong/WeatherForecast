package com.example.weatherforecast.feign;

import com.example.weatherforecast.dto.ApiResponse;
import com.example.weatherforecast.dto.MidTermForecastDto;
import com.example.weatherforecast.dto.MidTermLandForecastDto;
import com.example.weatherforecast.dto.MidTermSeaForecastDto;
import com.example.weatherforecast.dto.MidTermTemperatureDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 기상청 중기예보 Open API를 위한 Feign 클라이언트
 */
@FeignClient(value = "midTermForecastInfoClient", url = "${spring.cloud.openfeign.client.config.midTermForecastInfoClient.url}")
public interface MidTermForecastInfoClient {
    @GetMapping("/getMidFcst")
    ApiResponse<MidTermForecastDto> getMidTermForecast(@RequestParam int pageNo,
                                                       @RequestParam int numOfRows,
                                                       @RequestParam String stnId,
                                                       @RequestParam String tmFc);

    @GetMapping("/getMidLandFcst")
    ApiResponse<MidTermLandForecastDto> getMidTermLandForecast(@RequestParam int pageNo,
                                                               @RequestParam int numOfRows,
                                                               @RequestParam String regId,
                                                               @RequestParam String tmFc);

    @GetMapping("/getMidTa")
    ApiResponse<MidTermTemperatureDto> getMidTermTemperature(@RequestParam int pageNo,
                                                             @RequestParam int numOfRows,
                                                             @RequestParam String regId,
                                                             @RequestParam String tmFc);

    @GetMapping("/getMidSeaFcst")
    ApiResponse<MidTermSeaForecastDto> getMidTermSeaForecast(@RequestParam int pageNo,
                                                             @RequestParam int numOfRows,
                                                             @RequestParam String regId,
                                                             @RequestParam String tmFc);
}
