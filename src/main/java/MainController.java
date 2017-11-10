/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import api.PurchaseManager;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.Purchase;
import models.Month;
import javafx.scene.*;

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
    protected TableView<Purchase> latestPerchaseTable;
    @FXML
    protected TableView billsDue, changesTable;
    @FXML
    protected PieChart purchasesPie;
    @FXML
    protected TextField dateField, categoryField, amountField, nameField;
    @FXML
    protected CategoryAxis xAxis;
    @FXML
    protected NumberAxis yAxis;
    @FXML
    protected BarChart<String, Double> bc;

    final ObservableList<Purchase> data = FXCollections.observableArrayList();

    final ObservableList<Month> bcData = FXCollections.observableArrayList();

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
    public void saveData(){
        //Save function calls
        Alert saved = new Alert(Alert.AlertType.INFORMATION);
        saved.setHeaderText(null);
        saved.setContentText("Your data has been saved!");
        saved.showAndWait();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        latestPerchaseTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        latestPerchaseTable.setItems(data);
        dateCol.setCellValueFactory(new PropertyValueFactory<Purchase, String>("Date"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Purchase, String>("Name"));
        catCol.setCellValueFactory(new PropertyValueFactory<Purchase, String>("Category"));
        amountCol.setCellValueFactory(new PropertyValueFactory<Purchase, Float>("Amount"));

        xAxis.setLabel("Category");
        yAxis.setLabel("Percentage");

        XYChart.Series<String, Double> series1 = new XYChart.Series<>();
        bc.getData().add(series1);

        ObservableList<PieChart.Data> pieData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Unallocated", 100),
                        new PieChart.Data("Food", 30)
        );

        pieData.forEach(data ->
        data.nameProperty().bind(
                Bindings.concat(
                        data.getName(), " ", (data.pieValueProperty().multiply(100)), "%"
                )
        ));
        purchasesPie.setData(pieData);
        purchasesPie.setTitle("Money Spent This Month");
        purchasesPie.setLabelLineLength(10);
        purchasesPie.setLegendSide(Side.LEFT);



        }

    }    
    

