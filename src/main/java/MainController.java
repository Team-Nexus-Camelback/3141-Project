/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author cbdh2
 */
public class MainController implements Initializable {

    
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
    protected TableView latestPerchaseTable;
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
    	api.PurchaseManager.getInstance().savePurchaseData(123,categoryField.getText(),Float.parseFloat(amountField.getText()));
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
    }    
    
}
