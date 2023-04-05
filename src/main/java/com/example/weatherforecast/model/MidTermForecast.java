package com.example.weatherforecast.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MidTermForecast {
    @Id
    @GeneratedValue
    private Long id;
    private String stnId;
    private String tmFc;
    @Column(columnDefinition = "TEXT")
    private String wfSv;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStnId() {
        return stnId;
    }

    public void setStnId(String stnId) {
        this.stnId = stnId;
    }

    public String getTmFc() {
        return tmFc;
    }

    public void setTmFc(String tmFc) {
        this.tmFc = tmFc;
    }

    public String getWfSv() {
        return wfSv;
    }

    public void setWfSv(String wfSv) {
        this.wfSv = wfSv;
    }
}
