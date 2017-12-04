package api;

import core.Dto.Payment.PaymentCreationRequest;
import core.Dto.Payment.PaymentDeletionRequest;
import core.Dto.Payment.PaymentUpdateRequest;
import core.gateways.PaymentRepository;
import core.usecases.*;
import models.Payment;

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

    public void setRepo(PaymentRepository repo){
        getPaymentInfo = new PaymentInfoInterator(repo);
        updatePayment = new UpdatePayment(repo);
        deletePayment = new DeletePayment(repo);
    }
}
