package com.example.weatherforecast.repository;

import com.example.weatherforecast.model.MidTermForecastRegion;
import com.example.weatherforecast.repository.querydsl.QMidTermForecastRegionRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MidTermForecastRegionRepository extends JpaRepository<MidTermForecastRegion, Long>, QMidTermForecastRegionRepository {
}
