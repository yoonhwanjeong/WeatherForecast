package com.example.weatherforecast.repository;

import com.example.weatherforecast.model.VillageForecastRegion;
import com.example.weatherforecast.repository.querydsl.QVillageForecastRegionRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VillageForecastRegionRepository extends JpaRepository<VillageForecastRegion, Long>, QVillageForecastRegionRepository {
}
