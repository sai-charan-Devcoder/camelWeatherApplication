package com.camelApplication.camelWeatherApplication.components.rest;

import com.camelApplication.camelWeatherApplication.dto.WeatherDto;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.support.DefaultMessage;
import org.springframework.stereotype.Component;

@Component
public class RestJavaDsl extends RouteBuilder {

    private final WeatherDataProvider weatherDataProvider;
    public RestJavaDsl(WeatherDataProvider weatherDataProvider) {
        this.weatherDataProvider = weatherDataProvider;
    }

    //http://localhost:8080/services/javadsl/weather/{city}
    //http://localhost:8080/services/javadsl/weather/london
    //http://localhost:8080/services/javadsl/weather/new york
    @Override
    public void configure() throws Exception {

        from("rest:get:javadsl/weather/{city}?produces=application/json")
                .outputType(WeatherDto.class)
                .process(this::getWeatherData);
    }

    private void getWeatherData(Exchange exchange) {
        String city = exchange.getMessage().getHeader("city", String.class);
        WeatherDto currentWeather = weatherDataProvider.getCurrentWeather(city);


            Message message = new DefaultMessage(exchange.getContext());
            message.setBody(currentWeather);
            exchange.setMessage(message);

    }

}
