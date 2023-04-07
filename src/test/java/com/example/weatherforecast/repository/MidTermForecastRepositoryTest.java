package com.example.weatherforecast.repository;

import com.example.weatherforecast.TestSetup;
import com.example.weatherforecast.model.MidTermForecast;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestSetup
class MidTermForecastRepositoryTest {
    @Autowired
    private MidTermForecastRepository repository;

    @Test
    void testFindByRegionAndTime() {
        String stnId = "105";
        String tmFc = "202304070600";
        MidTermForecast forecast = repository.findByRegionAndTime(stnId, tmFc);
        assertNotNull(forecast);
        assertEquals(stnId, forecast.getStnId());
        assertEquals(tmFc, forecast.getTmFc());
    }
}