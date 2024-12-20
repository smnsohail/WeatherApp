package com.MyWeatherApp.firstApp.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.MyWeatherApp.firstApp.model.WeatherData;

@Service
public class WeatherService {
    private static final String API_KEY = "d5f5e1fd5f387dbadecdf52db4b7bc0e"; // my api key
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";

    public WeatherData getWeather(String city) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("%s?q=%s&appid=%s&units=metric", BASE_URL, city, API_KEY);
    
        try {
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);
            
            if (response == null || !response.containsKey("main") || !response.containsKey("weather")) {
                throw new RuntimeException("Invalid response from weather service");
            }
    
            WeatherData weatherData = new WeatherData();
            weatherData.setCityName(response.get("name").toString());
    
            //these tempr, humid, feelsLike are mapped in main(single map) as they belong to same json object in api response
            Map<String, Object> main = (Map<String, Object>) response.get("main");
            weatherData.setTemperature(Double.parseDouble(main.get("temp").toString()));
            weatherData.setHumidity(Double.parseDouble(main.get("humidity").toString()));
            weatherData.setFeelsLike(Double.parseDouble(main.get("feels_like").toString()));
    
            Map<String, Object> wind = (Map<String, Object>) response.get("wind");
            weatherData.setWindSpeed(Double.parseDouble(wind.get("speed").toString()));

            List<Map<String, Object>> weatherArray = (List<Map<String, Object>>) response.get("weather");
            weatherData.setDescription(weatherArray.get(0).get("description").toString());
    
            return weatherData;
        } catch (Exception e) {
            // Log the error and handle it appropriately
            throw new RuntimeException("Error fetching weather data", e);
        }
    }

}
