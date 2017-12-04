package models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Ryan on 11/2/2017.
 */
public class Purchase {
    SimpleDoubleProperty Amount;
    SimpleStringProperty Date;
    SimpleStringProperty Category;
    SimpleStringProperty Name;
    private int id;

    public Purchase(int id, double amount, String date, String category, String name){
        Amount = new SimpleDoubleProperty(amount);
        Date = new SimpleStringProperty(date);
        Category = new SimpleStringProperty(category);
        Name = new SimpleStringProperty(name);
        this.id = id;
    }

    public double getAmount() {
        return Amount.get();
    }

    public SimpleDoubleProperty amountProperty() {
        return Amount;
    }

    public void setAmount(float amount) {
        this.Amount.set(amount);
    }

    public String getDate() {
        return Date.get();
    }

    public SimpleStringProperty dateProperty() {
        return Date;
    }

    public void setDate(String date) {
        this.Date.set(date);
    }

    public String getCategory() {
        return Category.get();
    }

    public SimpleStringProperty categoryProperty() {
        return Category;
    }

    public void setCategory(String category) {
        this.Category.set(category);
    }

    public String getName() {
        return Name.get();
    }

    public SimpleStringProperty nameProperty() {
        return Name;
    }

    public void setName(String name) {
        this.Name.set(name);
    }

    public int getId() {
        return id;
    }
}
