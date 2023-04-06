package com.example.weatherforecast.service;

import com.example.weatherforecast.model.MidTermForecastRegion;
import com.example.weatherforecast.repository.MidTermForecastRegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MidTermForecastRegionService {
    private final MidTermForecastRegionRepository repository;

    public MidTermForecastRegionService(MidTermForecastRegionRepository repository) {
        this.repository = repository;
    }

    public List<String> getRegions(MidTermForecastRegion.Type type) {
        return repository.findAllDistinctRegionByType(type);
    }
}
