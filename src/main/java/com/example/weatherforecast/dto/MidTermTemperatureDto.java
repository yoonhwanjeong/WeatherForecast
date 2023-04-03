package com.example.weatherforecast.dto;

import com.example.weatherforecast.model.MidTermTemperatureValue;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class MidTermTemperatureDto {
    public final String regId;
    @JsonUnwrapped
    public final MidTermTemperatureValue value;

    public MidTermTemperatureDto(String regId, MidTermTemperatureValue value) {
        this.regId = regId;
        this.value = value;
    }

    public String getRegId() {
        return regId;
    }

    public MidTermTemperatureValue getValue() {
        return value;
    }
}
