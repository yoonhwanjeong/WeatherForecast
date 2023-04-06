package com.example.weatherforecast.component;

import com.example.weatherforecast.model.MidTermForecastRegion;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * 중기 예보 타입 문자열을 Enum으로 변경하는 Component
 */
@Component
public class MidTermForecastTypeConverter implements Converter<String, MidTermForecastRegion.Type> {
    @Override
    public MidTermForecastRegion.Type convert(String source) {
        try {
            return MidTermForecastRegion.Type.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
