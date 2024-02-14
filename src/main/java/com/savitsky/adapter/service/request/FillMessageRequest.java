package com.savitsky.adapter.service.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
public record FillMessageRequest(String message, String languageCode,
                                 CoordinatesRequest coordinates) {

    @Getter
    @AllArgsConstructor
    public static class CoordinatesRequest {
        private Double latitude;
        private Double longitude;
    }
}
