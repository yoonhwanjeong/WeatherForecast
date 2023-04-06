package com.example.weatherforecast.repository;

import com.example.weatherforecast.model.VillageForecast;
import com.example.weatherforecast.repository.querydsl.QVillageForecastRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VillageForecastRepository extends JpaRepository<VillageForecast, Long>, QVillageForecastRepository {
}
