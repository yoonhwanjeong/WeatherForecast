package com.example.weatherforecast.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class VillageForecast {
    @Id
    @GeneratedValue
    private Long id;
    private String baseDate;
    private String baseTime;
    private int nx;
    private int ny;
    @OneToMany(mappedBy = "forecast", cascade = CascadeType.ALL)
    private List<VillageForecastValue> values;
    @Enumerated(EnumType.STRING)
    private Type type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBaseDate() {
        return baseDate;
    }

    public void setBaseDate(String baseDate) {
        this.baseDate = baseDate;
    }

    public String getBaseTime() {
        return baseTime;
    }

    public void setBaseTime(String baseTime) {
        this.baseTime = baseTime;
    }

    public int getNx() {
        return nx;
    }

    public void setNx(int nx) {
        this.nx = nx;
    }

    public int getNy() {
        return ny;
    }

    public void setNy(int ny) {
        this.ny = ny;
    }

    public List<VillageForecastValue> getValues() {
        return values;
    }

    public void setValues(List<VillageForecastValue> values) {
        this.values = values;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Type {
        ULTRA_SHORT_NOWCAST, ULTRA_SHORT_FORECAST, VILLAGE_FORECAST
    }
}
