package Boundary;

import Control.ItemController;
import Control.NoteController;
import Control.ReminderController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SettingsScreen extends ApplicationGUI{

    public static void GetSettingScreen(Stage stage, Scene main_menu) {
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
        Button sb1 = new Button("Change Notebook to Light Yellow");
        Button sb2 = new Button("Change Notebook to Light Green");
        Button sb4 = new Button("Change Notebook to Light Blue");
        Button sb3 = new Button("Clear Entire Notebook");
        VBox centering = new VBox(10);
        centering.setLayoutX(220);
        centering.setLayoutY(100);
        centering.getChildren().addAll(sb1,sb2,sb4,sb3,b_b_m);
        grid.getChildren().add(centering);
        //person clicks the back button after looking at the weather
        b_b_m.setOnAction(e-> { 
            stage.setScene(main_menu);
            });
        sb3.setOnAction(e-> { 
            ItemController.deleteAllItems();
            NoteController.deleteNote();
            ReminderController.deleteAllItems();
            });
        sb2.setOnAction(e-> { 
            ApplicationGUI.c1 = Color.LIGHTGREEN;
            });
        sb1.setOnAction(e-> { 
            ApplicationGUI.c1 = Color.LIGHTYELLOW;
            });
        sb4.setOnAction(e-> { 
            ApplicationGUI.c1 = Color.LIGHTBLUE;
            });
        Scene weather_page = new Scene(grid,600,800);
        Duration duration = Duration.seconds(0.01);
        Timeline timeline = new Timeline(new KeyFrame(duration, event -> {
            // Change the background color
            weather_page.setFill(ApplicationGUI.c1);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        stage.setScene(weather_page);
    }
}
