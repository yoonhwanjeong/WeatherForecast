package com.example.weatherforecast.controller;

import com.example.weatherforecast.model.MidTermForecastRegion;
import com.example.weatherforecast.service.MidTermForecastRegionService;
import com.example.weatherforecast.service.VillageForecastRegionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class IndexController {
    private final VillageForecastRegionService villageForecastRegionService;
    private final MidTermForecastRegionService midTermForecastRegionService;

    public IndexController(VillageForecastRegionService villageForecastRegionService,
                           MidTermForecastRegionService midTermForecastRegionService) {
        this.villageForecastRegionService = villageForecastRegionService;
        this.midTermForecastRegionService = midTermForecastRegionService;
    }

    @GetMapping
    public String getIndex(Model model) {
        model.addAttribute("firstLevels", villageForecastRegionService.getFirstLevels());
        model.addAttribute("midTermRegions", midTermForecastRegionService.getRegions(MidTermForecastRegion.Type.FORECAST));
        return "index";
    }
}
