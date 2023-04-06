package com.example.weatherforecast.repository.querydsl;

import com.example.weatherforecast.model.MidTermSeaForecast;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.example.weatherforecast.model.QMidTermSeaForecast.midTermSeaForecast;

public class QMidTermSeaForecastRepositoryImpl implements QMidTermSeaForecastRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public QMidTermSeaForecastRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public MidTermSeaForecast findByRegionAndTime(String region, String time) {
        return jpaQueryFactory
                .selectFrom(midTermSeaForecast)
                .where(midTermSeaForecast.regId.eq(region),
                        midTermSeaForecast.tmFc.eq(time))
                .fetchFirst();
    }
}
