/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import api.PurchaseManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Purchase;

/**
 * FXML Controller class
 *
 * @author cbdh2
 */
public class MainController implements Initializable {

    @FXML
    public TableColumn<Purchase, String> dateCol;
    public TableColumn<Purchase, String> nameCol;
    public TableColumn<Purchase, Float> amountCol;
    public TableColumn<Purchase, String> catCol;

    @FXML
    private ResourceBundle resources;
    @FXML
    protected Label totalBudgetLabel;
    @FXML
    protected Label test2;
    @FXML
    protected ChoiceBox<String> categoryBox;
    @FXML
    protected TextField amount;

    @FXML 
    protected TableView<Purchase> latestPerchaseTable  = new TableView<>();

    @FXML
    protected TableView billsDue;
    @FXML
    protected TableView changesTable;
    @FXML
    protected PieChart purchasesPie;
    @FXML
    protected TextField dateField;
    @FXML
    protected TextField amountField;
    @FXML
    protected TextField nameField;
    @FXML
    protected TextField categoryField;

    final ObservableList<Purchase> data = FXCollections.observableArrayList(new Purchase(10.99f, "Today", "Hello", "World"));

    @FXML
    protected TableView latestPerchaseTable;
    @FXML
    protected void inputWindow(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("inputWindow.fxml"), resources);
            Stage stage = new Stage();
            stage.setTitle("Input Dialogue");
            stage.setScene(new Scene(root, 600, 450));
            stage.show();
    }
    
    @FXML
    protected void addPurchaseEvent(ActionEvent e) throws IOException{
        PurchaseManager.getInstance().savePurchaseData(0, categoryField.getText(), Float.parseFloat(amountField.getText()));
    	Purchase purchase = new Purchase(Float.parseFloat(amountField.getText()), dateField.getText(),categoryField.getText(), nameField.getText());
        data.add(purchase);
    	dateField.setText("Date");
    	categoryField.setText("Category");
    	amountField.setText("Amount");
    	nameField.setText("Name");

    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        latestPerchaseTable.setItems(data);
        dateCol.setCellValueFactory(new PropertyValueFactory<Purchase, String>("Date"));

        nameCol.setCellValueFactory(new PropertyValueFactory<Purchase, String>("Name"));
        catCol.setCellValueFactory(new PropertyValueFactory<Purchase, String>("Category"));
        amountCol.setCellValueFactory(new PropertyValueFactory<Purchase, Float>("Amount"));

    }    
    
}
