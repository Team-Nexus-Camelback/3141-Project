package core.Dto.Purchase;

import core.gateways.IRequest;

/**
 * Created by ryan on 11/13/17.
 */
public class PurchaseUpdateRequest implements IRequest, PurchaseRequest {
    private Double amount;
    private String category;
    private String date;
    private int id;

    public PurchaseUpdateRequest(int id, double amount, String category, String date) {
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.id = id;
    }

    @Override
    public Double getAmount() {
        return amount;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public int getId() {
        return id;
    }

}
