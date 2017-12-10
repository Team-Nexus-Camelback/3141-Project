package core.Dto.Payment;

import core.gateways.IRequest;

/**
 * Created by ryan on 11/18/17.
 */
public class PaymentDeletionRequest implements IRequest {
    private int id;

    public PaymentDeletionRequest(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
