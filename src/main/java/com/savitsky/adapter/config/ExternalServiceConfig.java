package com.savitsky.adapter.config;

import com.savitsky.adapter.adapter.ServiceBGatewayAdapter;
import com.savitsky.adapter.adapter.OpenWeatherMapGatewayAdapter;
import com.savitsky.adapter.service.external.ServiceBGateway;
import com.savitsky.adapter.service.external.WeatherGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExternalServiceConfig {

    @Bean
    ServiceBGateway serviceBGateway(ServiceBConfig config) {
        return new ServiceBGatewayAdapter(config.getUrl());
    }

    @Bean
    WeatherGateway weatherGateway(OpenWeatherMapConfig config) {
        return new OpenWeatherMapGatewayAdapter(config.getUrl(), config.getApiKey());
    }
}
