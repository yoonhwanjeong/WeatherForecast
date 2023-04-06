package com.example.weatherforecast.repository.querydsl;

import com.example.weatherforecast.model.MidTermForecastRegion;

import java.util.List;

public interface QMidTermForecastRegionRepository {
    List<String> findAllDistinctRegionByType(MidTermForecastRegion.Type type);

    MidTermForecastRegion findByNameAndType(String name, MidTermForecastRegion.Type type);
}
