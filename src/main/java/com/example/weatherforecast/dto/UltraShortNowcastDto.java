package com.example.weatherforecast.dto;

import com.example.weatherforecast.model.ForecastCategory;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UltraShortNowcastDto {
    public final String baseDate;
    public final String baseTime;
    public final int nx;
    public final int ny;
    public final ForecastCategory category;
    public final String obsrValue;

    @JsonCreator
    public UltraShortNowcastDto(@JsonProperty String baseDate,
                                @JsonProperty String baseTime,
                                @JsonProperty int nx,
                                @JsonProperty int ny,
                                @JsonProperty ForecastCategory category,
                                @JsonProperty String obsrValue) {
        this.baseDate = baseDate;
        this.baseTime = baseTime;
        this.nx = nx;
        this.ny = ny;
        this.category = category;
        this.obsrValue = obsrValue;
    }
}
