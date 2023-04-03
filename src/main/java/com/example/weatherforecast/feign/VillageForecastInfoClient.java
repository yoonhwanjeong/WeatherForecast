package com.example.weatherforecast.feign;

import com.example.weatherforecast.dto.ApiResponse;
import com.example.weatherforecast.dto.UltraShortForecastDto;
import com.example.weatherforecast.dto.UltraShortNowcastDto;
import com.example.weatherforecast.dto.VillageForecastDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 기상청 단기예보 Open API를 위한 Feign 클라이언트
 */
@FeignClient(value = "villageForecastInfoClient", url = "${spring.cloud.openfeign.client.config.villageForecastInfoClient.url}")
public interface VillageForecastInfoClient {
    @GetMapping("/getUltraSrtNcst")
    ApiResponse<UltraShortNowcastDto> getUltraShortNowcast(@RequestParam int pageNo,
                                                           @RequestParam int numOfRows,
                                                           @RequestParam("base_date") String baseDate,
                                                           @RequestParam("base_time") String baseTime,
                                                           @RequestParam int nx,
                                                           @RequestParam int ny);

    @GetMapping("/getUltraSrtFcst")
    ApiResponse<UltraShortForecastDto> getUltraShortForecast(@RequestParam int pageNo,
                                                             @RequestParam int numOfRows,
                                                             @RequestParam("base_date") String baseDate,
                                                             @RequestParam("base_time") String baseTime,
                                                             @RequestParam int nx,
                                                             @RequestParam int ny);

    @GetMapping("/getVilageFcst")
    ApiResponse<VillageForecastDto> getVillageForecast(@RequestParam int pageNo,
                                                       @RequestParam int numOfRows,
                                                       @RequestParam("base_date") String baseDate,
                                                       @RequestParam("base_time") String baseTime,
                                                       @RequestParam int nx,
                                                       @RequestParam int ny);
}
