/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;
import java.util.ResourceBundle;

import api.PurchaseManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Popup;
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

    private Stage stage;

    @FXML
    protected void inputWindow(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("inputWindow.fxml"), resources);
            stage = new Stage();
            stage.setTitle("Input Dialogue");
            stage.setScene(new Scene(root, 600, 450));


    }
    
    @FXML
    protected void addPressed(ActionEvent e) throws IOException{

    	
//    	test1.setText(categoryBox.getValue());
//    	test2.setText(amount.getText());
        if (categoryBox.getValue().equals("Purchase Category")) {
            float purchaseAmount = Float.parseFloat(amount.getText());
            Hashtable<String, String> reponse = PurchaseManager.getInstance().savePurchaseData(0, "Purchase", purchaseAmount);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "your purchase of $" + reponse.get("amount") +
                    " has been saved.");
            alert.setHeaderText("Purchase Saved");

            alert.show();

        }

    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
