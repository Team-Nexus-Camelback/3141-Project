/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.util.ResourceBundle;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import api.MonthManager;
import api.PurchaseManager;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.FloatStringConverter;
import models.Purchase;
import models.Month;
import org.controlsfx.dialog.LoginDialog;

/**
 * FXML Controller class
 *
 * @author cbdh2
 */
public class MainController implements Initializable {

    @FXML
    public TableColumn<Purchase, String> dateCol;
    public TableColumn<Purchase, String> nameCol;
    public TableColumn<Purchase, Double> amountCol;
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
    protected TableView<Purchase> latestPerchaseTable;
    @FXML
    protected TableView billsDue, changesTable;
    @FXML
    protected PieChart purchasesPie;
    @FXML
    protected TextField dateField, categoryField, amountField, nameField, amount;
    @FXML
    protected CategoryAxis xAxis;
    @FXML
    protected NumberAxis yAxis;
    @FXML
    protected BarChart<String, Double> bc;

    final ObservableList<Purchase> data = FXCollections.observableArrayList();


    private Month month = MonthManager.getInstance().getMonthData("11-2017");

    @FXML
    protected void addPurchaseEvent(ActionEvent e) throws IOException{
        try {
            if (month.getCategories().keySet().contains(categoryField.getText())) {
                DateFormat.getDateInstance(DateFormat.SHORT).parse(dateField.getText());
                Purchase purchase = new Purchase(0, Double.parseDouble(amountField.getText()), dateField.getText(), categoryField.getText(), nameField.getText());
                PurchaseManager.getInstance().savePurchaseData(purchase);
                data.add(purchase);
                dateField.setText("Date");
                categoryField.setText("Category");
                amountField.setText("Amount");
                nameField.setText("Name");
                month = MonthManager.getInstance().getMonthData("11-2017");
                bc.getData().forEach(new Consumer<XYChart.Series<String, Double>>() {
                    @Override
                    public void accept(XYChart.Series<String, Double> stringDoubleSeries) {
                        double newePercent = month.getCategories().get(stringDoubleSeries.getName());
                        stringDoubleSeries.getData().get(0).setYValue(newePercent);
                    }
                });
            }
            else {
                
            }
        } catch(Exception error)
            {
                Alert saved = new Alert(Alert.AlertType.INFORMATION);
                saved.setHeaderText(null);
                saved.setContentText("Date Formatted Wrong. Format: M/DD/YY\n Or Invalid Amount");
                saved.showAndWait();
            }

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

        //TODO tabPane.getSelectionModel().select(tabname); This forces the current tab to switch
        //TODO get table and chart data from the other class

        //sets up the table to be editable and makes it so the columns resize on their own
        latestPerchaseTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        latestPerchaseTable.setEditable(true);
        latestPerchaseTable.setItems(data);

        //TODO ADD UPDATE METHOD TO ALL OF THESE SO IT UPDATES THE DATA SAVED AND GRAPHS USES ID
        //Date Column declaration and handler
        dateCol.setCellValueFactory(new PropertyValueFactory("Date"));
        dateCol.setCellFactory(TextFieldTableCell.forTableColumn());
        dateCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Purchase, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Purchase, String> event) {
                        try {
                            DateFormat.getDateInstance(DateFormat.SHORT).parse(event.getNewValue());
                            ((Purchase) event.getTableView().getItems().get(
                                    event.getTablePosition().getRow())
                            ).setDate(event.getNewValue());

                        }catch(Exception e){
                            Alert saved = new Alert(Alert.AlertType.INFORMATION);
                            saved.setHeaderText(null);
                            saved.setContentText("Date Formatted Wrong. Format: M/DD/YY");
                            saved.showAndWait();
                            latestPerchaseTable.refresh();
                        }
                        }
                }
        );

        //Name Column declaration and handler
        nameCol.setCellValueFactory(new PropertyValueFactory<Purchase, String>("Name"));
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Purchase, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Purchase, String> event) {
                            ((Purchase) event.getTableView().getItems().get(
                                    event.getTablePosition().getRow())
                            ).setDate(event.getNewValue());
                                                }
                }
        );

        //Category Column declaration and handler
        catCol.setCellValueFactory(new PropertyValueFactory<Purchase, String>("Category"));
        catCol.setCellFactory(TextFieldTableCell.forTableColumn());
        catCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Purchase, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Purchase, String> event) {
                        final String value = event.getNewValue() != null ?
                                event.getNewValue() : event.getOldValue();
                        ((Purchase) event.getTableView().getItems().get(
                                event.getTablePosition().getRow())
                        ).setDate(value);
                    }
                }
        );

        //Amount Column declaration and handler to check if its a double when someone edits
        amountCol.setCellValueFactory(new PropertyValueFactory<Purchase, Double>("Amount"));
        amountCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        try {
        amountCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Purchase, Double>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Purchase, Double> event) {

                            final Double value = event.getNewValue() != null ?
                                    event.getNewValue() : event.getOldValue();
                            ((Purchase) event.getTableView().getItems().get(
                                    event.getTablePosition().getRow())
                            ).setAmount(value);
                    }
                }

        );
        } catch (Exception e){
            Alert saved = new Alert(Alert.AlertType.INFORMATION);
            saved.setHeaderText(null);
            saved.setContentText("Invalid Amount");
            saved.showAndWait();
        }

        xAxis.setLabel("Category");
        yAxis.setLabel("Percentage");
        yAxis.setLowerBound(0);
        yAxis.setUpperBound(100);



        // loads in purchase from the system
        data.addAll(month.getPurchases());

        // adds the data to bar graph
        month.getCategories().forEach((s, aDouble) -> {
            XYChart.Series<String, Double> catSeries = new XYChart.Series<>(s, FXCollections.observableArrayList(new XYChart.Data<>("", aDouble)));
            bc.getData().add(catSeries);
        });



        // add the data to pie chart
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();
        month.getOverview().forEach((s, aDouble) -> {
            PieChart.Data cat = new PieChart.Data(s, aDouble);
            pieData.add(cat);
        });


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
    

