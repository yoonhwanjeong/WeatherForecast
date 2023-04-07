package com.example.weatherforecast.repository;

import com.example.weatherforecast.TestSetup;
import com.example.weatherforecast.model.VillageForecast;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestSetup
class VillageForecastRepositoryTest {
    @Autowired
    private VillageForecastRepository repository;

    @Test
    void testFindByTypeAndDateAndTimeAndRegion() {
        VillageForecast.Type type = VillageForecast.Type.ULTRA_SHORT_NOWCAST;
        String baseDate = "20230407";
        String baseTime = "1000";
        int nx = 60;
        int ny = 127;
        VillageForecast forecast = repository.findByTypeAndDateAndTimeAndRegion(type, baseDate, baseTime, nx, ny);
        assertNotNull(forecast);
        assertEquals(baseDate, forecast.getBaseDate());
        assertEquals(baseTime, forecast.getBaseTime());
        assertEquals(nx, forecast.getNx());
        assertEquals(ny, forecast.getNy());
        assertEquals(type, forecast.getType());
    }
}