package com.savitsky.adapter.controller.mapper;

import com.savitsky.adapter.dto.MessageDto;
import com.savitsky.adapter.service.request.FillMessageRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageDtoMapper {

    public static List<FillMessageRequest> toFillMessageRequests(List<MessageDto> messageDtos) {
        return messageDtos.stream()
                .map(messageDto -> FillMessageRequest.builder()
                        .message(messageDto.message())
                        .languageCode(messageDto.languageCode())
                        .coordinates(new FillMessageRequest.CoordinatesRequest(
                                    messageDto.coordinates().getLatitude(),
                                    messageDto.coordinates().getLongitude()))
                        .build())
                .toList();
    }
}
