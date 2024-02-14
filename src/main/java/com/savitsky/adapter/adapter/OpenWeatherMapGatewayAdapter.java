package com.savitsky.adapter.adapter;

import com.savitsky.adapter.adapter.dto.OpenWeatherMapResponse;
import com.savitsky.adapter.exception.OpenWeatherMapException;
import com.savitsky.adapter.adapter.mapper.ExtraWeatherMapper;
import com.savitsky.adapter.model.ExtraWeatherData;
import com.savitsky.adapter.service.external.WeatherGateway;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.netty.http.client.HttpClient;

@Slf4j
public class OpenWeatherMapGatewayAdapter implements WeatherGateway {

    private static final String LATITUDE_QUERY_PARAM_NAME = "lat";
    private static final String LONGITUDE_QUERY_PARAM_NAME = "lon";
    private static final String APP_ID_QUERY_PARAM_NAME = "appid";

    private static final int READ_TIMEOUT_IN_SECONDS = 10;
    private static final int WRITE_TIMEOUT_IN_SECONDS = 10;

    private final WebClient webClient;
    private final String apiKey;

    public OpenWeatherMapGatewayAdapter(String openWeatherMapUrl, String apiKey) {
        HttpClient httpClient = HttpClient.create()
                .doOnConnected(conn -> conn
                        .addHandlerLast(new ReadTimeoutHandler(READ_TIMEOUT_IN_SECONDS))
                        .addHandlerLast(new WriteTimeoutHandler(WRITE_TIMEOUT_IN_SECONDS)));
        this.webClient = WebClient.builder()
                .baseUrl(openWeatherMapUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
        this.apiKey = apiKey;
    }

    @Override
    public ExtraWeatherData getExtraWeatherData(Double latitude, Double longitude) {
        log.info("Getting temperature from OpenWeatherMap");

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam(LATITUDE_QUERY_PARAM_NAME, latitude)
                        .queryParam(LONGITUDE_QUERY_PARAM_NAME, longitude)
                        .queryParam(APP_ID_QUERY_PARAM_NAME, apiKey)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<OpenWeatherMapResponse>() {})
                .onErrorMap(WebClientResponseException.class, this::createOpenWeatherMapException)
                .map(ExtraWeatherMapper::toExtraWeatherData)
                .block();
    }

    private Throwable createOpenWeatherMapException(WebClientResponseException e) {
        return new OpenWeatherMapException("Interaction Error with OpenWeatherMap service.");
    }
}
