package core.usecases;

import core.Dto.Payment.PaymentKeys;
import core.Dto.Payment.PaymentResponseMessage;
import core.Dto.Payment.PaymentUpdateRequest;
import core.entities.Payment;
import core.entities.PaymentFactory;
import core.gateways.IRequestHandler;
import core.gateways.PaymentRepository;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ryan on 11/18/17.
 */
public class UpdatePayment extends AbstractHandler<PaymentUpdateRequest, PaymentResponseMessage> {
    private PaymentRepository repository;

    public UpdatePayment(PaymentRepository repository) {
        this.repository = repository;
    }

    @Override
    public PaymentResponseMessage handleRequest(PaymentUpdateRequest request) {
        Payment currentPayment = repository.paymentByID(request.getId());
        try {
            Payment updatedPayment = PaymentFactory.updatePayment(currentPayment, request.getName(), request.getAmount(), request.getDate());
            repository.savePayment(updatedPayment, updatedPayment.getId());
            ArrayList<HashMap<String ,String>> responseData = new ArrayList<>();
            responseData.add(createResponseData(updatedPayment));
            return new PaymentResponseMessage(responseData);
        } catch (ParseException e) {
            return errorResponse("Date format is incorrect");
        }
    }

    @Override
    protected PaymentResponseMessage errorResponse(String errorMessage) {
        ArrayList<HashMap<String, String>> response = new ArrayList<>();
        HashMap<String, String> map = new HashMap<>();
        map.put(PaymentKeys.ERROR.getKeyName(), errorMessage);
        response.add(map);
        return new PaymentResponseMessage(response);
    }

    private HashMap<String,String> createResponseData(Payment payment) {
        HashMap<String, String> responseData = new HashMap<>();
        responseData.put(PaymentKeys.NAME.getKeyName(), payment.getPaymentName());
        responseData.put(PaymentKeys.AMOUNT.getKeyName(), String.valueOf(payment.getAmount()));
        responseData.put(PaymentKeys.DUE_DATE.getKeyName(), payment.getDueDate());
        return responseData;
    }
}
