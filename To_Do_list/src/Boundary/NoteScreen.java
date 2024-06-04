package Boundary;


import javafx.util.Duration;
import Control.NoteController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NoteScreen extends ApplicationGUI{


    public static void GetNoteScreen(Stage stage, Scene main_menu) {
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

        Button b_b_m = new Button("Back To Main Page");
        Button ai = new Button("Add Note");
        Button ei = new Button("Edit Note");
        Button di = new Button("Delete Note");
        //evenly space out the buttons
        HBox btnHB = new HBox(10);
        btnHB.getChildren().addAll(ai,ei,di,b_b_m);
        btnHB.setLayoutX(150);
        btnHB.setLayoutY(10);
        //adding to the plane of the scene
        grid.getChildren().add(btnHB);
        b_b_m.setOnAction(e-> { 
            stage.setScene(main_menu);
            });
        //edit item button below
        ei.setOnAction(e-> {
            //if there is previous note already filled then populate otherwise no
            grid.getChildren().remove(btnHB);
            Text item = new Text("Change Note Below:");
            TextArea ne = new TextArea();
            Button confirm = new Button("Confirm Changes");
            Button back = new Button("Back");

            ne.setLayoutX(150);
            ne.setLayoutY(150);
            ne.setPrefWidth(400);
            ne.setPrefHeight(300);

            item.setLayoutX(150);
            item.setLayoutY(140);
            HBox centering = new HBox(10);
            centering.setLayoutX(150);
            centering.setLayoutY(460);
            centering.getChildren().addAll(confirm,back);
            grid.getChildren().addAll(centering,item,ne);
            ne.appendText(NoteController.getNote());
            confirm.setOnAction(b-> {
                    //here we do the controller and apply to the main screen along with a number that says what item number it is and sussefully edit it.
                    //make sure to double check if there is a note added or not
                    String fill = ne.getText();
                    NoteController.setNote(fill);
                    grid.getChildren().removeAll(centering,item,ne);
                    grid.getChildren().add(btnHB);
                });
            back.setOnAction(c-> {
                    //there is no processing just getting back to the other page
                    grid.getChildren().removeAll(centering,item,ne);
                    grid.getChildren().add(btnHB);
                });
            });
        ai.setOnAction(e-> {
            //if there is previous note already filled then populate otherwise no
            grid.getChildren().remove(btnHB);
            Text item = new Text("Add Note Below:");
            TextArea ne = new TextArea();
            Button confirm = new Button("Confirm Changes");
            Button back = new Button("Back");

            ne.setLayoutX(150);
            ne.setLayoutY(150);
            ne.setPrefWidth(400);
            ne.setPrefHeight(300);

            item.setLayoutX(150);
            item.setLayoutY(140);
            HBox centering = new HBox(10);
            centering.setLayoutX(150);
            centering.setLayoutY(460);
            centering.getChildren().addAll(confirm,back);
            grid.getChildren().addAll(centering,item,ne);
            confirm.setOnAction(b-> {
                    //here we do the controller and apply to the main screen along with a number that says what item number it is and sussefully edit it.
                    //make sure to double check if there is a note added or not
                    String v = ne.getText();
                    NoteController.setNote(v);
                    grid.getChildren().removeAll(centering,item,ne);
                    grid.getChildren().add(btnHB);
                });
            back.setOnAction(c-> {
                    //there is no processing just getting back to the other page
                    grid.getChildren().removeAll(centering,item,ne);
                    grid.getChildren().add(btnHB);
                });
            });
        di.setOnAction(e-> {
            grid.getChildren().remove(btnHB);
            Button confirm = new Button("Confirm Delete for Entire Note");
            Button back = new Button("Back");
            HBox centering = new HBox(10);
            centering.setLayoutX(150);
            centering.setLayoutY(100);
            centering.getChildren().addAll(confirm,back);
            grid.getChildren().add(centering);
            confirm.setOnAction(b-> {
                    //here we do the controller and apply to the main screen along with a number that says what item number it is!!
                    //make sure to double check if there is a note added or not
                    NoteController.deleteNote();
                    grid.getChildren().remove(centering);
                    grid.getChildren().add(btnHB);
                });
            back.setOnAction(c-> {
                    //there is no processing just getting back to the other page
                    grid.getChildren().remove(centering);
                    grid.getChildren().add(btnHB);
                });
            });
        Scene item_page = new Scene(grid,600,800);
        item_page.setFill(ApplicationGUI.c1);
        stage.setScene(item_page);
    }
}