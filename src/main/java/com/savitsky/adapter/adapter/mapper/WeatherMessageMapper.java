package com.savitsky.adapter.adapter.mapper;

import com.savitsky.adapter.adapter.dto.WeatherMessageDto;
import com.savitsky.adapter.model.EnrichedWeatherMessage;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WeatherMessageMapper {

    public static List<WeatherMessageDto> toDto(List<EnrichedWeatherMessage> messages) {
        return messages.stream()
                .map(message ->  WeatherMessageDto.builder()
                                .txt(message.getMessage())
                                .createdDt(message.getCreatedDate())
                                .currentTemp(message.getTemperature())
                                .build())
                .toList();
    }
}
