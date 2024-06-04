package Boundary;

import Control.ItemController;
import Control.NoteController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.time.*;
import java.util.ArrayList;

import javafx.util.Duration;


/**
 * This class manages the user interface of the application, allowing users to interact with 
 * and view items, notes, tasks, and other information
 *
 * @author rschi
 */
public class ApplicationGUI extends Application {
    static Color c1;
    @Override
    public void start(Stage stage) {
        
        Group grid = new Group();
        //adding the rungs
        int y = 15;
        for(int i = 0; i < 150;i++) {
            Circle c2 = new Circle(4,4,30);
            c2.setFill(Color.TRANSPARENT);
            c2.setStroke(Color.BLACK);
            c2.setStrokeWidth(8);
            c2.setCenterX(15);
            c2.setCenterY(y);
            grid.getChildren().add(c2);
            y = y + 15;
        }
        //adding the buttons
        Button A_e_tb = new Button("Add/Edit Task");
        Button wb = new Button("Weather");
        Button A_e_rb = new Button("Add/Edit Reminders");
        Button sb = new Button("Settings");
        Button A_e_nb = new Button("Add/Edit Notes"); 
        A_e_nb.setLayoutX(50);
        A_e_nb.setLayoutY(770);
        HBox btnHB = new HBox(10);
        btnHB.getChildren().addAll(A_e_rb,A_e_tb,sb,wb);
        btnHB.setLayoutX(50);
        btnHB.setLayoutY(500);
        //adding to the plane of the scene
        grid.getChildren().add(btnHB);
        grid.getChildren().add(A_e_nb);
        //add the time that updates for the main menu only
        Text timeText = new Text();
        timeText.setFill(Color.CRIMSON);
        timeText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        timeText.setLayoutX(420);
        timeText.setLayoutY(30);
        grid.getChildren().add(timeText);
        // Create a Timeline to update the time every second
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> updateTime(timeText))
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        //add date using the simple LocalDate class from java the date_controller is for the reminders to use
        Text date_main = new Text(LocalDate.now().toString());
        date_main.setLayoutX(440);
        date_main.setLayoutY(60);
        grid.getChildren().add(date_main);

        Timeline alertTimeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> AlertScreen.showAlertIfTimePassed())
        );
        alertTimeline.setCycleCount(Timeline.INDEFINITE);
        alertTimeline.play();

        //add border lines to the notebook around the date and time
        Line b1 = new Line();
        b1.setStartX(420);
        b1.setStartY(0);
        b1.setEndX(420);
        b1.setEndY(65);
        Line b2 = new Line();
        b2.setStartX(420);
        b2.setStartY(65);
        b2.setEndX(600);
        b2.setEndY(65);

        grid.getChildren().addAll(b1,b2);

        // Items
        Text[] items = new Text[14];
        for (int i = 0; i < 14; i++) {
            Text textField = new Text();
            items[i] = textField;
        }

        //add lines for the items
        //we can have 14 lines of items
        int line_y = 100;
        for (int i = 0; i < 14; i++) {
            Line l1 = new Line();
            l1.setStartX(70);
            l1.setStartY(line_y);
            l1.setEndX(550);
            l1.setEndY(line_y);
            grid.getChildren().add(l1);

            items[i].setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 16));
            items[i].setLayoutX(70);
            items[i].setLayoutY(line_y - 1);
            grid.getChildren().add(items[i]);

            Timeline itemTimeline = new Timeline(
                    new KeyFrame(Duration.seconds(1), event -> updateItems(items))
            );
            itemTimeline.setCycleCount(Timeline.INDEFINITE);
            itemTimeline.play();

            line_y = line_y + 30;
        }

        // Notes
        Label note = new Label();
        note.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 16));
        note.setLayoutX(70);
        note.setLayoutY(530);
        note.setMaxWidth(500);
        note.setWrapText(true);
        note.setLineSpacing(10);
        grid.getChildren().add(note);

        //add lines for the notes
        int line_y2 = 550;
        for (int i = 0; i < 8; i++) {
            Line l1 = new Line();
            l1.setStartX(70);
            l1.setStartY(line_y2);
            l1.setEndX(550);
            l1.setEndY(line_y2);
            grid.getChildren().add(l1);

            Timeline itemTimeline = new Timeline(
                    new KeyFrame(Duration.seconds(1), event -> updateNote(note))
            );
            itemTimeline.setCycleCount(Timeline.INDEFINITE);
            itemTimeline.play();

            line_y2 = line_y2 + 30;
        }

        //add big title
        Text title1 = new Text();
        title1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 60));
        title1.setFill(Color.CRIMSON);
        title1.setText("To-Do-List");
        title1.setLayoutX(50);
        title1.setLayoutY(50);
        grid.getChildren().add(title1);
        //Delcare the main menu scene
        Scene main_menu = new Scene(grid,600,800);
        Duration duration = Duration.seconds(0.01);
        Timeline t2 = new Timeline(new KeyFrame(duration, event -> {
            // Change the background color
            main_menu.setFill(c1);
        }));
        t2.setCycleCount(Timeline.INDEFINITE);
        t2.play();
        stage.setScene(main_menu);
        stage.setTitle("Main Menu");
        stage.setResizable(false);
        //go and display the other screens
        A_e_tb.setOnAction(e-> { 
           //open new scene on the stage
           //have to make sure that the controllers and enitites actually update the main menu here!!
           //That is why we pass the main_menu scene as well to the item screen
           ItemScreen.GetItemScreen(stage, main_menu);
           });
        wb.setOnAction(e-> { 
           //open new scene on the stage
           WeatherScreen.GetWeatherScreen(stage, main_menu);
           });
        A_e_rb.setOnAction(e-> { 
           //open new scene on the stage
           ReminderScreen.GetReminderScreen(stage, main_menu);
           });
        sb.setOnAction(e-> { 
           //open new scene on the stage
           SettingsScreen.GetSettingScreen(stage, main_menu);
           });
        A_e_nb.setOnAction(e-> { 
           //open new scene on the stage
           NoteScreen.GetNoteScreen(stage,main_menu);
           });
        stage.show();
    }

    private void updateTime(Text timeText) {
        LocalTime time = LocalTime.now();
        int check = time.getHour();
        String min = String.format("%02d", time.getMinute());

        if (check > 12) {
            check = check - 12;
            timeText.setText(Integer.toString(check) + " : " + min + " PM");
        } else {
            timeText.setText(Integer.toString(check) + " : " + min + " AM");
        }
    }

    private void updateItems(Text[] items) {
        for (int i = 0; i < 14; i++) {
            ArrayList<String> item = ItemController.getItem(i);
            if (!item.isEmpty()) {
                items[i].setText(i + 1 + ". " + item.get(0) + ": " + item.get(1));
            } else {
                items[i].setText("");
            }
        }
    }

    private void updateNote(Label note) {
        for (int i = 0; i < 8; i++) {
            String noteText = NoteController.getNote();
            if (!noteText.isEmpty()) {
                note.setText(noteText);
            } else {
                note.setText("");
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }

}
