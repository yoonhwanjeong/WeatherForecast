package com.example.weatherforecast.repository.querydsl;

import com.example.weatherforecast.model.MidTermLandForecast;

public interface QMidTermLandForecastRepository {
    MidTermLandForecast findByRegionAndTime(String region, String time);
}
