package com.example.weatherforecast.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 컨버터를 등록하는 Configuration
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    private final Converter<?, ?>[] converters;

    public WebMvcConfiguration(Converter<?, ?>[] converters) {
        this.converters = converters;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        if (converters != null) {
            for (Converter<?, ?> converter : converters) {
                registry.addConverter(converter);
            }
        }
    }
}
