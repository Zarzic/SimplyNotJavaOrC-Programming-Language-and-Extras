/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 * This class represents the core functionality of the weather application, which includes retrieving and 
 * processing weather data from external sources or APIs and providing it to the Weather Screen for display
 *
 * @author rschi
 */
public class Weather {
    private int temperature;
    private String condition;

    public Weather(int temperature, String condition) {
        this.temperature = temperature;
        this.condition = condition;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
