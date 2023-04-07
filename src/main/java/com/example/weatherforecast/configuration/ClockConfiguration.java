package com.example.weatherforecast.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.time.ZoneId;

/**
 * 테스트 할 때 현재 시간을 고정된 시간으로 설정할 수 있도록 Clock을 Bean으로 등록하는 Configuration
 */
@Configuration
public class ClockConfiguration {
    @Bean
    public Clock clock() {
        return Clock.system(ZoneId.of("+9"));
    }
}
