package core.usecases;

import core.Dto.Payment.PaymentKeys;
import core.Dto.Payment.PaymentResponseMessage;
import core.Dto.Payment.PaymentUpdateRequest;
import core.entities.Payment;
import core.entities.PaymentFactory;
import core.gateways.IRequestHandler;
import core.gateways.PaymentRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ryan on 11/18/17.
 */
public class UpdatePayment extends PaymentInteractor<PaymentUpdateRequest> {
    public UpdatePayment(PaymentRepository repository) {
        super(repository);
    }


    @Override
    public PaymentResponseMessage handleRequest(PaymentUpdateRequest request) {
        try {
            Payment currentPayment = repository.paymentByID(request.getId());
            Payment updatedPayment = PaymentFactory.updatePayment(currentPayment, request.getName(), request.getAmount(), DateFormat.getDateInstance(DateFormat.SHORT).parse(request.getDate()));
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


}
