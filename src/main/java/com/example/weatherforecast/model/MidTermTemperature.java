package com.example.weatherforecast.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MidTermTemperature {
    @Id
    @GeneratedValue
    private Long id;
    private String regId;
    private String tmFc;
    @Embedded
    private MidTermTemperatureValue value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public String getTmFc() {
        return tmFc;
    }

    public void setTmFc(String tmFc) {
        this.tmFc = tmFc;
    }

    public MidTermTemperatureValue getValue() {
        return value;
    }

    public void setValue(MidTermTemperatureValue value) {
        this.value = value;
    }
}
