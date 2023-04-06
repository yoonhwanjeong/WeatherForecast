package com.example.weatherforecast.controller;

import com.example.weatherforecast.dto.MidTermForecastDto;
import com.example.weatherforecast.dto.MidTermLandForecastDto;
import com.example.weatherforecast.dto.MidTermSeaForecastDto;
import com.example.weatherforecast.dto.MidTermTemperatureDto;
import com.example.weatherforecast.model.MidTermForecast;
import com.example.weatherforecast.model.MidTermForecastRegion;
import com.example.weatherforecast.model.MidTermLandForecast;
import com.example.weatherforecast.model.MidTermSeaForecast;
import com.example.weatherforecast.model.MidTermTemperature;
import com.example.weatherforecast.service.MidTermForecastService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/midTermForecast")
public class MidTermForecastController {
    private final MidTermForecastService service;

    public MidTermForecastController(MidTermForecastService service) {
        this.service = service;
    }

    @GetMapping
    public String getMidTermForecast(Model model, @RequestParam MidTermForecastRegion.Type type, @RequestParam String region) {
        model.addAttribute("type", type);
        model.addAttribute("region", region);
        Object result = null;
        String tmFc = null;
        switch (type) {
            case FORECAST: {
                MidTermForecast forecast = service.getMidTermForecast(region);
                if (forecast != null) {
                    result = new MidTermForecastDto(forecast.getWfSv());
                    tmFc = forecast.getTmFc();
                }
                break;
            }
            case LAND: {
                MidTermLandForecast forecast = service.getMidTermLandForecast(region);
                if (forecast != null) {
                    result = new MidTermLandForecastDto(forecast.getRegId(), forecast.getValue());
                    tmFc = forecast.getTmFc();
                }
                break;
            }
            case SEA: {
                MidTermSeaForecast forecast = service.getMidTermSeaForecast(region);
                if (forecast != null) {
                    result = new MidTermSeaForecastDto(forecast.getRegId(), forecast.getValue());
                    tmFc = forecast.getTmFc();
                }
                break;
            }
            case TEMPERATURE: {
                MidTermTemperature temperature = service.getMidTermTemperature(region);
                if (temperature != null) {
                    result = new MidTermTemperatureDto(temperature.getRegId(), temperature.getValue());
                    tmFc = temperature.getTmFc();
                }
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        model.addAttribute("result", result);
        model.addAttribute("tmFc", tmFc);
        return "midTermForecast";
    }
}
