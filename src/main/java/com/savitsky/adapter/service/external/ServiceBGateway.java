package com.savitsky.adapter.service.external;

import com.savitsky.adapter.model.EnrichedWeatherMessage;

import java.util.List;

public interface ServiceBGateway {

    void send(List<EnrichedWeatherMessage> enrichedWeatherMessages);
}
