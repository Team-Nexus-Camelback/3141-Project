package core.Dto.Purchase;

import core.gateways.IRequest;



/**
 * Created by ryan on 11/14/17.
 */
public class PurchaseDeletionRequest implements IRequest {
    private int id;

    public PurchaseDeletionRequest(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }
}
