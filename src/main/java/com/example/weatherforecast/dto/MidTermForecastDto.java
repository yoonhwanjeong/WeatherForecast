package com.example.weatherforecast.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MidTermForecastDto {
    public final String wfSv;

    @JsonCreator
    public MidTermForecastDto(@JsonProperty String wfSv) {
        this.wfSv = wfSv;
    }

    public String getWfSv() {
        return wfSv;
    }
}
