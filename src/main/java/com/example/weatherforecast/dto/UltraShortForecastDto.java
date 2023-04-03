package com.example.weatherforecast.dto;

import com.example.weatherforecast.model.ForecastCategory;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UltraShortForecastDto {
    public final String baseDate;
    public final String baseTime;
    public final int nx;
    public final int ny;
    public final ForecastCategory category;
    public final String fcstDate;
    public final String fcstTime;
    public final String fcstValue;

    @JsonCreator
    public UltraShortForecastDto(@JsonProperty String baseDate,
                                 @JsonProperty String baseTime,
                                 @JsonProperty int nx,
                                 @JsonProperty int ny,
                                 @JsonProperty ForecastCategory category,
                                 @JsonProperty String fcstDate,
                                 @JsonProperty String fcstTime,
                                 @JsonProperty String fcstValue) {
        this.baseDate = baseDate;
        this.baseTime = baseTime;
        this.nx = nx;
        this.ny = ny;
        this.category = category;
        this.fcstDate = fcstDate;
        this.fcstTime = fcstTime;
        this.fcstValue = fcstValue;
    }
}
