package api;

import core.Dto.Payment.*;
import core.gateways.PaymentRepository;
import core.usecases.*;
import models.Payment;

import java.util.List;

/**
 * Created by ryan on 11/15/17.
 */
public class PaymentManager {
    private static PaymentManager instance = new PaymentManager();
    private PaymentInfoInterator getPaymentInfo;
    private UpdatePayment updatePayment;
    private DeletePayment deletePayment;
    private CreatePayment createPayment;
    public static PaymentManager getInstance(){
        return instance;
    }

    private PaymentManager() {
    }

    public  void savePayment(Payment payment){
        PaymentCreationRequest request = new PaymentCreationRequest(payment.getName(), payment.getAmount(), payment.getDueDate());
        createPayment.handleRequest(request);
    }

    public void payPayment(Payment payment){
        PaymentUpdateRequest request = new PaymentUpdateRequest(payment.getId(), true, payment.getDueDate().toString(),
                payment.getName(), payment.getAmount());
        updatePayment.handleRequest(request);
    }

    public void deletePayment(Payment payment){
        PaymentDeletionRequest request = new PaymentDeletionRequest(payment.getId());
        deletePayment.handleRequest(request);
    }

    public List<Payment> getUnfinishedPayments(){
        PaymentRequestMessage requestMessage = new PaymentRequestMessage(true);
        PaymentResponseMessage responseMessage = getPaymentInfo.handleRequest(requestMessage);
        return ResponseTranslator.paymentsFromResponse(responseMessage.getMessage().toString());
    }

    public void setRepo(PaymentRepository repo){
        getPaymentInfo = new PaymentInfoInterator(repo);
        updatePayment = new UpdatePayment(repo);
        deletePayment = new DeletePayment(repo);
        createPayment = new CreatePayment(repo);
    }
}
