/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Boundary;

import Control.ItemController;
import Control.ReminderController;
import Entity.Reminder;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *This class displays reminders and facilitates the creation, editing, and deletion of reminders
 *
 * @author rschi
 */
public class ReminderScreen extends ApplicationGUI{
    private ReminderController reminderController;

    public static void GetReminderScreen(Stage stage, Scene main_menu) {
        Group grid = new Group();
        //adding the rungs
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

        //add 22 lines here
        Line l1 = new Line();
        l1.setStartX(70);
        l1.setStartY(100);
        l1.setEndX(550);
        l1.setEndY(100);
        Line l2 = new Line();
        l2.setStartX(70);
        l2.setStartY(130);
        l2.setEndX(550);
        l2.setEndY(130);
        Line l3= new Line();
        l3.setStartX(70);
        l3.setStartY(160);
        l3.setEndX(550);
        l3.setEndY(160);
        Line l4 = new Line();
        l4.setStartX(70);
        l4.setStartY(190);
        l4.setEndX(550);
        l4.setEndY(190);
        Line l5 = new Line();
        l5.setStartX(70);
        l5.setStartY(220);
        l5.setEndX(550);
        l5.setEndY(220);
        Line l6 = new Line();
        l6.setStartX(70);
        l6.setStartY(250);
        l6.setEndX(550);
        l6.setEndY(250);
        Line l7 = new Line();
        l7.setStartX(70);
        l7.setStartY(280);
        l7.setEndX(550);
        l7.setEndY(280);
        Line l8 = new Line();
        l8.setStartX(70);
        l8.setStartY(310);
        l8.setEndX(550);
        l8.setEndY(310);
        Line l9 = new Line();
        l9.setStartX(70);
        l9.setStartY(340);
        l9.setEndX(550);
        l9.setEndY(340);
        Line l10 = new Line();
        l10.setStartX(70);
        l10.setStartY(370);
        l10.setEndX(550);
        l10.setEndY(370);
        Line l11 = new Line();
        l11.setStartX(70);
        l11.setStartY(400);
        l11.setEndX(550);
        l11.setEndY(400);
        Line l12 = new Line();
        l12.setStartX(70);
        l12.setStartY(430);
        l12.setEndX(550);
        l12.setEndY(430);
        Line l13 = new Line();
        l13.setStartX(70);
        l13.setStartY(460);
        l13.setEndX(550);
        l13.setEndY(460);
        Line l14 = new Line();
        l14.setStartX(70);
        l14.setStartY(490);
        l14.setEndX(550);
        l14.setEndY(490);
        Line l15 = new Line();
        l15.setStartX(70);
        l15.setStartY(520);
        l15.setEndX(550);
        l15.setEndY(520);
        Line l16 = new Line();
        l16.setStartX(70);
        l16.setStartY(550);
        l16.setEndX(550);
        l16.setEndY(550);
        Line l17 = new Line();
        l17.setStartX(70);
        l17.setStartY(580);
        l17.setEndX(550);
        l17.setEndY(580);
        Line l18 = new Line();
        l18.setStartX(70);
        l18.setStartY(610);
        l18.setEndX(550);
        l18.setEndY(610);
        Line l19 = new Line();
        l19.setStartX(70);
        l19.setStartY(640);
        l19.setEndX(550);
        l19.setEndY(640);
        Line l20 = new Line();
        l20.setStartX(70);
        l20.setStartY(670);
        l20.setEndX(550);
        l20.setEndY(670);
        Line l21 = new Line();
        l21.setStartX(70);
        l21.setStartY(700);
        l21.setEndX(550);
        l21.setEndY(700);
        Line l22 = new Line();
        l22.setStartX(70);
        l22.setStartY(730);
        l22.setEndX(550);
        l22.setEndY(730);
        Line l23 = new Line();
        l23.setStartX(70);
        l23.setStartY(760);
        l23.setEndX(550);
        l23.setEndY(760);
        grid.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,l17,l18,l19,l20,l21,l22,l23);
        //add listener here that updates any time the reminder entity class updates so that we can
        //print the reminders on the screen here as well

        // Reminders
        Text[] reminders = new Text[22];
        for (int i = 0; i < 22; i++) {
            Text textField = new Text();
            reminders[i] = textField;
        }

