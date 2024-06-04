/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Entity.Date;
import Entity.Reminder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * This class controls and manages the flow of reminder-related tasks and data between 
 * the Reminder Screen and the Reminder class
 * @author rschi
 */
public class ReminderController {
    private static HashMap<Integer, Reminder> reminders = new HashMap<>();

    public static ArrayList<String> getItem(int index) {
        ArrayList<String> arrayList = new ArrayList<>();
        if(reminders.get(index) != null) {
            arrayList.add(reminders.get(index).getTitle());
            arrayList.add(reminders.get(index).getDate().getDate());
            arrayList.add(reminders.get(index).getDate().getTime());
        }

        return arrayList;
    }

    public static void setItem(String title, String date, int index) {
        String[] dateAndTime = splitDate(date);
        reminders.put(index, new Reminder(title, new Date(dateAndTime[0], dateAndTime[1])));
    }

    public static void addItem(String title, String date) {
        if (reminders.size() < 10) {
            int key = 0;
            String[] dateAndTime = splitDate(date);

            Reminder reminder = new Reminder(title, new Date(dateAndTime[0], dateAndTime[1]));

            for (int i = 0; i < 10; i++) {
                if (!reminders.containsKey(i)) {
                    key = i;
                    break;
                }
            }

            reminders.put(key, reminder);
        }
    }

    private static String[] splitDate(String date) {
        String[] dateAndTime = new String[2];

        int i = 0;
        while (date.charAt(i) != ' ') {
            i++;
        }

        dateAndTime[0] = date.substring(0, i);
        dateAndTime[1] = date.substring(i+1);

        return dateAndTime;
    }

    public static void deleteItem(int index) {
        reminders.remove(index);
    }

    public static void deleteAllItems() {
        reminders.clear();
    }

    public static int getItemIndex(Reminder reminder) {
        int index = -1;

        if (reminders.containsValue(reminder)) {
            for (HashMap.Entry<Integer, Reminder> entry : reminders.entrySet()) {
                if (Objects.equals(entry.getValue(), reminder)) {
                    index = entry.getKey();
                }
            }
            return index;
        } else {
            System.out.println("Item is not found in HashMap");
            return -1;
        }
    }
}