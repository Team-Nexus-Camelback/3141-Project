package core.usecases;

import core.Dto.Payment.PaymentDeletionRequest;
import core.Dto.Payment.PaymentKeys;
import core.Dto.Payment.PaymentResponseMessage;
import core.entities.Payment;
import core.gateways.PaymentRepository;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ryan on 11/18/17.
 */
public class DeletePayment extends AbstractHandler<PaymentDeletionRequest, PaymentResponseMessage> {

    private PaymentRepository repository;

    @Override
    public PaymentResponseMessage handleRequest(PaymentDeletionRequest request) {
        Payment paymentToDelete = repository.paymentByID(request.getId());
        if (!repository.deletePaymentByID(request.getId()))
            return errorResponse("Payment was not properly deleted");
        return null;
    }

    @Override
    protected PaymentResponseMessage errorResponse(String errorMessage) {
        HashMap<String, String> errorData = new HashMap<>();
        errorData.put(PaymentKeys.ERROR.getKeyName(), errorMessage);
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        list.add(errorData);
        return new PaymentResponseMessage(list);
    }
}
