package com.example.weatherforecast.repository.querydsl;

import com.example.weatherforecast.model.VillageForecast;

public interface QVillageForecastRepository {
    VillageForecast findByTypeAndDateAndTimeAndRegion(VillageForecast.Type type, String baseDate, String baseTime, int nx, int ny);
}
