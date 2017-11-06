package models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import java.util.HashMap;

/**
 * Created by ryan on 11/5/17.
 */
public class Month {
    private SimpleStringProperty monthDate;
    private SimpleDoubleProperty spendingAmount;
    private SimpleListProperty<Purchase> purchases;
    private SimpleListProperty<Payment> payments;
    private SimpleMapProperty<String, Double> categories;

    public Month(String date, double budgetAmount, HashMap<String, Double> categories) {
        this.monthDate = new SimpleStringProperty(date);
        this.spendingAmount = new SimpleDoubleProperty(budgetAmount);
        ObservableMap<String, Double> observableMap = FXCollections.observableMap(categories);
        this.categories = new SimpleMapProperty<>(observableMap);


    }

    public String getMonthDate() {
        return monthDate.get();
    }

    public SimpleStringProperty monthDateProperty() {
        return monthDate;
    }

    public double getSpendingAmount() {
        return spendingAmount.get();
    }

    public SimpleDoubleProperty spendingAmountProperty() {
        return spendingAmount;
    }

    public ObservableList<Purchase> getPurchases() {
        return purchases.get();
    }

    public SimpleListProperty<Purchase> purchasesProperty() {
        return purchases;
    }

    public ObservableList<Payment> getPayments() {
        return payments.get();
    }

    public SimpleListProperty<Payment> paymentsProperty() {
        return payments;
    }

    public ObservableMap<String, Double> getCategories() {
        return categories.get();
    }

    public SimpleMapProperty<String, Double> categoriesProperty() {
        return categories;
    }
}
