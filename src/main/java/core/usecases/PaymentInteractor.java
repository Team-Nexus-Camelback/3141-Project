package core.usecases;

import core.Dto.Payment.PaymentKeys;
import core.Dto.Payment.PaymentResponseMessage;
import core.entities.Payment;
import core.gateways.IRequest;
import core.gateways.PaymentRepository;

import javax.imageio.event.IIOReadProgressListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ryan on 11/29/17.
 */
public abstract class PaymentInteractor<I extends IRequest> extends AbstractHandler<I, PaymentResponseMessage> {
    protected PaymentRepository repository;

    public PaymentInteractor(PaymentRepository repository) {
        this.repository = repository;
    }

    protected HashMap<String, String> createResponseData(Payment payment) {
        HashMap<String, String> responseData = new HashMap<>();
        responseData.put(PaymentKeys.NAME.getKeyName(), payment.getPaymentName());
        responseData.put(PaymentKeys.AMOUNT.getKeyName(), String.valueOf(payment.getAmount()));
        responseData.put(PaymentKeys.DUE_DATE.getKeyName(), payment.getDueDate());
        return responseData;
    }

    @Override
    protected PaymentResponseMessage errorResponse(String error) {
        HashMap<String, String> errorMessage = new HashMap<>();
        errorMessage.put(PaymentKeys.ERROR.getKeyName(), error);
        ArrayList<HashMap<String, String>> responseMessage = new ArrayList<>();
        responseMessage.add(errorMessage);
        return new PaymentResponseMessage(responseMessage, false);


    }
}