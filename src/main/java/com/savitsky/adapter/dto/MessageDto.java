package com.savitsky.adapter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

public record MessageDto(@NotNull @JsonProperty("msg") String message, @NotNull @JsonProperty("lng") String languageCode,
                         @NotNull @JsonProperty("coordinates") MessageDto.Coordinates coordinates) {

    @Getter
    @AllArgsConstructor
    public static class Coordinates {
        @NotNull
        private Double latitude;
        @NotNull
        private Double longitude;
    }
}
