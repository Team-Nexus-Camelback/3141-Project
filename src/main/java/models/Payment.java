package models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ryan on 11/5/17.
 */
public class Payment {
    private SimpleStringProperty name;
    private SimpleDoubleProperty amount;
    private SimpleObjectProperty<Date> dueDate;

    public Payment(String name, double amount, String dueDate) throws ParseException {
        this.name = new SimpleStringProperty(name);
        this.amount = new SimpleDoubleProperty(amount);
        Date date = DateFormat.getInstance().parse(dueDate);
        this.dueDate = new SimpleObjectProperty<>(date);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public double getAmount() {
        return amount.get();
    }

    public SimpleDoubleProperty amountProperty() {
        return amount;
    }

    public Date getDueDate() {
        return dueDate.get();
    }

    public SimpleObjectProperty<Date> dueDateProperty() {
        return dueDate;
    }
}
