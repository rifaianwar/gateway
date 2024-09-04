package com.r3s.gateway.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Configuration
@ConfigurationProperties("app")
public class AppProperties {

    private String SECRET_KEY = "";
    private String WHITE_LIST_URL = "";
}
