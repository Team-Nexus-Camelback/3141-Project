package core.usecases;

import core.Dto.Payment.PaymentCreationRequest;
import core.Dto.Payment.PaymentResponseMessage;
import core.entities.Payment;
import core.entities.PaymentFactory;
import core.gateways.PaymentRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by ryan on 11/29/17.
 */
public class CreatePayment extends PaymentInteractor<PaymentCreationRequest> {

    public CreatePayment(PaymentRepository repository) {
        super(repository);
    }

    @Override
    public PaymentResponseMessage handleRequest(PaymentCreationRequest request) {
        //TODO create a payment id system
        Payment newPayment = PaymentFactory.createNewPayment(0, request.getName(), request.getAmount(), request.getDueDate());
        if (!repository.savePayment(newPayment, 0))
            return errorResponse("Payment did not save correctly");
        ArrayList<HashMap<String, String>> data = new ArrayList<>();
        data.add(createResponseData(newPayment));
        return new PaymentResponseMessage(data, true);

    }
}
