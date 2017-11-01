package core.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
     * Created by Ryan Philipps on 10/13/17.
     * Date Last Modified: 10/16/17 by Cody Koski
     * The class representation of a 'bill', or 'payment' within the system
     */
public class Payment {
    private String paymentName;
    private double amount;
    private Date dueDate;
    private int id;
    private boolean  isPaid;

    /**
     *
     * @param paymentName
     * @param amount
     * @param dueDate should be in the format of dd-mm-yyyy;
     */
    public Payment(int id, String paymentName, double amount, String dueDate) throws ParseException {
        this.id = id;
        this.paymentName = paymentName;
        this.amount = amount;
        this.dueDate = getDateFromString(dueDate);
    }

    private Date getDateFromString(String dueDateString) throws ParseException {
        return DateFormat.getInstance().parse(dueDateString);
    }

    public int daysTillDueDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        Date today = calendar.getTime();
        return today.compareTo(dueDate);
    }

    public String getPaymentName() {
        return paymentName;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public int getId() {
        return id;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void payPayment(){
        isPaid = true;
    }
}

