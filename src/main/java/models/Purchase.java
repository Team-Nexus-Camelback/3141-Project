package models;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Ryan on 11/2/2017.
 */
public class Purchase {
    SimpleFloatProperty Amount;
    SimpleStringProperty Date;
    SimpleStringProperty Category;
    SimpleStringProperty Name;

    public Purchase(float amount, String date, String category, String name){
        Amount = new SimpleFloatProperty(amount);
        Date = new SimpleStringProperty(date);
        Category = new SimpleStringProperty(category);
        Name = new SimpleStringProperty(name);
    }

    public float getAmount() {
        return Amount.get();
    }

    public SimpleFloatProperty amountProperty() {
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
}
