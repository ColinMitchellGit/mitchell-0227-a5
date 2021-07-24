package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.math.BigDecimal;

public class InventoryController {

    //Create main ObservableList to store all inventory items
    ObservableList<InventoryItem> mainList = FXCollections.observableArrayList();

    //Declare variable for holding the initial stage for switching scenes
    Stage stage;

    //Declaring FXML tableView
    @FXML
    private TableView<InventoryItem> inventoryTableView;
    @FXML
    private TableColumn<InventoryItem, String> symbolColumn;
    @FXML
    private TableColumn<InventoryItem, BigDecimal> valueColumn;
    @FXML
    private TableColumn<InventoryItem, String> serialNumberColumn;
    @FXML
    private TableColumn<InventoryItem, String> nameColumn;

    @FXML
    public void initialize() {
        //Setting main inventory item TableView
        if(symbolColumn != null && valueColumn != null && serialNumberColumn != null && nameColumn != null && inventoryTableView != null) {
            symbolColumn.setCellValueFactory(new PropertyValueFactory<InventoryItem, String>("symbol"));
            valueColumn.setCellValueFactory(new PropertyValueFactory<InventoryItem, BigDecimal>("value"));
            serialNumberColumn.setCellValueFactory(new PropertyValueFactory<InventoryItem, String>("serial"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<InventoryItem, String>("name"));
            inventoryTableView.setItems(mainList);
        }
    }

    @FXML
    void exportInventoryItems(ActionEvent event) {
        //Create file writer
        //Convert items to json objects
        //Write json objects to json file
    }

    @FXML
    void loadInventoryItems(ActionEvent event) {
        //Create file reader
        //Convert JSON to ArrayList containing all inventory items
        //Add all items in the ArrayList to the main ObservableList of inventory items
        //Close file reader
    }

    @FXML
    void openNewItem(ActionEvent event) {
        //Switch to the new inventory item scene
        switchSceneNew(event);
    }

    @FXML
    void removeSelectedItem(ActionEvent event) {
        if(inventoryTableView.getSelectionModel().getSelectedItem() != null) {
            //Get the selected inventory item from the TableView
            InventoryItem tobeRemoved = inventoryTableView.getSelectionModel().getSelectedItem();

            //Remove the inventory item from the main ObservableList
            mainList.remove(tobeRemoved);
        }
    }

    @FXML
    void viewSelectedItem(ActionEvent event) {
        if(inventoryTableView.getSelectionModel().getSelectedItem() != null) {
            //Get selected to-do list's index from tableView
            int index = inventoryTableView.getSelectionModel().getSelectedIndex();

            //Set scene to the view to-do list window and pass the required list info
            switchSceneView(index, event);
        }
    }

    public void switchSceneNew(ActionEvent event) {
        try {
            //Load provided FXML scene file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InventoryNew.fxml"));
            Parent root = loader.load();

            InventoryNewController controller = loader.getController();
            controller.passMainList(mainList);

            Scene switcher = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();

            //Switch scene to provided fxml scene file
            stage.setScene(switcher);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchSceneView(int index, ActionEvent event) {
        try {
            //Load provided FXML scene file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InventoryView.fxml"));
            Parent root = loader.load();

            InventoryViewController controller = loader.getController();

            controller.passMainList(mainList);
            controller.setSelectedListIndex(index);
            controller.display();

            Scene switcher = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();

            //Switch scene to provided fxml scene file
            stage.setScene(switcher);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPrimaryStage(Stage primaryStage) {
        //Set primary stage for switching scenes
        stage = primaryStage;
    }

    //Pass main list of inventory items through each scene
    public void passMainList(ObservableList<InventoryItem> list) {
        for (InventoryItem item : list) {
            mainList.add(item);
        }
    }
}
