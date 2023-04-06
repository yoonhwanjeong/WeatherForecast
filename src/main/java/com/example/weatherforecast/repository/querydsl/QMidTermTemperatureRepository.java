package com.example.weatherforecast.repository.querydsl;

import com.example.weatherforecast.model.MidTermTemperature;

public interface QMidTermTemperatureRepository {
    MidTermTemperature findByRegionAndTime(String region, String time);
}
