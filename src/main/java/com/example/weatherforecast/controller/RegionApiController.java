package com.example.weatherforecast.controller;

import com.example.weatherforecast.model.MidTermForecastRegion;
import com.example.weatherforecast.service.MidTermForecastRegionService;
import com.example.weatherforecast.service.VillageForecastRegionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/region")
public class RegionApiController {
    private final VillageForecastRegionService villageForecastRegionService;
    private final MidTermForecastRegionService midTermForecastRegionService;

    public RegionApiController(VillageForecastRegionService villageForecastRegionService,
                               MidTermForecastRegionService midTermForecastRegionService) {
        this.villageForecastRegionService = villageForecastRegionService;
        this.midTermForecastRegionService = midTermForecastRegionService;
    }

    /**
     * @param firstLevel 1단계 행정구역
     * @return 2단계 행정구역 목록
     */
    @GetMapping("/getSecondLevels")
    public List<String> getSecondLevels(@RequestParam String firstLevel) {
        return villageForecastRegionService.getSecondLevels(firstLevel);
    }

    /**
     * @param firstLevel 1단계 행정구역
     * @param secondLevel 2단계 행정구역
     * @return 3단계 행정구역 목록
     */
    @GetMapping("/getThirdLevels")
    public List<String> getThirdLevels(@RequestParam String firstLevel, @RequestParam String secondLevel) {
        return villageForecastRegionService.getThirdLevels(firstLevel, secondLevel);
    }

    /**
     * @param type 중기 예보 타입
     * @return 중기 예보 조화 가능한 지역 목록
     */
    @GetMapping("/getMidTermRegions")
    public List<String> getMidTermRegions(@RequestParam MidTermForecastRegion.Type type) {
        return midTermForecastRegionService.getRegions(type);
    }
}
