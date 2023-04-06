package com.example.weatherforecast.repository.querydsl;

import com.example.weatherforecast.model.VillageForecast;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.example.weatherforecast.model.QVillageForecast.villageForecast;

public class QVillageForecastRepositoryImpl implements QVillageForecastRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public QVillageForecastRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public VillageForecast findByTypeAndDateAndTimeAndRegion(VillageForecast.Type type, String baseDate, String baseTime, int nx, int ny) {
        return jpaQueryFactory
                .selectFrom(villageForecast)
                .where(villageForecast.type.eq(type),
                        villageForecast.baseDate.eq(baseDate),
                        villageForecast.baseTime.eq(baseTime),
                        villageForecast.nx.eq(nx),
                        villageForecast.ny.eq(ny))
                .fetchFirst();
    }
}
