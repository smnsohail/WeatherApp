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
        RestTemplate restTemplate = new RestTemplate(); // RestTemplate makes the HTTP GET request, and we extract the
                                                        // relevant fields like city name, temperature, and description
                                                        // from the JSON response.
        String url = String.format("%s?q=%s&appid=%s&units=metric", BASE_URL, city, API_KEY);

        Map<String, Object> response = restTemplate.getForObject(url, Map.class); // Sends an HTTP GET request to the
                                                                                  // constructed URL and parses the JSON
                                                                                  // response into a Map. Why Map? The
                                                                                  // API returns a nested JSON response.
                                                                                  // Using a Map makes it easier to
                                                                                  // extract key-value pairs.

                                                                                  
        // CREATING INSTANCE OF MODEL CLASS (WEARTHERDATA CLASS)

        WeatherData weatherData = new WeatherData();
        weatherData.setCityName(response.get("name").toString());

        // Get temperature info
        Map<String, Object> main = (Map<String, Object>) response.get("main");
        weatherData.setTemperature(Double.parseDouble(main.get("temp").toString()));

        // Get weather description
        List<Map<String, Object>> weatherArray = (List<Map<String, Object>>) response.get("weather");
        weatherData.setDescription(weatherArray.get(0).get("description").toString());

        return weatherData;
    }

}
