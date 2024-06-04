package Control;

public class NoteController {
    private static String note = "";

    public static String getNote() {
        return note;
    }

    public static void setNote(String str) {
        note = str;
    }

    public static void deleteNote() {
        note = "";
    }
}
