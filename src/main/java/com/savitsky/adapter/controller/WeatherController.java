package com.savitsky.adapter.controller;

import com.savitsky.adapter.controller.mapper.MessageDtoMapper;
import com.savitsky.adapter.dto.AdapterResponse;
import com.savitsky.adapter.dto.MessageDto;
import com.savitsky.adapter.service.WeatherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/weather/messages")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @PostMapping
    public AdapterResponse<String> fillMessage(@Valid @RequestBody List<MessageDto> messages) {
        weatherService.fillMessage(MessageDtoMapper.toFillMessageRequests(messages));
        return AdapterResponse.ok("Weather data successfully filled and proxied");
    }
}
