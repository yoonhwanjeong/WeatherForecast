package com.example.weatherforecast.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class VillageForecastValue {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private VillageForecast forecast;
    private String forecastDate;
    private String forecastTime;
    @Enumerated(EnumType.STRING)
    private ForecastCategory category;
    private String forecastValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VillageForecast getForecast() {
        return forecast;
    }

    public void setForecast(VillageForecast forecast) {
        this.forecast = forecast;
    }

    public String getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(String forecastDate) {
        this.forecastDate = forecastDate;
    }

    public String getForecastTime() {
        return forecastTime;
    }

    public void setForecastTime(String forecastTime) {
        this.forecastTime = forecastTime;
    }

    public ForecastCategory getCategory() {
        return category;
    }

    public void setCategory(ForecastCategory category) {
        this.category = category;
    }

    public String getForecastValue() {
        return forecastValue;
    }

    public void setForecastValue(String forecastValue) {
        this.forecastValue = forecastValue;
    }
}
