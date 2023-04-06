package com.example.weatherforecast.repository;

import com.example.weatherforecast.model.MidTermForecast;
import com.example.weatherforecast.repository.querydsl.QMidTermForecastRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MidTermForecastRepository extends JpaRepository<MidTermForecast, Long>, QMidTermForecastRepository {
}
