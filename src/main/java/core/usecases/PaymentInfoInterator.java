package core.usecases;

import core.Dto.Payment.PaymentRequestMessage;
import core.Dto.Payment.PaymentResponseMessage;
import core.entities.Payment;
import core.gateways.IRequestHandler;
import core.gateways.PaymentRepository;

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
        for(int id : payments){
            Payment payment = paymentRepository.paymentByID(id);
            // TODO wait for payment class
        }

        return null;
    }

    private PaymentResponseMessage responseForAllPayments(List<Payment> unFinishedPayments) {
        // TODO wait for how the payment class will be made
        return null;
    }
}
