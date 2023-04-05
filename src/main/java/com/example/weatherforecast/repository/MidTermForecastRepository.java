package com.example.weatherforecast.repository;

import com.example.weatherforecast.model.MidTermForecast;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MidTermForecastRepository extends JpaRepository<MidTermForecast, Long> {
}
