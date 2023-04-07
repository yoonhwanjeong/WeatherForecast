package com.example.weatherforecast.repository;

import com.example.weatherforecast.TestSetup;
import com.example.weatherforecast.model.MidTermForecastRegion;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestSetup
class MidTermForecastRegionRepositoryTest {
    @Autowired
    private MidTermForecastRegionRepository repository;

    @Test
    void testFindAllDistinctRegionByType() {
        List<String> expected = Lists.newArrayList("강원도", "전국", "서울", "인천", "경기도", "충청북도",
                "대전", "세종", "충청남도", "전라북도", "광주", "전라남도",
                "대구", "경상북도", "부산", "울산", "경상남도", "제주도");
        assertEquals(expected, repository.findAllDistinctRegionByType(MidTermForecastRegion.Type.FORECAST));
    }

    @Test
    void testFindByNameAndType() {
        MidTermForecastRegion region = repository.findByNameAndType("강원도", MidTermForecastRegion.Type.FORECAST);
        assertNotNull(region);
        assertEquals("105", region.getCode());
    }
}