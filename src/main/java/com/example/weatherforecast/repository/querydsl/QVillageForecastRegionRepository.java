package com.example.weatherforecast.repository.querydsl;

import com.example.weatherforecast.model.VillageForecastRegion;

import java.util.List;

public interface QVillageForecastRegionRepository {
    List<String> findAllDistinctFirstLevel();

    List<String> findAllDistinctSecondLevel(String firstLevel);

    List<String> findAllDistinctThirdLevel(String firstLevel, String secondLevel);

    VillageForecastRegion findByLevels(String firstLevel, String secondLevel, String thirdLevel);
}
