package com.example.weatherforecast.repository.querydsl;

import com.example.weatherforecast.model.VillageForecastRegion;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

import static com.example.weatherforecast.model.QVillageForecastRegion.villageForecastRegion;

public class QVillageForecastRegionRepositoryImpl implements QVillageForecastRegionRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public QVillageForecastRegionRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<String> findAllDistinctFirstLevel() {
        return jpaQueryFactory
                .select(villageForecastRegion.firstLevel)
                .from(villageForecastRegion)
                .distinct()
                .fetch();
    }

    @Override
    public List<String> findAllDistinctSecondLevel(String firstLevel) {
        return jpaQueryFactory
                .select(villageForecastRegion.secondLevel)
                .from(villageForecastRegion)
                .where(villageForecastRegion.firstLevel.eq(firstLevel),
                        villageForecastRegion.secondLevel.isNotEmpty())
                .distinct()
                .fetch();
    }

    @Override
    public List<String> findAllDistinctThirdLevel(String firstLevel, String secondLevel) {
        return jpaQueryFactory
                .select(villageForecastRegion.thirdLevel)
                .from(villageForecastRegion)
                .where(villageForecastRegion.firstLevel.eq(firstLevel),
                        villageForecastRegion.secondLevel.eq(secondLevel),
                        villageForecastRegion.thirdLevel.isNotEmpty())
                .distinct()
                .fetch();
    }

    @Override
    public VillageForecastRegion findByLevels(String firstLevel, String secondLevel, String thirdLevel) {
        return jpaQueryFactory
                .selectFrom(villageForecastRegion)
                .where(villageForecastRegion.firstLevel.eq(firstLevel),
                        secondLevel != null ? villageForecastRegion.secondLevel.eq(secondLevel) : villageForecastRegion.secondLevel.isNull(),
                        thirdLevel != null ? villageForecastRegion.thirdLevel.eq(thirdLevel) : villageForecastRegion.thirdLevel.isNull())
                .fetchFirst();
    }
}
