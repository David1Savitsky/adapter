package com.savitsky.adapter.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("open-weather-map")
public class OpenWeatherMapConfig {
    private String url;
    private String apiKey;
}
