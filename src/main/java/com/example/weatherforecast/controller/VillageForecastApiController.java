package com.example.weatherforecast.controller;

import com.example.weatherforecast.dto.UltraShortForecastDto;
import com.example.weatherforecast.dto.UltraShortNowcastDto;
import com.example.weatherforecast.dto.VillageForecastDto;
import com.example.weatherforecast.model.VillageForecast;
import com.example.weatherforecast.model.VillageForecastValue;
import com.example.weatherforecast.service.VillageForecastService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/villageForecast")
public class VillageForecastApiController {
    private final VillageForecastService service;

    public VillageForecastApiController(VillageForecastService service) {
        this.service = service;
    }

    /**
     * @param firstLevel 1단계 행정구역
     * @param secondLevel 2단계 행정구역
     * @param thirdLevel 3단계 행정구역
     * @return 초단기실황
     */
    @GetMapping("/ultraShortNowcast")
    public List<UltraShortNowcastDto> getUltraShortNowcast(@RequestParam String firstLevel,
                                                           @RequestParam(required = false) String secondLevel,
                                                           @RequestParam(required = false) String thirdLevel) {
        VillageForecast forecast = service.getOrCreate(VillageForecast.Type.ULTRA_SHORT_NOWCAST, firstLevel, secondLevel, thirdLevel);
        List<UltraShortNowcastDto> values = new ArrayList<>(forecast.getValues().size());
        for (VillageForecastValue value : forecast.getValues()) {
            values.add(new UltraShortNowcastDto(forecast.getBaseDate(),
                    forecast.getBaseTime(),
                    forecast.getNx(),
                    forecast.getNy(),
                    value.getCategory(),
                    value.getForecastValue()));
        }
        return values;
    }

    /**
     * @param firstLevel 1단계 행정구역
     * @param secondLevel 2단계 행정구역
     * @param thirdLevel 3단계 행정구역
     * @return 초단기예보
     */
    @GetMapping("/ultraShortForecast")
    public List<UltraShortForecastDto> getUltraShortForecast(@RequestParam String firstLevel,
                                                             @RequestParam(required = false) String secondLevel,
                                                             @RequestParam(required = false) String thirdLevel) {
        VillageForecast forecast = service.getOrCreate(VillageForecast.Type.ULTRA_SHORT_FORECAST, firstLevel, secondLevel, thirdLevel);
        List<UltraShortForecastDto> values = new ArrayList<>(forecast.getValues().size());
        for (VillageForecastValue value : forecast.getValues()) {
            values.add(new UltraShortForecastDto(forecast.getBaseDate(),
                    forecast.getBaseTime(),
                    forecast.getNx(),
                    forecast.getNy(),
                    value.getCategory(),
                    value.getForecastDate(),
                    value.getForecastTime(),
                    value.getForecastValue()));
        }
        return values;
    }

    /**
     * @param firstLevel 1단계 행정구역
     * @param secondLevel 2단계 행정구역
     * @param thirdLevel 3단계 행정구역
     * @return 단기예보
     */
    @GetMapping("/villageForecast")
    public List<VillageForecastDto> getVillageForecast(@RequestParam String firstLevel,
                                                       @RequestParam(required = false) String secondLevel,
                                                       @RequestParam(required = false) String thirdLevel) {
        VillageForecast forecast = service.getOrCreate(VillageForecast.Type.VILLAGE_FORECAST, firstLevel, secondLevel, thirdLevel);
        List<VillageForecastDto> values = new ArrayList<>(forecast.getValues().size());
        for (VillageForecastValue value : forecast.getValues()) {
            values.add(new VillageForecastDto(forecast.getBaseDate(),
                    forecast.getBaseTime(),
                    value.getForecastDate(),
                    value.getForecastTime(),
                    value.getCategory(),
                    value.getForecastValue(),
                    forecast.getNx(),
                    forecast.getNy()));
        }
        return values;
    }
}
