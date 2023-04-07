package com.example.weatherforecast.service;

import com.example.weatherforecast.TestSetup;
import com.example.weatherforecast.model.MidTermForecast;
import com.example.weatherforecast.model.MidTermLandForecast;
import com.example.weatherforecast.model.MidTermSeaForecast;
import com.example.weatherforecast.model.MidTermTemperature;
import com.example.weatherforecast.repository.MidTermForecastRepository;
import com.example.weatherforecast.repository.MidTermLandForecastRepository;
import com.example.weatherforecast.repository.MidTermSeaForecastRepository;
import com.example.weatherforecast.repository.MidTermTemperatureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@TestSetup
class MidTermForecastServiceTest {
    @Autowired
    private MidTermForecastService service;
    @Autowired
    private MidTermForecastRepository midTermForecastRepository;
    @Autowired
    private MidTermLandForecastRepository midTermLandForecastRepository;
    @Autowired
    private MidTermSeaForecastRepository midTermSeaForecastRepository;
    @Autowired
    private MidTermTemperatureRepository midTermTemperatureRepository;
    @MockBean
    private Clock clock;

    @BeforeEach
    void setUp() {
        Clock fixedClock = Clock.fixed(Instant.parse("2023-04-07T02:00:00Z"), ZoneId.of("+9"));
        given(clock.instant()).willReturn(fixedClock.instant());
        given(clock.getZone()).willReturn(fixedClock.getZone());
    }

    @Test
    void getMidTermForecastExisting() {
        long count = midTermForecastRepository.count();
        MidTermForecast forecast = service.getMidTermForecast("강원도");
        assertEquals(count, midTermForecastRepository.count());
        assertEquals("202304070600", forecast.getTmFc());
    }

    @Test
    void getMidTermForecastNew() {
        Clock fixedClock = Clock.fixed(Instant.parse("2023-04-07T14:00:00Z"), ZoneId.of("+9"));
        given(clock.instant()).willReturn(fixedClock.instant());
        long count = midTermForecastRepository.count();
        MidTermForecast forecast = service.getMidTermForecast("강원도");
        assertEquals(count + 1, midTermForecastRepository.count());
        assertEquals("202304071800", forecast.getTmFc());
    }

    @Test
    void getMidTermLandForecastExisting() {
        long count = midTermLandForecastRepository.count();
        MidTermLandForecast forecast = service.getMidTermLandForecast("서울");
        assertEquals(count, midTermLandForecastRepository.count());
        assertEquals("202304070600", forecast.getTmFc());
    }

    @Test
    void getMidTermLandForecastNew() {
        Clock fixedClock = Clock.fixed(Instant.parse("2023-04-07T14:00:00Z"), ZoneId.of("+9"));
        given(clock.instant()).willReturn(fixedClock.instant());
        long count = midTermLandForecastRepository.count();
        MidTermLandForecast forecast = service.getMidTermLandForecast("서울");
        assertEquals(count + 1, midTermLandForecastRepository.count());
        assertEquals("202304071800", forecast.getTmFc());
    }

    @Test
    void getMidTermSeaForecastExisting() {
        long count = midTermSeaForecastRepository.count();
        MidTermSeaForecast forecast = service.getMidTermSeaForecast("서해중부");
        assertEquals(count, midTermSeaForecastRepository.count());
        assertEquals("202304070600", forecast.getTmFc());
    }

    @Test
    void getMidTermSeaForecastNew() {
        Clock fixedClock = Clock.fixed(Instant.parse("2023-04-07T14:00:00Z"), ZoneId.of("+9"));
        given(clock.instant()).willReturn(fixedClock.instant());
        long count = midTermSeaForecastRepository.count();
        MidTermSeaForecast forecast = service.getMidTermSeaForecast("서해중부");
        assertEquals(count + 1, midTermSeaForecastRepository.count());
        assertEquals("202304071800", forecast.getTmFc());
    }

    @Test
    void getMidTermTemperatureExisting() {
        long count = midTermTemperatureRepository.count();
        MidTermTemperature temperature = service.getMidTermTemperature("백령도");
        assertEquals(count, midTermTemperatureRepository.count());
        assertEquals("202304070600", temperature.getTmFc());
    }

    @Test
    void getMidTermTemperatureNew() {
        Clock fixedClock = Clock.fixed(Instant.parse("2023-04-07T14:00:00Z"), ZoneId.of("+9"));
        given(clock.instant()).willReturn(fixedClock.instant());
        long count = midTermTemperatureRepository.count();
        MidTermTemperature temperature = service.getMidTermTemperature("백령도");
        assertEquals(count + 1, midTermTemperatureRepository.count());
        assertEquals("202304071800", temperature.getTmFc());
    }

    @Test
    void getTime() {
        assertEquals("202304070600", service.getTime());
    }
}