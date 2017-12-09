package core.entities;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ryan on 10/17/17.
 */
public class Purchase {
    private double amount;
    private Date purchaseDate;
    private String purchaseName;
    private String category;
    private int id;

    public Purchase(int id, String name,  double amount, String category) {
        this.id = id;
        this.amount = amount;
        this.category = category;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        this.purchaseDate = calendar.getTime();
        this.purchaseName = name;
    }

    public Purchase(double amount, Date purchaseDate, String purchaseName, String category, int id) {
        this.amount = amount;
        this.purchaseDate = purchaseDate;
        this.purchaseName = purchaseName;
        this.category = category;
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public String getPurchaseName() {
        return purchaseName;
    }

    public String getCategory() {
        return category;
    }

    public int getId() {
        return id;
    }

    public Date getDate(){
        return purchaseDate;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    @Override
    public String toString() {
        return "id " + id + " name " + purchaseName + " category " + category + " amount " + amount
                    + " date " + DateFormat.getInstance().format(purchaseDate);
    }
}
