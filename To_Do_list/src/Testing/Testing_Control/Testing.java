package Testing.Testing_Control;
import java.util.ArrayList;

import org.junit.*;

import Control.ReminderController;
public class Testing {

    @Test
    public void addRemindertest() {
        //correct input
        ReminderController.addItem("Hello","11/30/2023 1:30 PM");
        ArrayList<String> arr = new ArrayList<>();
        arr = ReminderController.getItem(0);
        Assert.assertEquals(arr.get(0), "Hello");
        Assert.assertEquals(arr.get(1), "11/30/2023");
        Assert.assertEquals(arr.get(2), "1:30 PM");
        //large input
        ReminderController.addItem("HelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHello","11/30/2023 1:30 PM");
        ArrayList<String> arr3 = new ArrayList<>();
        arr3 = ReminderController.getItem(1);
        Assert.assertEquals(arr3.get(0), "HelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHello");
        Assert.assertEquals(arr3.get(1), "11/30/2023");
        Assert.assertEquals(arr3.get(2), "1:30 PM");
        //incorrect input
        //title will accept any charcter
        //date has to be in correct format
        ReminderController.addItem("Hello","11/30");
        ArrayList<String> arr2 = new ArrayList<>();
        arr2 = ReminderController.getItem(2);
        Assert.assertEquals("Hello", arr2.get(0));
        Assert.assertEquals("Error. Please try again with correct input layout!", arr2.get(1));
    }

    @Test
    public void deleteRemindertest() {
        //This is to clear the reminder controller of all reminders before testing.
        ReminderController.deleteAllItems();
        ReminderController.addItem("Hello","11/30/2023 1:30 PM");
        ReminderController.addItem("Hello1","11/30/2023 1:31 PM");
        ReminderController.addItem("Hello2","11/30/2023 1:32 PM");
        ReminderController.addItem("Hello3","11/30/2023 1:33 PM");
        //deleting items in the middle
        ReminderController.deleteItem(1);
        //deleting items at the front
        ReminderController.deleteItem(0);
        //deleting items at the end
        ReminderController.deleteItem(3);
        //make sure that these values are now null
        Assert.assertEquals(0, ReminderController.getItem(1).size());
        Assert.assertEquals(0, ReminderController.getItem(0).size());
        Assert.assertEquals(0, ReminderController.getItem(3).size());
        Assert.assertNotNull(ReminderController.getItem(2));
    }
}
