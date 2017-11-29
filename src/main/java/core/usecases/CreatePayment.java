package core.usecases;

import core.Dto.Payment.PaymentCreationRequest;
import core.Dto.Payment.PaymentRequestMessage;

/**
 * Created by ryan on 11/29/17.
 */
public class CreatePayment extends AbstractHandler<PaymentCreationRequest, PaymentRequestMessage> {
    @Override
    public PaymentRequestMessage handleRequest(PaymentCreationRequest request) {
        return null;
    }

    @Override
    protected PaymentRequestMessage errorResponse(String errorMessage) {
        return null;
    }
}
