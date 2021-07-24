/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Colin Mitchell
 */

package ucf.assignments;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class InventoryExportController {

    //Create main ObservableList to store all inventory items
    ObservableList<InventoryItem> mainList = FXCollections.observableArrayList();

    @FXML
    private TextField fileNameField;
    @FXML
    private TextField fileLocationField;

    //Error message for invalid JSON file location
    @FXML
    private Text errorText;

    @FXML
    void exportInventory(ActionEvent event) {
        if(!fileNameField.getText().equals("") && !fileLocationField.getText().equals("")) {
            try{
                //Get file name
                String fileName = fileNameField.getText();

                //Get file location and format it for the file writer
                String fileLocation = fileLocationField.getText().replace("\\", "\\\\");
                fileLocation = fileLocation.replace("\"", "");
                fileLocation += "\\\\";

                //Create file writer
                Writer writer = new FileWriter(fileLocation + fileName + ".json");
                Gson gson = new GsonBuilder().create();

                //Convert inventory items to JSON objects and write them to json file
                gson.toJson(mainList, writer);

                //Close the file writer
                writer.flush();
                writer.close();

                //Return to the main inventory screen
                switchSceneMain(event);

            }catch(Exception e) {
                errorText.setVisible(true);
            }
        }
    }

    @FXML
    void goBackToMain(ActionEvent event) {
        //Return to the main inventory screen
        switchSceneMain(event);
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

    //Pass main list of inventory items through each scene
    public void passMainList(ObservableList<InventoryItem> list) {
        for (InventoryItem item : list) {
            mainList.add(item);
        }
    }
}
