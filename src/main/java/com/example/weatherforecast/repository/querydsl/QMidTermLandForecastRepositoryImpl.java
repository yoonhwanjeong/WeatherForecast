package com.example.weatherforecast.repository.querydsl;

import com.example.weatherforecast.model.MidTermLandForecast;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.example.weatherforecast.model.QMidTermLandForecast.midTermLandForecast;

public class QMidTermLandForecastRepositoryImpl implements QMidTermLandForecastRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public QMidTermLandForecastRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public MidTermLandForecast findByRegionAndTime(String region, String time) {
        return jpaQueryFactory
                .selectFrom(midTermLandForecast)
                .where(midTermLandForecast.regId.eq(region),
                        midTermLandForecast.tmFc.eq(time))
                .fetchFirst();
    }
}
