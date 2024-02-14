package com.savitsky.adapter.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ExtraWeatherData {

    private Double temperature;
    private LocalDateTime createdDate;
}
