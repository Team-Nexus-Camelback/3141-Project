package core.usecases;

import core.Dto.Payment.PaymentKeys;
import core.Dto.Payment.PaymentRequestMessage;
import core.Dto.Payment.PaymentResponseMessage;
import core.entities.Payment;
import core.gateways.IRequestHandler;
import core.gateways.PaymentRepository;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ryan on 10/16/17.
 * Handles fetching payments from the repo and sending then in a response message
 */
public class PaymentInfoInterator extends PaymentInteractor<PaymentRequestMessage>{

    public PaymentInfoInterator(PaymentRepository paymentRepository) {
        super(paymentRepository);
    }

    @Override
    public PaymentResponseMessage handleRequest(PaymentRequestMessage request) {
        try{
            if (request.needsAllPayments())
                return responseForAllPayments(repository.getUnFinishedPayments());
            return responseForCertainPayments(request.getPayments());
        } catch (ParseException e) {
            return errorResponse("Wrong date format");
        }
    }

    private PaymentResponseMessage responseForCertainPayments(List<Integer> payments) throws ParseException {
        if (payments.isEmpty())
            return errorResponse("Did not specify a payment");

        ArrayList<HashMap<String, String>> listOfPaymentInfo = new ArrayList<>();
        for(int id : payments){
            Payment payment = repository.paymentByID(id);
            listOfPaymentInfo.add(createResponseData(payment));
        }
        return new PaymentResponseMessage(listOfPaymentInfo);
    }

    private PaymentResponseMessage responseForAllPayments(List<Payment> unFinishedPayments) {
        if (unFinishedPayments.isEmpty())
            return errorResponse("There are no payment within the system");

        ArrayList<HashMap<String, String>> paymentInfoList = new ArrayList<>();
        for (Payment payment : unFinishedPayments){
            HashMap<String, String> paymentMessage = createResponseData(payment);
            paymentInfoList.add(paymentMessage);
         }
         return new PaymentResponseMessage(paymentInfoList);
    }

}
