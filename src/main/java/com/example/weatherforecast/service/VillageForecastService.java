package com.example.weatherforecast.service;

import com.example.weatherforecast.feign.VillageForecastInfoClient;
import com.example.weatherforecast.model.VillageForecast;
import com.example.weatherforecast.model.VillageForecastRegion;
import com.example.weatherforecast.model.VillageForecastValue;
import com.example.weatherforecast.repository.VillageForecastRegionRepository;
import com.example.weatherforecast.repository.VillageForecastRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Service
public class VillageForecastService {
    private static final Logger LOGGER = LoggerFactory.getLogger(VillageForecastService.class);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("uuuuMMdd");
    private final VillageForecastRepository forecastRepository;
    private final VillageForecastRegionRepository regionRepository;
    private final VillageForecastInfoClient client;
    private final Clock clock;

    public VillageForecastService(VillageForecastRepository forecastRepository,
                                  VillageForecastRegionRepository regionRepository,
                                  VillageForecastInfoClient client,
                                  Clock clock) {
        this.forecastRepository = forecastRepository;
        this.regionRepository = regionRepository;
        this.client = client;
        this.clock = clock;
    }

    public VillageForecast getOrCreate(VillageForecast.Type type, String firstLevel, String secondLevel, String thirdLevel) {
        VillageForecastRegion region = regionRepository.findByLevels(firstLevel, secondLevel, thirdLevel);
        if (region == null) {
            return null;
        }
        String baseDate = getBaseDate(type);
        String baseTime = getBaseTime(type);
        VillageForecast forecast = forecastRepository.findByTypeAndDateAndTimeAndRegion(type, baseDate, baseTime, region.getX(), region.getY());
        if (forecast != null) {
            return forecast;
        }
        return forecastRepository.save(fetchForecast(type, baseDate, baseTime, region));
    }

    protected String getBaseDate(VillageForecast.Type type) {
        LocalDateTime time = LocalDateTime.now(clock);
        switch (type) {
            case ULTRA_SHORT_NOWCAST:
                return DATE_FORMATTER.format(time.minusMinutes(40));
            case ULTRA_SHORT_FORECAST:
                return DATE_FORMATTER.format(time.minusMinutes(45));
            case VILLAGE_FORECAST:
                return DATE_FORMATTER.format(time.minusMinutes(130));
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    protected String getBaseTime(VillageForecast.Type type) {
        LocalDateTime time = LocalDateTime.now(clock);
        switch (type) {
            case ULTRA_SHORT_NOWCAST:
                return DateTimeFormatter.ofPattern("HH00").format(time.minusMinutes(40));
            case ULTRA_SHORT_FORECAST:
                return DateTimeFormatter.ofPattern("HH30").format(time.minusMinutes(45));
            case VILLAGE_FORECAST:
                return String.format("%02d00", time.minusMinutes(130).getHour() / 3 * 3 + 2);
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    protected VillageForecast fetchForecast(VillageForecast.Type type, String baseDate, String baseTime, VillageForecastRegion region) {
        VillageForecast forecast = new VillageForecast();
        forecast.setBaseDate(baseDate);
        forecast.setBaseTime(baseTime);
        forecast.setNx(region.getX());
        forecast.setNy(region.getY());
        forecast.setType(type);
        switch (type) {
            case ULTRA_SHORT_NOWCAST:
                LOGGER.info("Fetching ultra short nowcast for ({}, {}) at {}{}", region.getX(), region.getY(), baseDate, baseTime);
                forecast.setValues(client.getUltraShortNowcast(1, 1000, baseDate, baseTime, region.getX(), region.getY()).items
                        .stream()
                        .map(dto -> {
                            VillageForecastValue value = new VillageForecastValue();
                            value.setForecast(forecast);
                            value.setCategory(dto.category);
                            value.setForecastValue(dto.obsrValue);
                            return value;
                        }).collect(Collectors.toList()));
                break;
            case ULTRA_SHORT_FORECAST:
                LOGGER.info("Fetching ultra short forecast for ({}, {}) at {}{}", region.getX(), region.getY(), baseDate, baseTime);
                forecast.setValues(client.getUltraShortForecast(1, 1000, baseDate, baseTime, region.getX(), region.getY()).items
                        .stream()
                        .map(dto -> {
                            VillageForecastValue value = new VillageForecastValue();
                            value.setForecast(forecast);
                            value.setForecastDate(dto.fcstDate);
                            value.setForecastTime(dto.fcstTime);
                            value.setCategory(dto.category);
                            value.setForecastValue(dto.fcstValue);
                            return value;
                        }).collect(Collectors.toList()));
                break;
            case VILLAGE_FORECAST:
                LOGGER.info("Fetching village forecast for ({}, {}) at {}{}", region.getX(), region.getY(), baseDate, baseTime);
                forecast.setValues(client.getVillageForecast(1, 1000, baseDate, baseTime, region.getX(), region.getY()).items
                        .stream()
                        .map(dto -> {
                            VillageForecastValue value = new VillageForecastValue();
                            value.setForecast(forecast);
                            value.setForecastDate(dto.fcstDate);
                            value.setForecastTime(dto.fcstTime);
                            value.setCategory(dto.category);
                            value.setForecastValue(dto.fcstValue);
                            return value;
                        }).collect(Collectors.toList()));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        return forecast;
    }
}
