package core.Dto.Purchase;

import core.gateways.IRequest;


/**
 * Created by ryan on 10/17/17.
 * Use case for creating purchase, successfully creating a purchase will return the purchase saved
 */
public class PurchaseCreationRequest implements IRequest, PurchaseRequest {
    private double amount;
    private String category;
    private String date;
    private int id;
    private String name;

    public PurchaseCreationRequest(int id, String name, double amount, String category, String date) {
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.id = id;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public Double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }

    public int getId() {
        return id;
    }
}
