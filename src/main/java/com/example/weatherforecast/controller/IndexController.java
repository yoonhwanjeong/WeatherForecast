package com.example.weatherforecast.controller;

import com.example.weatherforecast.service.VillageForecastRegionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class IndexController {
    private final VillageForecastRegionService villageForecastRegionService;

    public IndexController(VillageForecastRegionService villageForecastRegionService) {
        this.villageForecastRegionService = villageForecastRegionService;
    }

    @GetMapping
    public String getIndex(Model model) {
        model.addAttribute("firstLevels", villageForecastRegionService.getFirstLevels());
        return "index";
    }
}
