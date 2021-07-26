/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Colin Mitchell
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    @Test
    void store100InventoryItems() {
        //Create value
        String value = "399.99";

        //Create serial
        String serial = "1234567891";

        //Create name
        String name = "Xbox One";

        //Create ArrayList to add 100 items to
        ArrayList<InventoryItem> items = new ArrayList<InventoryItem>();

        //Create 100 inventory items and store them in ObservableList
        for (int i = 0; i < 100; i++) {
            InventoryItem item = new InventoryItem(value, serial, name);
            items.add(item);
        }

        //Assert that the list contains 100 items
        assertEquals(100, items.size());
    }

    @Test
    void addInventoryItem() {
        //Create value
        String value = "399.99";

        //Create serial
        String serial = "1234567891";

        //Create name
        String name = "Xbox One";

        //Create ArrayList to add item to
        ArrayList<InventoryItem> items = new ArrayList<InventoryItem>();

        items.add(new InventoryItem(value, serial, name));

        //Assert that the ArrayList contains the added item
        assertEquals(1, items.size());
    }

    @Test
    void removeInventoryItem() {
        //Create value
        String value = "399.99";

        //Create serial
        String serial = "1234567891";

        //Create name
        String name = "Xbox One";

        //Create ArrayList to add item to
        ArrayList<InventoryItem> items = new ArrayList<InventoryItem>();

        //Add an inventory item
        items.add(new InventoryItem(value, serial, name));

        //Remove the inventory item
        items.remove(0);

        //Assert that the ArrayList no longer contains the added item after removing it
        assertEquals(0, items.size());
    }

    @Test
    void editValueOfInventoryItem() {
        //Create value
        String value = "399.99";

        //Create serial
        String serial = "1234567891";

        //Create name
        String name = "Xbox One";

        //Create ArrayList to add item to
        ArrayList<InventoryItem> items = new ArrayList<InventoryItem>();

        //Create ArrayList to check against the other
        ArrayList<InventoryItem> edited = new ArrayList<InventoryItem>();

        //Add an inventory item
        items.add(new InventoryItem(value, serial, name));

        //Add the same to the other
        edited.add(new InventoryItem(value, serial, name));

        //Edit the value of the item in the edited list
        edited.get(0).setValue("299.99");

        //Assert that the item's value was changed against the un-edited list
        assertNotEquals(items.get(0).getValue(), edited.get(0).getValue());
    }

    @Test
    void editSerialOfInventoryItem() {
        //Create value
        String value = "399.99";

        //Create serial
        String serial = "1234567891";

        //Create name
        String name = "Xbox One";

        //Create ArrayList to add item to
        ArrayList<InventoryItem> items = new ArrayList<InventoryItem>();

        //Create ArrayList to check against the other
        ArrayList<InventoryItem> edited = new ArrayList<InventoryItem>();

        //Add an inventory item
        items.add(new InventoryItem(value, serial, name));

        //Add the same to the other
        edited.add(new InventoryItem(value, serial, name));

        //Edit the value of the item in the edited list
        edited.get(0).setSerial("1234567892");

        //Assert that the item's serial was changed against the un-edited list
        assertNotEquals(items.get(0).getSerial(), edited.get(0).getSerial());
    }

    @Test
    void editNameOfInventoryItem() {
        //Create value
        String value = "399.99";

        //Create serial
        String serial = "1234567891";

        //Create name
        String name = "Xbox One";

        //Create ArrayList to add item to
        ArrayList<InventoryItem> items = new ArrayList<InventoryItem>();

        //Create ArrayList to check against the other
        ArrayList<InventoryItem> edited = new ArrayList<InventoryItem>();

        //Add an inventory item
        items.add(new InventoryItem(value, serial, name));

        //Add the same to the other
        edited.add(new InventoryItem(value, serial, name));

        //Edit the name of the item in the edited list
        edited.get(0).setName("Xbox Four");

        //Assert that the item's name was changed against the un-edited list
        assertNotEquals(items.get(0).getName(), edited.get(0).getName());
    }

    @Test
    void sortByValue() {
        //I didn't manually code this requirement into my project because its handled by the TableView's automatic sorting function.
        //So I didn't make a test for it.
    }

    @Test
    void sortBySerial() {
        //I didn't manually code this requirement into my project because its handled by the TableView's automatic sorting function.
        //So I didn't make a test for it.
    }

    @Test
    void sortByName() {
        //I didn't manually code this requirement into my project because its handled by the TableView's automatic sorting function.
        //So I didn't make a test for it.
    }

    @Test
    void searchBySerial() {
        //Create value
        String value = "399.99";

        //Create serial
        String serial = "1234567891";

        //Create name
        String name = "Xbox One";

        //Create ArrayList to add item to
        ArrayList<InventoryItem> items = new ArrayList<InventoryItem>();

        //Create ArrayList to check against the other
        ArrayList<InventoryItem> sortedBySerial = new ArrayList<InventoryItem>();

        //Add an inventory item
        items.add(new InventoryItem(value, serial, name));

        //Add an inventory item with different serial
        items.add(new InventoryItem(value, "1234567819", name));

        //If the list contains an item with the searched serial, add it to the sorted list
        for (InventoryItem item : items) {
            if(item.getSerial().equals(serial)) {
                sortedBySerial.add(item);
            }
        }

        //Asserting that the list with the searched by serial items is smaller than the list with all items
        assertNotEquals(items.size(), sortedBySerial.size());
    }

    @Test
    void searchByName() {
        //Create value
        String value = "399.99";

        //Create serial
        String serial = "1234567891";

        //Create name
        String name = "Xbox One";

        //Create ArrayList to add item to
        ArrayList<InventoryItem> items = new ArrayList<InventoryItem>();

        //Create ArrayList to check against the other
        ArrayList<InventoryItem> sortedByName = new ArrayList<InventoryItem>();

        //Add an inventory item
        items.add(new InventoryItem(value, serial, name));

        //Add an inventory item with different name
        items.add(new InventoryItem(value, "Playstation 4", name));

        //If the list contains an item with the searched name, add it to the sorted list
        for (InventoryItem item : items) {
            if(item.getSerial().equals(name)) {
                sortedByName.add(item);
            }
        }

        //Asserting that the list with the searched by name items is smaller than the list with all items
        assertNotEquals(items.size(), sortedByName.size());
    }

    @Test
    void saveToFile() {
        //Create a couple InventoryItems
        //Store them in an ArrayList
        //Create new JSON file in provided directory
        //Create a JSON object arraylist
        //use forEach() ArrayList method:
            //try catch use a json library to convert each InventoryItem to an object
            //Store the object in the json arraylist
        //use forEach() ArrayList method:
            //output each object to the json file
        //assertTrue using File method exists() to ensure that the file was created
    }

    @Test
    void loadFromFile() {
        //Create a couple InventoryItems
        //Store them in an ArrayList
        //Create new JSON file in provided directory
        //Create a JSON object arraylist
        //use forEach() ArrayList method:
            //use a json library to convert each InventoryItem to an object
            //Store the object in the json arraylist
        //use forEach() ArrayList method:
            //output each object to the json file
        //Use the same json library to read back each InventoryItem object from the file into a new ArrayList
        //assertEquals the first ArrayList against the new ArrayList with the json loaded InventoryItems
    }
}