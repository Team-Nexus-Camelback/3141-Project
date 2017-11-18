package core.Dto.Purchase;

import java.util.Date;

/**
 * Created by ryan on 11/14/17.
 */
public class PurchaseDeletionRequest implements PurchaseRequest {
    private String amount;
    private String category;
    private String date;
    private int id;


    public PurchaseDeletionRequest(String amount, String category, String date, int id) {
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.id = id;
    }

    @Override
    public String getAmount() {
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
