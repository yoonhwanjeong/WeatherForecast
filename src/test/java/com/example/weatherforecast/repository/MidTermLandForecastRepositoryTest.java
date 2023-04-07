package com.example.weatherforecast.repository;

import com.example.weatherforecast.TestSetup;
import com.example.weatherforecast.model.MidTermLandForecast;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestSetup
class MidTermLandForecastRepositoryTest {
    @Autowired
    private MidTermLandForecastRepository repository;

    @Test
    void testFindByRegionAndTime() {
        String regId = "11B00000";
        String tmFc = "202304070600";
        MidTermLandForecast forecast = repository.findByRegionAndTime(regId, tmFc);
        assertNotNull(forecast);
        assertEquals(regId, forecast.getRegId());
        assertEquals(tmFc, forecast.getTmFc());
    }
}