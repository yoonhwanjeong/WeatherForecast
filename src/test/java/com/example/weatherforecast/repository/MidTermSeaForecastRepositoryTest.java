package com.example.weatherforecast.repository;

import com.example.weatherforecast.TestSetup;
import com.example.weatherforecast.model.MidTermSeaForecast;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestSetup
class MidTermSeaForecastRepositoryTest {
    @Autowired
    private MidTermSeaForecastRepository repository;

    @Test
    void testFindByRegionAndTime() {
        String regId = "12A20000";
        String tmFc = "202304070600";
        MidTermSeaForecast forecast = repository.findByRegionAndTime(regId, tmFc);
        assertNotNull(forecast);
        assertEquals(regId, forecast.getRegId());
        assertEquals(tmFc, forecast.getTmFc());
    }
}