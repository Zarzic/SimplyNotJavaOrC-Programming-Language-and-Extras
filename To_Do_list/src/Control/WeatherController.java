/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Entity.Weather;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This class controls and manages the flow of weather-related tasks and data between the Weather Screen and the Weather API
 * @author rschi
 */
public class WeatherController {
    private Weather weather;

    public Weather getWeather() {
        return this.weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }
}
