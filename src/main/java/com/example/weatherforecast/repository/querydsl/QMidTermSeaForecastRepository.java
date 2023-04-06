package com.example.weatherforecast.repository.querydsl;

import com.example.weatherforecast.model.MidTermSeaForecast;

public interface QMidTermSeaForecastRepository {
    MidTermSeaForecast findByRegionAndTime(String region, String time);
}
