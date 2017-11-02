package core.usecases;

import core.Dto.Payment.PaymentKeys;
import core.Dto.Payment.PaymentRequestMessage;
import core.Dto.Payment.PaymentResponseMessage;
import core.entities.Payment;
import core.gateways.IRequestHandler;
import core.gateways.PaymentRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ryan on 10/16/17.
 * Handles fetching payments from the repo and sending then in a response message
 */
public class PaymentInfoInterator  implements IRequestHandler<PaymentRequestMessage, PaymentResponseMessage>{

    private PaymentRepository paymentRepository;

    public PaymentInfoInterator(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }


    @Override
    public PaymentResponseMessage handleRequest(PaymentRequestMessage request) {
        if (request.needsAllPayments())
            return responseForAllPayments(paymentRepository.getUnFinishedPayments());

        return responseForCertainPayments(request.getPayments());

    }

    private PaymentResponseMessage responseForCertainPayments(List<Integer> payments) {
        if (payments.isEmpty())
            return errorResponse("Did not specify a payment");

        ArrayList<HashMap<String, String>> listOfPaymentInfo = new ArrayList<>();
        for(int id : payments){
            Payment payment = paymentRepository.paymentByID(id);
            listOfPaymentInfo.add(createResponseData(payment));
        }
        return new PaymentResponseMessage(listOfPaymentInfo);
    }

    private PaymentResponseMessage errorResponse(String error) {
        HashMap<String,String> errorMessage = new HashMap<>();
        errorMessage.put(PaymentKeys.ERROR.getKeyName(), error);
        ArrayList<HashMap<String, String>> responseMessage = new ArrayList<>();
        responseMessage.add(errorMessage);
        return new PaymentResponseMessage(responseMessage, false);
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

    private HashMap<String,String> createResponseData(Payment payment) {
        HashMap<String, String> responseData = new HashMap<>();
        responseData.put(PaymentKeys.NAME.getKeyName(), payment.getPaymentName());
        responseData.put(PaymentKeys.AMOUNT.getKeyName(), String.valueOf(payment.getAmount()));
        responseData.put(PaymentKeys.DUE_DATE.getKeyName(), payment.getDueDate());
        return responseData;
    }
}
