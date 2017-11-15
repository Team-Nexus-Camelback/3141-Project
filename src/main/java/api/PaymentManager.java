package api;

import core.gateways.PaymentRepository;
import core.usecases.PaymentInfoInterator;
import models.Payment;

/**
 * Created by ryan on 11/15/17.
 */
public class PaymentManager {
    private static PaymentManager instance = new PaymentManager();
    private PaymentInfoInterator getPaymentInfo;
    public static PaymentManager getInstance(){
        return instance;
    }

    private PaymentManager() {
    }

    public static void savePayment(Payment payment){

    }

    public void setRepo(PaymentRepository repo){
        getPaymentInfo = new PaymentInfoInterator(repo);
    }
}
