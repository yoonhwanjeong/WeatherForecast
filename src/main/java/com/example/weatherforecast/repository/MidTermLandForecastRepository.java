package com.example.weatherforecast.repository;

import com.example.weatherforecast.model.MidTermLandForecast;
import com.example.weatherforecast.repository.querydsl.QMidTermLandForecastRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MidTermLandForecastRepository extends JpaRepository<MidTermLandForecast, Long>, QMidTermLandForecastRepository {
}
