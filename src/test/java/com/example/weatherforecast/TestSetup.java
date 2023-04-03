package com.example.weatherforecast;

import com.example.weatherforecast.extension.MySQLExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest
@AutoConfigureWireMock(port = 0)
@ExtendWith(MySQLExtension.class)
public @interface TestSetup {
}
