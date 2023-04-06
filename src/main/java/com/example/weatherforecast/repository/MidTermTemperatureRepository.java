package com.example.weatherforecast.repository;

import com.example.weatherforecast.model.MidTermTemperature;
import com.example.weatherforecast.repository.querydsl.QMidTermTemperatureRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MidTermTemperatureRepository extends JpaRepository<MidTermTemperature, Long>, QMidTermTemperatureRepository {
}
