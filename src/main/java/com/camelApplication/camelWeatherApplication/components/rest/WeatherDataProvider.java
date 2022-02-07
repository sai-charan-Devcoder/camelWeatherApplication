package com.camelApplication.camelWeatherApplication.components.rest;

import com.camelApplication.camelWeatherApplication.dto.WeatherDto;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class WeatherDataProvider {
    private static Map<String, WeatherDto> weatherData=new HashMap<>();

    public WeatherDataProvider()
    {
        WeatherDto dto=WeatherDto.builder().city("LONDON").temp("10").unit("C")
                .receivedTime(new Date().toString()).id(1).build();
        weatherData.put("LONDON", dto);
    }

    public WeatherDto getCurrentWeather(String city)
    {
        return weatherData.get(city.toUpperCase());
    }

    public void setCurrentWeather(WeatherDto dto)
    {
       dto.setReceivedTime(new Date().toString());
       weatherData.put(dto.getCity().toUpperCase(),dto);
    }

}
