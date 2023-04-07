package com.example.weatherforecast.repository;

import com.example.weatherforecast.TestSetup;
import com.example.weatherforecast.model.MidTermTemperature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestSetup
class MidTermTemperatureRepositoryTest {
    @Autowired
    private MidTermTemperatureRepository repository;

    @Test
    void testFindByRegionAndTime() {
        String regId = "11A00101";
        String tmFc = "202304070600";
        MidTermTemperature temperature = repository.findByRegionAndTime(regId, tmFc);
        assertNotNull(temperature);
        assertEquals(regId, temperature.getRegId());
        assertEquals(tmFc, temperature.getTmFc());
    }
}