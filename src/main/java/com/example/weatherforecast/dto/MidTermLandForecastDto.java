package com.example.weatherforecast.dto;

import com.example.weatherforecast.model.MidTermLandForecastValue;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class MidTermLandForecastDto {
    public final String regId;
    @JsonUnwrapped
    public final MidTermLandForecastValue value;

    public MidTermLandForecastDto(String regId, MidTermLandForecastValue value) {
        this.regId = regId;
        this.value = value;
    }

    public String getRegId() {
        return regId;
    }

    public MidTermLandForecastValue getValue() {
        return value;
    }
}
