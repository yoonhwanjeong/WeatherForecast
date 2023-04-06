package com.example.weatherforecast.repository.querydsl;

import com.example.weatherforecast.model.MidTermForecast;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.example.weatherforecast.model.QMidTermForecast.midTermForecast;

public class QMidTermForecastRepositoryImpl implements QMidTermForecastRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public QMidTermForecastRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public MidTermForecast findByRegionAndTime(String region, String time) {
        return jpaQueryFactory
                .selectFrom(midTermForecast)
                .where(midTermForecast.stnId.eq(region),
                        midTermForecast.tmFc.eq(time))
                .fetchFirst();
    }
}
