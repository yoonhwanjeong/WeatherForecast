package com.example.weatherforecast.dto;

import com.example.weatherforecast.model.ForecastCategory;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class VillageForecastDto {
    public final String baseDate;
    public final String baseTime;
    public final String fcstDate;
    public final String fcstTime;
    public final ForecastCategory category;
    public final String fcstValue;
    public final int nx;
    public final int ny;

    @JsonCreator
    public VillageForecastDto(@JsonProperty String baseDate,
                              @JsonProperty String baseTime,
                              @JsonProperty String fcstDate,
                              @JsonProperty String fcstTime,
                              @JsonProperty ForecastCategory category,
                              @JsonProperty String fcstValue,
                              @JsonProperty int nx,
                              @JsonProperty int ny) {
        this.baseDate = baseDate;
        this.baseTime = baseTime;
        this.fcstDate = fcstDate;
        this.fcstTime = fcstTime;
        this.category = category;
        this.fcstValue = fcstValue;
        this.nx = nx;
        this.ny = ny;
    }
}
