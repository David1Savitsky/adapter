package com.savitsky.adapter.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class EnrichedWeatherMessage {

    private String message;
    private LocalDateTime createdDate;
    private Double temperature;
}
