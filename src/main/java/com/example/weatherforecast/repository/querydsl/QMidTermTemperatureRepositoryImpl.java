package com.example.weatherforecast.repository.querydsl;

import com.example.weatherforecast.model.MidTermTemperature;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.example.weatherforecast.model.QMidTermTemperature.midTermTemperature;

public class QMidTermTemperatureRepositoryImpl implements QMidTermTemperatureRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public QMidTermTemperatureRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public MidTermTemperature findByRegionAndTime(String region, String time) {
        return jpaQueryFactory
                .selectFrom(midTermTemperature)
                .where(midTermTemperature.regId.eq(region),
                        midTermTemperature.tmFc.eq(time))
                .fetchFirst();
    }
}
