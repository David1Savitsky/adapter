package com.savitsky.adapter.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OpenWeatherMapResponse {

    private Main main;
    private long dt;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Main {
        private Double temp;
    }
}
