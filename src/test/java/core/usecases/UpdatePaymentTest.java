package core.usecases;

import core.Dto.Payment.PaymentKeys;
import core.Dto.Payment.PaymentResponseMessage;
import core.Dto.Payment.PaymentUpdateRequest;
import core.entities.Payment;
import core.gateways.PaymentRepository;

import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by ryan on 11/21/17.
 */
public class UpdatePaymentTest {


    private UpdatePayment setupTestClass(){
        try {
            return new UpdatePayment(new PaymentRepository() {
                private Payment testPayment = new Payment(0,"Test", 420, "1/17/18");
                @Override
                public List<Payment> getUnFinishedPayments() {
                    return null;
                }

                @Override
                public Payment paymentByID(int id) {
                    return testPayment;
                }

                @Override
                public boolean savePayment(Payment payment, int id) {
                    testPayment = payment;
                    return true;
                }
            });
        } catch (Exception e) {
            System.out.print("You screwed up");
        }
        return null;
    }

    @Test
    public void simpleRequest(){
        UpdatePayment test = setupTestClass();
        PaymentUpdateRequest request = new PaymentUpdateRequest(0, true, "1/17/18","bar",420);
        PaymentResponseMessage responseMessage = test.handleRequest(request);
        assertTrue(responseMessage.isSuccessful());
        assertTrue(responseMessage.getMessage().get(0).get(PaymentKeys.NAME.getKeyName()).equals("bar"));
    }
}