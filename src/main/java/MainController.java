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
    protected Label test1;
    @FXML
    protected Label test2;
    @FXML
    protected ChoiceBox<String> categoryBox;
    @FXML
    protected TextField amount;
    @FXML
    protected void inputWindow(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("inputWindow.fxml"), resources);
            Stage stage = new Stage();
            stage.setTitle("Input Dialogue");
            stage.setScene(new Scene(root, 600, 450));
            stage.show();
    }
    
    @FXML
    protected void addPressed(ActionEvent e) throws IOException{
    	test1.setText(categoryBox.getValue());
    	Double numericValue;
    	if (categoryBox.getValue().equals("Purchase Category")) {
    		numericValue = 0.0 - Double.parseDouble(amount.getText());
    	}
    	else
    		numericValue = Double.parseDouble(amount.getText());
    	test2.setText(numericValue.toString());
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
