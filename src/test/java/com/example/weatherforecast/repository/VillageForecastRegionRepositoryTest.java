package com.example.weatherforecast.repository;

import com.example.weatherforecast.TestSetup;
import com.example.weatherforecast.model.VillageForecastRegion;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestSetup
class VillageForecastRegionRepositoryTest {
    @Autowired
    private VillageForecastRegionRepository repository;

    @Test
    void testFindAllDistinctFirstLevel() {
        List<String> expected = Lists.newArrayList("서울특별시", "부산광역시", "대구광역시",
                "인천광역시", "광주광역시", "대전광역시",
                "울산광역시", "세종특별자치시", "경기도",
                "강원도", "충청북도", "충청남도",
                "전라북도", "전라남도", "경상북도",
                "경상남도", "제주특별자치도", "이어도");
        assertEquals(expected, repository.findAllDistinctFirstLevel());
    }

    @Test
    void testFindAllDistinctSecondLevel() {
        List<String> expected = Lists.newArrayList("종로구", "중구", "용산구", "성동구", "광진구",
                "동대문구", "중랑구", "성북구", "강북구", "도봉구",
                "노원구", "은평구", "서대문구", "마포구", "양천구",
                "강서구", "구로구", "금천구", "영등포구", "동작구",
                "관악구", "서초구", "강남구", "송파구", "강동구");
        assertEquals(expected, repository.findAllDistinctSecondLevel("서울특별시"));
    }

    @Test
    void testFindAllDistinctThirdLevel() {
        List<String> expected = Lists.newArrayList("청운효자동", "사직동", "삼청동",
                "부암동", "평창동", "무악동",
                "교남동", "가회동", "종로1.2.3.4가동",
                "종로5.6가동", "이화동", "혜화동",
                "창신제1동", "창신제2동", "창신제3동",
                "숭인제1동", "숭인제2동");
        assertEquals(expected, repository.findAllDistinctThirdLevel("서울특별시", "종로구"));
    }

    @Test
    void testFindByLevels() {
        VillageForecastRegion region = repository.findByLevels("서울특별시", "종로구", "청운효자동");
        assertNotNull(region);
        assertEquals("1111051500", region.getCode());
    }
}