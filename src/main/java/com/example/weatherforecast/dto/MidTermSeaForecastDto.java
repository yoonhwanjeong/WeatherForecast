package com.example.weatherforecast.dto;

import com.example.weatherforecast.model.MidTermSeaForecastValue;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class MidTermSeaForecastDto {
    public final String regId;
    @JsonUnwrapped
    public final MidTermSeaForecastValue value;

    public MidTermSeaForecastDto(String regId, MidTermSeaForecastValue value) {
        this.regId = regId;
        this.value = value;
    }

    public String getRegId() {
        return regId;
    }

    public MidTermSeaForecastValue getValue() {
        return value;
    }
}
