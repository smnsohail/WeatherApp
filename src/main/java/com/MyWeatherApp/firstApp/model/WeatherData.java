//MODEL CLASS FILE

package com.MyWeatherApp.firstApp.model;

public class WeatherData {
    
    private String cityName;
    private double temperature;
    private String description;

    // generate getters and setters for all above instance fileds to get access
    public String getCityName() {
        return cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public double getTemperature() {
        return temperature;
    }
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
}
