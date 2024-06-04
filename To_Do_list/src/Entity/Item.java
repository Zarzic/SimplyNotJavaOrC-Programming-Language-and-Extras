/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 * This class represents a specific item within the application, encompassing tasks, 
 * reminders, notes, or any other type of content
 * @author rschi
 */
public class Item {
    private Note note;
    private String title;

    public Item(Note note, String title) {
        this.note = note;
        this.title = title;
    }

    public Note getItemNote() {
        return note;
    }

    public void setItemNote(Note itemNote) {
        this.note = note;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
