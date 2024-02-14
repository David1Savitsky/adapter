package com.savitsky.adapter.service;

import com.savitsky.adapter.model.EnrichedWeatherMessage;
import com.savitsky.adapter.service.external.ServiceBGateway;
import com.savitsky.adapter.service.external.WeatherGateway;
import com.savitsky.adapter.service.request.FillMessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherGateway weatherGateway;
    private final ServiceBGateway serviceBGateway;
    private final MessageFilterService filterService;

    public void fillMessage(List<FillMessageRequest> fillMessageRequests) {
        var filteredMessageRequests = filterService.filter(fillMessageRequests);
        var enrichedWeatherMessages = filteredMessageRequests.stream()
                .map(request -> {
                    var extraWeatherData = weatherGateway.getExtraWeatherData(  // Should be bulk get
                            request.coordinates().getLatitude(),
                            request.coordinates().getLongitude());
                    return EnrichedWeatherMessage.builder()
                            .message(request.message())
                            .temperature(extraWeatherData.getTemperature())
                            .createdDate(extraWeatherData.getCreatedDate())
                            .build();
                })
                .toList();
        serviceBGateway.send(enrichedWeatherMessages);
    }
}
