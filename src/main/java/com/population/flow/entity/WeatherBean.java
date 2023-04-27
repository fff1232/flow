package com.population.flow.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class WeatherBean {
    private String weather;
    private String wind_direction;
    private String area;
    private String wind_power;
    private String temperature;
}
