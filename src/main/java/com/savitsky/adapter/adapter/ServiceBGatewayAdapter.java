package com.savitsky.adapter.adapter;

import com.savitsky.adapter.adapter.mapper.WeatherMessageMapper;
import com.savitsky.adapter.model.EnrichedWeatherMessage;
import com.savitsky.adapter.service.external.ServiceBGateway;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.util.List;

@Slf4j
public class ServiceBGatewayAdapter implements ServiceBGateway {

    private static final int READ_TIMEOUT_IN_SECONDS = 10;
    private static final int WRITE_TIMEOUT_IN_SECONDS = 10;

    private final WebClient webClient;

    public ServiceBGatewayAdapter(String serviceBUrl) {
        HttpClient httpClient = HttpClient.create()
                .doOnConnected(conn -> conn
                        .addHandlerLast(new ReadTimeoutHandler(READ_TIMEOUT_IN_SECONDS))
                        .addHandlerLast(new WriteTimeoutHandler(WRITE_TIMEOUT_IN_SECONDS)));
        this.webClient = WebClient.builder()
                .baseUrl(serviceBUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    @Override
    public void send(List<EnrichedWeatherMessage> enrichedWeatherMessages) {
        log.info("Sending filled weather data to ServiceB");

        webClient.post()
                .bodyValue(WeatherMessageMapper.toDto(enrichedWeatherMessages))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {})
                .block();
    }
}
