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

public class InventoryViewController {

    //Storing all the inventory items
    ObservableList<InventoryItem> mainList = FXCollections.observableArrayList();

    //Storing selected inventory item's index in the main ObservableList
    int SelectedItemIndex;

    @FXML
    private TextField viewValueField;
    @FXML
    private TextField viewSerialNumberField;
    @FXML
    private TextField viewNameField;

    @FXML
    private Text errorText;

    @FXML
    public void initialize() {
        //Adding a listener to the name text field to limit the input length to 256 characters
        limitDescription(viewNameField);
    }

    @FXML
    void goBackToMain(ActionEvent event) {
        switchSceneMain(event);
    }

    @FXML
    void saveChanges(ActionEvent event) {
        //Check if all fields are filled, the serial number is in the correct format, and the name is at least 2 characters long
        if(!viewValueField.getText().equals("") && !viewSerialNumberField.getText().equals("") && viewSerialNumberField.getText().length() == 10 && viewSerialNumberField.getText().matches("[a-zA-Z0-9]*") && !viewNameField.getText().equals("") && viewNameField.getText().length() >= 2) {
            boolean unique = true;

            //Check if the serial number entered is unique from the rest
            for (InventoryItem inventoryItem : mainList) {
                if(inventoryItem.getSerial().equals(viewSerialNumberField.getText())) {
                    unique = false;
                }
            }

            //Create a new inventory item if the serial is unique
            if(unique) {
                //Get value from value field
                String value = viewValueField.getText();

                //Get serial from serial field
                String serial = viewSerialNumberField.getText();

                //Get name from name field
                String name = viewNameField.getText();

                //Use setters in InventoryItem class to set changes in the main inventory item list
                mainList.get(SelectedItemIndex).setValue(value);
                mainList.get(SelectedItemIndex).setSerial(serial);
                mainList.get(SelectedItemIndex).setName(name);

                //Go to the main inventory screen
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

    //Pass main list of inventory items through each scene
    public void passMainList(ObservableList<InventoryItem> list) {
        mainList.addAll(list);
    }

    //Sets the index of the inventory item that was selected on the main inventory screen
    public void setSelectedListIndex(int index) {
        SelectedItemIndex = index;
    }

    //Sets the value, serial, and name to the selected item's information
    public void display() {
        viewValueField.setText(mainList.get(SelectedItemIndex).getValue().toString());
        viewSerialNumberField.setText(mainList.get(SelectedItemIndex).getSerial());
        viewNameField.setText(mainList.get(SelectedItemIndex).getName());
    }
}
