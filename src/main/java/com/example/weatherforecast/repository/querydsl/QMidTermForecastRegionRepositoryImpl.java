package com.example.weatherforecast.repository.querydsl;

import com.example.weatherforecast.model.MidTermForecastRegion;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

import static com.example.weatherforecast.model.QMidTermForecastRegion.midTermForecastRegion;

public class QMidTermForecastRegionRepositoryImpl implements QMidTermForecastRegionRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public QMidTermForecastRegionRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<String> findAllDistinctRegionByType(MidTermForecastRegion.Type type) {
        return jpaQueryFactory
                .select(midTermForecastRegion.name)
                .from(midTermForecastRegion)
                .where(midTermForecastRegion.type.eq(type))
                .distinct()
                .fetch();
    }

    @Override
    public MidTermForecastRegion findByNameAndType(String name, MidTermForecastRegion.Type type) {
        return jpaQueryFactory
                .selectFrom(midTermForecastRegion)
                .where(midTermForecastRegion.name.eq(name),
                        midTermForecastRegion.type.eq(type))
                .fetchFirst();
    }
}
