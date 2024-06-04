/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Boundary;

import Control.ReminderController;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * This class displays alerts, reminders,
 * @author rschi
 */
public class AlertScreen {
    public static void showAlertIfTimePassed() {
        LocalDateTime currentDateTime = LocalDateTime.now();

        for (int i = 0; i < 22; i++) {
            if (!ReminderController.getItem(i).isEmpty()) {
                String dateString = ReminderController.getItem(i).get(1) + " " + ReminderController.getItem(i).get(2);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");

                LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);

                if (currentDateTime.isAfter(dateTime)) {
                    int finalI = i; // To use inside runLater

                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Reminder");
                        alert.setHeaderText(null);
                        alert.setContentText(ReminderController.getItem(finalI).get(0));

                        DialogPane dialogPane = alert.getDialogPane();
                        dialogPane.getButtonTypes().add(ButtonType.CLOSE);

                        ReminderController.deleteItem(finalI);

                        alert.showAndWait();
                    });
                }
            }
        }
    }
}
