/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Entity.Item;
import Entity.Note;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * This class controls and manages the flow of item-related tasks and data within the application, 
 * facilitating the creation, editing, and management of items and tasks
 * @author rschi
 */
public class ItemController {
    public static HashMap<Integer, Item> items = new HashMap<>(10);

    public static ArrayList<String> getItem(int index) {
        ArrayList<String> arrayList = new ArrayList<>();
        if(items.get(index) != null) {
            arrayList.add(items.get(index).getTitle());
            arrayList.add(items.get(index).getItemNote().getNote());
        }

        return arrayList;
    }

    public static void setItem(String note, String title, int index) {
        items.put(index, new Item(new Note(note), title));
    }

    public static void addItem(String note, String title) {
        int key = 0;

        if (items.size() < 10) {
            Item item = new Item(new Note(note), title);

            for (int i = 0; i < 10; i++) {
                if (!items.containsKey(i)) {
                    key = i;
                    break;
                }
            }

            items.put(key, item);
        }
    }

    public static void deleteItem(int index) {
        items.remove(index);
    }

    public static void deleteAllItems() {
        items.clear();
    }

    public static int getItemIndex(Item item) {
        int index = -1;

        if (items.containsValue(item)) {
            for (HashMap.Entry<Integer, Item> entry : items.entrySet()) {
                if (Objects.equals(entry.getValue(), item)) {
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
