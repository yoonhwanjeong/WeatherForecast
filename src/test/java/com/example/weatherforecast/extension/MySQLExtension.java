package com.example.weatherforecast.extension;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.MySQLContainer;

public class MySQLExtension implements BeforeAllCallback {
    private static final MySQLContainer<?> MYSQL_CONTAINER = new MySQLContainer<>("mysql:8.0.32")
            .withDatabaseName("weather_forecast")
            .withUsername("user")
            .withPassword("password")
            .withExposedPorts(3306)
            .withInitScript("weather_forecast.sql");

    @Override
    public void beforeAll(ExtensionContext context) {
        if (!MYSQL_CONTAINER.isRunning()) {
            MYSQL_CONTAINER.start();
        }
        System.setProperty("spring.datasource.url", MYSQL_CONTAINER.getJdbcUrl());
        System.setProperty("spring.datasource.username", MYSQL_CONTAINER.getUsername());
        System.setProperty("spring.datasource.password", MYSQL_CONTAINER.getPassword());
    }
}
