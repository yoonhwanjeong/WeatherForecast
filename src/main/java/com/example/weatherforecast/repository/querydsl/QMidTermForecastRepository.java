package com.example.weatherforecast.repository.querydsl;

import com.example.weatherforecast.model.MidTermForecast;

public interface QMidTermForecastRepository {
    MidTermForecast findByRegionAndTime(String region, String time);
}
