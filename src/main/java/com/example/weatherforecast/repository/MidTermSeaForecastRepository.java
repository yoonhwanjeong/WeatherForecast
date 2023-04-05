package com.example.weatherforecast.repository;

import com.example.weatherforecast.model.MidTermSeaForecast;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MidTermSeaForecastRepository extends JpaRepository<MidTermSeaForecast, Long> {
}