        int line_y = 100;
        for (int i = 0; i < 22; i++) {
            reminders[i].setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 16));
            reminders[i].setLayoutX(70);
            reminders[i].setLayoutY(line_y - 1);
            grid.getChildren().add(reminders[i]);

            Timeline itemTimeline = new Timeline(
                    new KeyFrame(Duration.seconds(1), event -> updateReminders(reminders))
            );
            itemTimeline.setCycleCount(Timeline.INDEFINITE);
            itemTimeline.play();

            line_y = line_y + 30;
        }

        //add title
        Text title1 = new Text();
        title1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        title1.setFill(Color.CRIMSON);
        title1.setText("Reminders");
        title1.setLayoutX(100);
        title1.setLayoutY(60);
        grid.getChildren().add(title1);
        //add the buttons
        Button b_b_m = new Button("Back To Main Page");
        Button ai = new Button("Add Reminder");
        Button ei = new Button("Edit Reminder");
        Button di = new Button("Delete Reminder");
        //evenly space out the buttons
        HBox btnHB = new HBox(10);
        btnHB.getChildren().addAll(ai,ei,di,b_b_m);
        btnHB.setLayoutX(100);
        btnHB.setLayoutY(10);
        //adding to the plane of the scene
        grid.getChildren().add(btnHB);
        b_b_m.setOnAction(e-> { 
            stage.setScene(main_menu);
            });
        //adding a item button below
        ai.setOnAction(e-> {
            //get the number already ready on previous amount entered
            grid.getChildren().removeAll(l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,l17,l18,l19,l20,l21,l22,l23);
            grid.getChildren().remove(btnHB);
            grid.getChildren().remove(title1);
            for(int i = 0; i<reminders.length; i++) {
                grid.getChildren().remove(reminders[i]);
            }
            TextArea input = new TextArea();
            TextArea time_date = new TextArea();
            Text r = new Text("Enter Reminder Below:");
            Text rtl = new Text("Enter Reminder Time and Date Below (MM/DD/YYYY (Space) TT:TT PM or AM):");
            Button confirm = new Button("Confirm");
            Button back = new Button("Back");
            input.setLayoutX(150);
            input.setLayoutY(50);
            input.setPrefWidth(400);
            input.setPrefHeight(400);

            time_date.setLayoutX(150);
            time_date.setLayoutY(470);
            time_date.setPrefWidth(400);
            time_date.setPrefHeight(2);

            r.setLayoutX(150);
            r.setLayoutY(30);

            rtl.setLayoutX(150);
            rtl.setLayoutY(465);
            HBox centering = new HBox(10);
            centering.setLayoutX(150);
            centering.setLayoutY(520);
            centering.getChildren().addAll(confirm,back);
            grid.getChildren().addAll(input,centering,r,time_date,rtl);
            confirm.setOnAction(b-> {
                    //here we do the controller and apply to the reminder screen along with a number that says what item number it is!!
                    //make sure to double check if there is a note added or not
                    String reminder = input.getText();
                    String dt = time_date.getText();
                    ReminderController.addItem(reminder, dt);
                    grid.getChildren().removeAll(input,centering,r,time_date,rtl);
                    grid.getChildren().add(btnHB);
                    grid.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,l17,l18,l19,l20,l21,l22,l23);
                    grid.getChildren().add(title1);
                    for(int i = 0; i<reminders.length; i++) {
                        grid.getChildren().add(reminders[i]);
                    }
                });
            back.setOnAction(c-> {
                    //there is no processing just getting back to the other page
                    grid.getChildren().removeAll(input,centering,r,time_date,rtl);
                    grid.getChildren().add(btnHB);
                    grid.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,l17,l18,l19,l20,l21,l22,l23);
                    grid.getChildren().add(title1);
                    for(int i = 0; i<reminders.length; i++) {
                        grid.getChildren().add(reminders[i]);
                    }
                });
            });
        //edit item button below
        ei.setOnAction(e-> {
            //get the number already ready on previous amount entered
            grid.getChildren().removeAll(l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,l17,l18,l19,l20,l21,l22,l23);
            grid.getChildren().remove(btnHB);
            grid.getChildren().remove(title1);
            for(int i = 0; i<reminders.length; i++) {
                grid.getChildren().remove(reminders[i]);
            }
            Text number = new Text("Enter Number of Reminder Below:");
            Text item = new Text("Change Reminder Below:");
            TextArea ion = new TextArea();
            TextArea ie = new TextArea();
            TextArea ine = new TextArea();
            Text note_label = new Text("Change Time and Date for Reminder Below (MM/DD/YYYY (Space) TT:TT PM or AM):");
            Button confirm = new Button("Confirm Changes");
            Button cn = new Button("Confirm Number");
            Button back = new Button("Back");

            cn.setLayoutX(150);
            cn.setLayoutY(100);

            ion.setLayoutX(150);
            ion.setLayoutY(60);
            ion.setPrefWidth(400);
            ion.setPrefHeight(2);

            ie.setLayoutX(150);
            ie.setLayoutY(150);
            ie.setPrefWidth(400);
            ie.setPrefHeight(300);

            ine.setLayoutX(150);
            ine.setLayoutY(500);
            ine.setPrefWidth(400);
            ine.setPrefHeight(3);
            
            note_label.setLayoutX(150);
            note_label.setLayoutY(480);

            number.setLayoutX(150);
            number.setLayoutY(50);

            item.setLayoutX(150);
            item.setLayoutY(140);
            HBox centering = new HBox(10);
            centering.setLayoutX(150);
            centering.setLayoutY(540);
            centering.getChildren().addAll(confirm,back);
            grid.getChildren().addAll(ion,ie,centering,ine,note_label,item,number,cn);
            AtomicInteger n_change = new AtomicInteger();
            cn.setOnAction(m-> {
                    //make sure to get items and fill the Text area from the entities class here
                    n_change.set(Integer.parseInt(ion.getText()) - 1);
                    ArrayList<String> rdt = new ArrayList<>();
                    rdt = ReminderController.getItem(n_change.get());
                    ie.appendText(rdt.get(0));
                    ine.appendText(rdt.get(1));
                });
            confirm.setOnAction(b-> {
                    //here we do the controller and apply to the reminder screen along with a number that says what item number it is and sussefully edit it.
                    //make sure to double check if there is a note added or not
                    String reminder = ie.getText();
                    String dt = ine.getText();
                    ReminderController.setItem(reminder, dt, n_change.get());
                    grid.getChildren().removeAll(ion,ie,centering,ine,note_label,item,number,cn);
                    grid.getChildren().add(btnHB);
                    grid.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,l17,l18,l19,l20,l21,l22,l23);
                    grid.getChildren().add(title1);
                    for(int i = 0; i<reminders.length; i++) {
                        grid.getChildren().add(reminders[i]);
                    }
                });
            back.setOnAction(c-> {
                    //there is no processing just getting back to the other page
                    grid.getChildren().removeAll(ion,ie,centering,ine,note_label,item,number,cn);
                    grid.getChildren().add(btnHB);
                    grid.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,l17,l18,l19,l20,l21,l22,l23);
                    grid.getChildren().add(title1);
                    for(int i = 0; i<reminders.length; i++) {
                        grid.getChildren().add(reminders[i]);
                    }
                });
            });
        di.setOnAction(e-> {
            grid.getChildren().removeAll(l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,l17,l18,l19,l20,l21,l22,l23);
            for(int i = 0; i<reminders.length; i++) {
                grid.getChildren().remove(reminders[i]);
            }
            grid.getChildren().remove(btnHB);
            grid.getChildren().remove(title1);
            Text number = new Text("Enter Number of Reminder to Delete Below:");
            TextArea ion = new TextArea();
            Button confirm = new Button("Confirm Delete");
            Button back = new Button("Back");

            ion.setLayoutX(150);
            ion.setLayoutY(60);
            ion.setPrefWidth(400);
            ion.setPrefHeight(2);            

            number.setLayoutX(150);
            number.setLayoutY(50);

            HBox centering = new HBox(10);
            centering.setLayoutX(150);
            centering.setLayoutY(100);
            centering.getChildren().addAll(confirm,back);
            grid.getChildren().addAll(ion,centering,number);
            confirm.setOnAction(b-> {
                    //here we do the controller and apply to the reminder screen along with a number that says what item number it is!!
                    //make sure to double check if there is a note added or not
                    int num = Integer.parseInt(ion.getText()) - 1;
                    ReminderController.deleteItem(num);
                    grid.getChildren().removeAll(ion,centering,number);
                    grid.getChildren().add(btnHB);
                    grid.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,l17,l18,l19,l20,l21,l22,l23);
                    grid.getChildren().add(title1);
                    for(int i = 0; i<reminders.length; i++) {
                        grid.getChildren().add(reminders[i]);
                    }
                });
            back.setOnAction(c-> {
                    //there is no processing just getting back to the other page
                    grid.getChildren().removeAll(ion,centering,number);
                    grid.getChildren().add(btnHB);
                    grid.getChildren().addAll(l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,l17,l18,l19,l20,l21,l22,l23);
                    grid.getChildren().add(title1);
                    for(int i = 0; i<reminders.length; i++) {
                        grid.getChildren().add(reminders[i]);
                    }
                });
            });
        Scene item_page = new Scene(grid,600,800);
        item_page.setFill(ApplicationGUI.c1);
        stage.setScene(item_page);
    }

    private static void updateReminders(Text[] reminders) {
        for (int i = 0; i < 22; i++) {
            ArrayList<String> reminder = ReminderController.getItem(i);
            if (!reminder.isEmpty()) {
                reminders[i].setText(i + 1 + ". " + reminder.get(0) + ": " + reminder.get(1) + " " + reminder.get(2));
            } else {
                reminders[i].setText("");
            }
        }
    }

}
