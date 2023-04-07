package com.example.weatherforecast.service;

import com.example.weatherforecast.TestSetup;
import com.example.weatherforecast.model.VillageForecast;
import com.example.weatherforecast.model.VillageForecastRegion;
import com.example.weatherforecast.repository.VillageForecastRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@TestSetup
class VillageForecastServiceTest {
    @Autowired
    private VillageForecastService service;
    @Autowired
    private VillageForecastRepository repository;
    @MockBean
    private Clock clock;

    @BeforeEach
    void setUp() {
        Clock fixedClock = Clock.fixed(Instant.parse("2023-04-07T02:00:00Z"), ZoneId.of("+9"));
        given(clock.instant()).willReturn(fixedClock.instant());
        given(clock.getZone()).willReturn(fixedClock.getZone());
    }

    @Test
    void getOrCreateExisting() {
        long count = repository.count();
        VillageForecast forecast = service.getOrCreate(VillageForecast.Type.ULTRA_SHORT_NOWCAST, "서울특별시", "종로구", "청운효자동");
        assertEquals(count, repository.count());
        assertEquals("20230407", forecast.getBaseDate());
        assertEquals("1000", forecast.getBaseTime());
    }

    @Test
    void getOrCreateNew() {
        Clock fixedClock = Clock.fixed(Instant.parse("2023-04-07T03:00:00Z"), ZoneId.of("+9"));
        given(clock.instant()).willReturn(fixedClock.instant());
        long count = repository.count();
        VillageForecast forecast = service.getOrCreate(VillageForecast.Type.ULTRA_SHORT_NOWCAST, "서울특별시", "종로구", "청운효자동");
        assertEquals(count + 1, repository.count());
        assertEquals("20230407", forecast.getBaseDate());
        assertEquals("1100", forecast.getBaseTime());
    }

    @Test
    void getBaseDate() {
        assertEquals("20230407", service.getBaseDate(VillageForecast.Type.ULTRA_SHORT_NOWCAST));
        assertEquals("20230407", service.getBaseDate(VillageForecast.Type.ULTRA_SHORT_FORECAST));
        assertEquals("20230407", service.getBaseDate(VillageForecast.Type.VILLAGE_FORECAST));
    }

    @Test
    void getBaseTime() {
        assertEquals("1000", service.getBaseTime(VillageForecast.Type.ULTRA_SHORT_NOWCAST));
        assertEquals("1030", service.getBaseTime(VillageForecast.Type.ULTRA_SHORT_FORECAST));
        assertEquals("0800", service.getBaseTime(VillageForecast.Type.VILLAGE_FORECAST));
    }

    @Test
    void fetchForecast() {
        VillageForecastRegion region = new VillageForecastRegion();
        region.setX(60);
        region.setY(127);
        VillageForecast forecast = service.fetchForecast(VillageForecast.Type.ULTRA_SHORT_NOWCAST, "20230407", "1000", region);
        assertNull(forecast.getId());
        assertEquals(VillageForecast.Type.ULTRA_SHORT_NOWCAST, forecast.getType());
        assertEquals("20230407", forecast.getBaseDate());
        assertEquals("1000", forecast.getBaseTime());
        assertEquals(region.getX(), forecast.getNx());
        assertEquals(region.getY(), forecast.getNy());
    }
}