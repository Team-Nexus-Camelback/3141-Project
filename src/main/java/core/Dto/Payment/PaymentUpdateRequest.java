package core.Dto.Payment;

import core.gateways.IRequest;

/**
 * Created by ryan on 11/18/17.
 */
public class PaymentUpdateRequest implements IRequest {
    private int id;
    private boolean isPaid;
    private String date;
    private String name;
    private double amount;

    public PaymentUpdateRequest(int id, boolean isPaid, String date, String name, double amount) {
        this.id = id;
        this.isPaid = isPaid;
        this.date = date;
        this.name = name;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }
}
