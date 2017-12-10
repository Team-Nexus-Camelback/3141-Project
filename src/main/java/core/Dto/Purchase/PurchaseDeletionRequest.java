package core.Dto.Purchase;

import core.gateways.IRequest;



/**
 * Created by ryan on 11/14/17.
 */
public class PurchaseDeletionRequest implements IRequest {
    private int id;
    private String monthDate;

    public PurchaseDeletionRequest(String monthDate, int id) {
        this.id = id;
        this.monthDate = monthDate;
    }

    public String getMonthDate() {
        return monthDate;
    }

    public int getId() {
        return id;
    }
}
