package com.example.weatherforecast.service;

import com.example.weatherforecast.dto.MidTermForecastDto;
import com.example.weatherforecast.dto.MidTermLandForecastDto;
import com.example.weatherforecast.dto.MidTermSeaForecastDto;
import com.example.weatherforecast.dto.MidTermTemperatureDto;
import com.example.weatherforecast.feign.MidTermForecastInfoClient;
import com.example.weatherforecast.model.MidTermForecast;
import com.example.weatherforecast.model.MidTermForecastRegion;
import com.example.weatherforecast.model.MidTermLandForecast;
import com.example.weatherforecast.model.MidTermSeaForecast;
import com.example.weatherforecast.model.MidTermTemperature;
import com.example.weatherforecast.repository.MidTermForecastRegionRepository;
import com.example.weatherforecast.repository.MidTermForecastRepository;
import com.example.weatherforecast.repository.MidTermLandForecastRepository;
import com.example.weatherforecast.repository.MidTermSeaForecastRepository;
import com.example.weatherforecast.repository.MidTermTemperatureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class MidTermForecastService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MidTermForecastService.class);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("uuuuMMdd");
    private final MidTermForecastRepository midTermForecastRepository;
    private final MidTermLandForecastRepository midTermLandForecastRepository;
    private final MidTermSeaForecastRepository midTermSeaForecastRepository;
    private final MidTermTemperatureRepository midTermTemperatureRepository;
    private final MidTermForecastRegionRepository midTermForecastRegionRepository;
    private final MidTermForecastInfoClient client;
    private final Clock clock;

    public MidTermForecastService(MidTermForecastRepository midTermForecastRepository,
                                  MidTermLandForecastRepository midTermLandForecastRepository,
                                  MidTermSeaForecastRepository midTermSeaForecastRepository,
                                  MidTermTemperatureRepository midTermTemperatureRepository,
                                  MidTermForecastRegionRepository midTermForecastRegionRepository,
                                  MidTermForecastInfoClient client,
                                  Clock clock) {
        this.midTermForecastRepository = midTermForecastRepository;
        this.midTermLandForecastRepository = midTermLandForecastRepository;
        this.midTermSeaForecastRepository = midTermSeaForecastRepository;
        this.midTermTemperatureRepository = midTermTemperatureRepository;
        this.midTermForecastRegionRepository = midTermForecastRegionRepository;
        this.client = client;
        this.clock = clock;
    }

    public MidTermForecast getMidTermForecast(String regionName) {
        MidTermForecastRegion region = getRegion(regionName, MidTermForecastRegion.Type.FORECAST);
        if (region == null) {
            return null;
        }
        String time = getTime();
        MidTermForecast forecast = midTermForecastRepository.findByRegionAndTime(region.getCode(), time);
        if (forecast != null) {
            return forecast;
        }
        LOGGER.info("Fetching mid-term forecast for {} at {}", region.getCode(), time);
        MidTermForecastDto dto = client.getMidTermForecast(1, 1000, region.getCode(), time).items.get(0);
        forecast = new MidTermForecast();
        forecast.setStnId(region.getCode());
        forecast.setTmFc(time);
        forecast.setWfSv(dto.wfSv);
        return midTermForecastRepository.save(forecast);
    }

    public MidTermLandForecast getMidTermLandForecast(String regionName) {
        MidTermForecastRegion region = getRegion(regionName, MidTermForecastRegion.Type.LAND);
        if (region == null) {
            return null;
        }
        String time = getTime();
        MidTermLandForecast forecast = midTermLandForecastRepository.findByRegionAndTime(region.getCode(), time);
        if (forecast != null) {
            return forecast;
        }
        LOGGER.info("Fetching mid-term land forecast for {} at {}", region.getCode(), time);
        MidTermLandForecastDto dto = client.getMidTermLandForecast(1, 1000, region.getCode(), time).items.get(0);
        forecast = new MidTermLandForecast();
        forecast.setRegId(region.getCode());
        forecast.setTmFc(time);
        forecast.setValue(dto.value);
        return midTermLandForecastRepository.save(forecast);
    }

    public MidTermSeaForecast getMidTermSeaForecast(String regionName) {
        MidTermForecastRegion region = getRegion(regionName, MidTermForecastRegion.Type.SEA);
        if (region == null) {
            return null;
        }
        String time = getTime();
        MidTermSeaForecast forecast = midTermSeaForecastRepository.findByRegionAndTime(region.getCode(), time);
        if (forecast != null) {
            return forecast;
        }
        LOGGER.info("Fetching mid-term sea forecast for {} at {}", region.getCode(), time);
        MidTermSeaForecastDto dto = client.getMidTermSeaForecast(1, 1000, region.getCode(), time).items.get(0);
        forecast = new MidTermSeaForecast();
        forecast.setRegId(region.getCode());
        forecast.setTmFc(time);
        forecast.setValue(dto.value);
        return midTermSeaForecastRepository.save(forecast);
    }

    public MidTermTemperature getMidTermTemperature(String regionName) {
        MidTermForecastRegion region = getRegion(regionName, MidTermForecastRegion.Type.TEMPERATURE);
        if (region == null) {
            return null;
        }
        String time = getTime();
        MidTermTemperature temperature = midTermTemperatureRepository.findByRegionAndTime(region.getCode(), time);
        if (temperature != null) {
            return temperature;
        }
        LOGGER.info("Fetching mid-term temperature for {} at {}", region.getCode(), time);
        MidTermTemperatureDto dto = client.getMidTermTemperature(1, 1000, region.getCode(), time).items.get(0);
        temperature = new MidTermTemperature();
        temperature.setRegId(region.getCode());
        temperature.setTmFc(time);
        temperature.setValue(dto.value);
        return midTermTemperatureRepository.save(temperature);
    }

    protected MidTermForecastRegion getRegion(String name, MidTermForecastRegion.Type type) {
        return midTermForecastRegionRepository.findByNameAndType(name, type);
    }

    protected String getTime() {
        LocalDateTime time = LocalDateTime.now(clock).minusHours(6);
        return String.format("%s%02d00", DATE_FORMATTER.format(time), time.getHour() / 12 * 12 + 6);
    }
}
