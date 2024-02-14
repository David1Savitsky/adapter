package com.savitsky.adapter.service.external;

import com.savitsky.adapter.model.ExtraWeatherData;

public interface WeatherGateway {

    ExtraWeatherData getExtraWeatherData(Double latitude, Double longitude);
}
