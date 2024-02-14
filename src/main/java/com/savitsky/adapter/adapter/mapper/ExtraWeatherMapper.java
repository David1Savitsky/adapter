package com.savitsky.adapter.adapter.mapper;

import com.savitsky.adapter.adapter.dto.OpenWeatherMapResponse;
import com.savitsky.adapter.model.ExtraWeatherData;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExtraWeatherMapper {

    public static ExtraWeatherData toExtraWeatherData(OpenWeatherMapResponse response) {
        var createdDate = LocalDateTime.ofInstant(Instant.ofEpochSecond(response.getDt()), ZoneOffset.UTC);
        return new ExtraWeatherData(response.getMain().getTemp(), createdDate);
    }
}
