package com.example.weatherforecast.repository;

import com.example.weatherforecast.model.MidTermLandForecast;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MidTermLandForecastRepository extends JpaRepository<MidTermLandForecast, Long> {
}
