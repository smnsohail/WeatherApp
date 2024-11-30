package com.MyWeatherApp.firstApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MyWeatherApp.firstApp.model.WeatherData;
import com.MyWeatherApp.firstApp.service.WeatherService;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/{city}")

    public WeatherData getWeather (@PathVariable String city){
        return weatherService.getWeather(city);
    }
    
}
