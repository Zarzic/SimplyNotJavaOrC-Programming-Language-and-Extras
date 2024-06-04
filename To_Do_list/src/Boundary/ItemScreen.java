/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Boundary;

import java.util.ArrayList;

import Control.ItemController;
import Control.ReminderController;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *This class displays items, tasks, and notes
 * @author rschi
 */
public class ItemScreen extends ApplicationGUI{
    private ItemController itemController;
    private static int confirm_num;

    public static void GetItemScreen(Stage stage, Scene main_menu) {
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
        Button ai = new Button("Add Item");
        Button ei = new Button("Edit Item");
        Button di = new Button("Delete Item");
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
        //adding a item button below
        ai.setOnAction(e-> {
            //get the number already ready on previous amount entered
            grid.getChildren().remove(btnHB);
            TextArea input = new TextArea();
            TextArea item_note = new TextArea();
            Text note_label = new Text("Note for Item Below:");
            Text item = new Text("Enter Item Below:");
            Button confirm = new Button("Confirm");
            Button back = new Button("Back");
            input.setLayoutX(150);
            input.setLayoutY(50);
            input.setPrefWidth(400);
            input.setPrefHeight(400);

            item_note.setLayoutX(150);
            item_note.setLayoutY(470);
            item_note.setPrefWidth(400);
            item_note.setPrefHeight(200);
            
            note_label.setLayoutX(150);
            note_label.setLayoutY(460);

            item.setLayoutX(150);
            item.setLayoutY(30);
            HBox centering = new HBox(10);
            centering.setLayoutX(150);
            centering.setLayoutY(680);
            centering.getChildren().addAll(confirm,back);
            grid.getChildren().addAll(input,centering,item_note,note_label,item);
            confirm.setOnAction(b-> {
                    //here we do the controller and apply to the main screen along with a number that says what item number it is!!
                    //make sure to double check if there is a note added or not
                    String i = input.getText();
                    String inl = item_note.getText();
                    ItemController.addItem(inl, i);
                    grid.getChildren().removeAll(input,centering,item_note,note_label,item);
                    grid.getChildren().add(btnHB);
                });
            back.setOnAction(c-> {
                    //there is no processing just getting back to the other page
                    grid.getChildren().removeAll(input,centering,item_note,note_label,item);
                    grid.getChildren().add(btnHB);
                });
            });
        //edit item button below
        ei.setOnAction(e-> {
            //get the number already ready on previous amount entered
            grid.getChildren().remove(btnHB);
            Text number = new Text("Enter Number of Item Below:");
            Text item = new Text("Change Task Below:");
            TextArea ion = new TextArea();
            TextArea ie = new TextArea();
            TextArea ine = new TextArea();
            Text note_label = new Text("Change Note for Task Below:");
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
            ine.setPrefHeight(200);
            
            note_label.setLayoutX(150);
            note_label.setLayoutY(480);

            number.setLayoutX(150);
            number.setLayoutY(50);

            item.setLayoutX(150);
            item.setLayoutY(140);
            HBox centering = new HBox(10);
            centering.setLayoutX(150);
            centering.setLayoutY(710);
            centering.getChildren().addAll(confirm,back);
            grid.getChildren().addAll(ion,ie,centering,ine,note_label,item,number,cn);
            cn.setOnAction(m-> {
                    //make sure to get items and fill the Text area from the entities class here
                    String val = ion.getText();
                    confirm_num = Integer.parseInt(val) - 1;
                    ArrayList<String> item_e = new ArrayList<>();
                    item_e = ItemController.getItem(confirm_num);
                    ie.appendText(item_e.get(0));
                    ine.appendText(item_e.get(1));
                });
            confirm.setOnAction(b-> {
                    //here we do the controller and apply to the main screen along with a number that says what item number it is and sussefully edit it.
                    //make sure to double check if there is a note added or not
                    //get the item here
                    String taske = ie.getText();
                    String taskne = ine.getText();
                    ItemController.setItem(taskne, taske, confirm_num);
                    grid.getChildren().removeAll(ion,ie,centering,ine,note_label,item,number,cn);
                    grid.getChildren().add(btnHB);
                });
            back.setOnAction(c-> {
                    //there is no processing just getting back to the other page
                    grid.getChildren().removeAll(ion,ie,centering,ine,note_label,item,number,cn);
                    grid.getChildren().add(btnHB);
                });
            });
        //delete item button below
        di.setOnAction(e-> {
            grid.getChildren().remove(btnHB);
            Text number = new Text("Enter Number of Item to Delete Below:");
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
                    //here we do the controller and apply to the main screen along with a number that says what item number it is!!
                    //make sure to double check if there is a note added or not
                    String user_input1 = ion.getText();
                    int arg = Integer.parseInt(user_input1) - 1;
                    ItemController.deleteItem(arg);
                    grid.getChildren().removeAll(ion,centering,number);
                    grid.getChildren().add(btnHB);
                });
            back.setOnAction(c-> {
                    //there is no processing just getting back to the other page
                    grid.getChildren().removeAll(ion,centering,number);
                    grid.getChildren().add(btnHB);
                });
            });
        Scene item_page = new Scene(grid,600,800);
        item_page.setFill(ApplicationGUI.c1);
        stage.setScene(item_page);
        
    }

    public ItemController getItemController() {
        return itemController;
    }

    public void setItemController(ItemController itemController) {
        this.itemController = itemController;
    }
}
