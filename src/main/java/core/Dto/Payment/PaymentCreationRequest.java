package core.Dto.Payment;

import core.gateways.IRequest;

import java.util.Date;

/**
 * Created by ryan on 11/29/17.
 */
public class PaymentCreationRequest implements IRequest {
    private String name;
    private double amount;
    private Date dueDate;

    public PaymentCreationRequest(String name, double amount, Date dueDate) {
        this.name = name;
        this.amount = amount;
        this.dueDate = dueDate;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDueDate() {
        return dueDate;
    }
}
