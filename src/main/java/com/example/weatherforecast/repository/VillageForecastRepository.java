package com.example.weatherforecast.repository;

import com.example.weatherforecast.model.VillageForecast;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VillageForecastRepository extends JpaRepository<VillageForecast, Long> {
}
