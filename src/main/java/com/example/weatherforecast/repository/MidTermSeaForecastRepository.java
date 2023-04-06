package com.example.weatherforecast.repository;

import com.example.weatherforecast.model.MidTermSeaForecast;
import com.example.weatherforecast.repository.querydsl.QMidTermSeaForecastRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MidTermSeaForecastRepository extends JpaRepository<MidTermSeaForecast, Long>, QMidTermSeaForecastRepository {
}
