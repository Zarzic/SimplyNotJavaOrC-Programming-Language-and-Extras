/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Boundary;

import Control.WeatherController;
import Entity.Weather;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDate;

/**
 *This class displays weather-related information, such as current conditions, forecasts, and other weather data
 * @author rschi
 */
public class WeatherScreen extends ApplicationGUI{
    public static void GetWeatherScreen(Stage stage, Scene main_menu) {
        Group grid = new Group();

        //adding rungs to the notebook
        int y = 15;
        for(int i = 0; i < 150;i++) {
            Circle c1 = new Circle(4,4,30);
            c1.setFill(Color.TRANSPARENT);
            c1.setStroke(Color.BLACK);
            c1.setStrokeWidth(8);
            c1.setCenterX(15);
            c1.setCenterY(y);
            grid.getChildren().add(c1);
            y = y + 15;
        }
        Button b_b_m = new Button("Back To Main Page");
        b_b_m.setLayoutX(150);
        b_b_m.setLayoutY(10);
        grid.getChildren().add(b_b_m);
        //person clicks the back button after looking at the weather
        b_b_m.setOnAction(e-> { 
            stage.setScene(main_menu);
            });
        //AREA TO DISPLAY THE WEATHER INFORMATION THAT i GET FROM THE CONTROLLER. I NEED THE CONTROLLER READY.
            Scene weather_page = new Scene(grid,600,800);
            weather_page.setFill(ApplicationGUI.c1);
            stage.setScene(weather_page);

        //EXAMPLE --- REMOVE
        Weather currentWeather = WeatherAPI.getWeather();

        Text title1 = new Text();
        title1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 48));
        title1.setFill(Color.CRIMSON);
        title1.setText(String.valueOf("Temperature: " + currentWeather.getTemperature() + "°F"));
        title1.setLayoutX(60);
        title1.setLayoutY(260);
        grid.getChildren().add(title1);

        Text title2 = new Text();
        title2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 48));
        title2.setFill(Color.AQUAMARINE);
        title2.setText(String.valueOf("Condition: " + currentWeather.getCondition()));
        title2.setLayoutX(100);
        title2.setLayoutY(360);
        grid.getChildren().add(title2);

        Text title3 = new Text();
        title3.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 48));
        title3.setFill(Color.GRAY);
        title3.setText(String.valueOf(LocalDate.now()));
        title3.setLayoutX(60);
        title3.setLayoutY(760);
        grid.getChildren().add(title3);

        Text title4 = new Text();
        title4.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 48));
        title4.setFill(Color.GRAY);
        title4.setText(String.valueOf("Cape Girardeau,\nMissouri"));
        title4.setLayoutX(60);
        title4.setLayoutY(560);
        grid.getChildren().add(title4);
    }
}
