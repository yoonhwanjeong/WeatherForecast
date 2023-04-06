package com.example.weatherforecast.service;

import com.example.weatherforecast.repository.VillageForecastRegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VillageForecastRegionService {
    private final VillageForecastRegionRepository repository;

    public VillageForecastRegionService(VillageForecastRegionRepository repository) {
        this.repository = repository;
    }

    public List<String> getFirstLevels() {
        return repository.findAllDistinctFirstLevel();
    }

    public List<String> getSecondLevels(String firstLevel) {
        return repository.findAllDistinctSecondLevel(firstLevel);
    }

    public List<String> getThirdLevels(String firstLevel, String secondLevel) {
        return repository.findAllDistinctThirdLevel(firstLevel, secondLevel);
    }
}
