package com.savitsky.adapter.adapter.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class WeatherMessageDto {

    private final String txt;
    private final LocalDateTime createdDt;
    private final Double currentTemp;
}
