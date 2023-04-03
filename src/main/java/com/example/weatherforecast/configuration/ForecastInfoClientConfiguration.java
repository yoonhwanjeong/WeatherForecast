package com.example.weatherforecast.configuration;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 설정 파일에서 기상청 Open API에 필요한 serviceKey를 요청 변수에 넣는 Feign 클라이어트 Configuration
 */
@Configuration
public class ForecastInfoClientConfiguration {
    @Value("${serviceKey}")
    private String serviceKey;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> template.query("serviceKey", serviceKey).query("dataType", "JSON");
    }
}
