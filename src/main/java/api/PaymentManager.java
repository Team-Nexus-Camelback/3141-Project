package api;

import core.gateways.PaymentRepository;
import core.usecases.DeletePayment;
import core.usecases.DeletePurchase;
import core.usecases.PaymentInfoInterator;
import core.usecases.UpdatePayment;
import models.Payment;

/**
 * Created by ryan on 11/15/17.
 */
public class PaymentManager {
    private static PaymentManager instance = new PaymentManager();
    private PaymentInfoInterator getPaymentInfo;
    private UpdatePayment updatePayment;
    private DeletePayment deletePayment;
    public static PaymentManager getInstance(){
        return instance;
    }

    private PaymentManager() {
    }

    public  void savePayment(Payment payment){
        
    }

    public void setRepo(PaymentRepository repo){
        getPaymentInfo = new PaymentInfoInterator(repo);
        updatePayment = new UpdatePayment(repo);
        deletePayment = new DeletePayment(repo);
    }
}
