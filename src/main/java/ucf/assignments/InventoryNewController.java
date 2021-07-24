/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Colin Mitchell
 */

package ucf.assignments;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;

public class InventoryNewController {

    //Storing all the inventory items
    ObservableList<InventoryItem> mainList = FXCollections.observableArrayList();

    @FXML
    private TextField newValueField;
    @FXML
    private TextField newSerialNumberField;
    @FXML
    private TextField newNameField;

    @FXML
    private Text errorText;

    @FXML
    public void initialize() {
        //Adding a listener to the name text field to limit the input length to 256 characters
        limitDescription(newNameField);
    }

    @FXML
    void goBackToMain(ActionEvent event) {
        //Return to the main inventory screen
        switchSceneMain(event);
    }

    @FXML
    void saveNewItem(ActionEvent event) {
        //Check if all fields are filled, the serial number is in the correct format, and the name is at least 2 characters long
        if(!newValueField.getText().equals("") && !newSerialNumberField.getText().equals("") && newSerialNumberField.getText().length() == 10 && newSerialNumberField.getText().matches("[a-zA-Z0-9]*") && !newNameField.getText().equals("") && newNameField.getText().length() >= 2) {
            boolean unique = true;

            //Check if the serial number entered is unique from the rest
            for (InventoryItem inventoryItem : mainList) {
                if(inventoryItem.getSerial().equals(newSerialNumberField.getText())) {
                    unique = false;
                }
            }

            //Create a new inventory item if the serial is unique
            if(unique) {
                //Get value from value field
                String value = newValueField.getText();

                //Get serial from serial field
                String serial = newSerialNumberField.getText();

                //Get name from name field
                String name = newNameField.getText();

                //Create new inventory item from fields and store it in the main ObservableList
                InventoryItem newItem = new InventoryItem(value, serial, name);
                mainList.add(newItem);

                //Switch scene back to the main inventory screen
                switchSceneMain(event);
            }else {
                errorText.setVisible(true);
            }
        }
    }

    private void switchSceneMain(ActionEvent event) {
        try {
            //Load the main inventory FXML File
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InventoryMain.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            //Get the controller to pass the main list of inventory items
            InventoryController controller = loader.getController();
            controller.passMainList(mainList);

            //Switch scene to main FXML scene file
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Limit the length of the name text field to 256 characters
    public static void limitDescription(final TextField text) {
        text.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (text.getText().length() > 256) {
                    String s = text.getText().substring(0, 256);
                    text.setText(s);
                }
            }
        });
    }

    //Pass main list of to-do items through each scene
    public void passMainList(ObservableList<InventoryItem> list) {
        for (InventoryItem item : list) {
            mainList.add(item);
        }
    }
}
