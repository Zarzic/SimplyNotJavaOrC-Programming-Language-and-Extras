/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Boundary;

import Entity.Weather;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This class acts as an interface to external weather data sources or services. It communicates with 
 * the Weather Controller to fetch and provide real-time weather information for display on the Weather Screen
 *
 * @author rschi
 */
public class WeatherAPI {
    public static Weather getWeather() {
        String apikey = "7490a2568971b20bf108357eded5fcc7";
        String city = "Cape Girardeau";
        Weather currentWeather = null;

        try {
            String apiHost = "http://api.weatherstack.com/current?access_key=" + apikey + "&query=" + city +"&units=f";
            URL url = new URL(apiHost);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            String jasonPackage = response.toString();

            int temperature = Integer.parseInt(jasonPackage.split("\"temperature\":")[1].split(",")[0]);
            String condition = jasonPackage.split("\"weather_descriptions\":\\[\"")[1].split("\"")[0];

            currentWeather = new Weather(temperature, condition);

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return currentWeather;
    }
}
