package usecases;

import core.Dto.Payment.PaymentKeys;
import core.Dto.Payment.PaymentRequestMessage;
import core.Dto.Payment.PaymentResponseMessage;
import core.entities.Payment;
import core.gateways.PaymentRepository;
import core.usecases.PaymentInfoInterator;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by ryan on 11/2/17.
 */
class PaymentInfoInteratorTest {

    private PaymentInfoInterator getTestableInteractor(){
        return new PaymentInfoInterator(new PaymentRepository() {
            @Override
            public List<Payment> getUnFinishedPayments() {
                ArrayList<Payment> testList = new ArrayList<>();
                try {
                    testList.add(new Payment(0, "Car Payment", 350.34, "02-11-2017"));
                    return  testList;
                }
                catch (ParseException e){
                    return null;
                }


            }

            @Override
            public Payment paymentByID(int id) {
                try {
                    return new Payment(0, "Car Payment", 350.34, "11/2/17");
                }
                catch (ParseException e){
                    return null;
                }
            }

            @Override
            public boolean savePayment(Payment payment, int id) {
                return false;
            }

            @Override
            public boolean deletePaymentByID(int id) {
                return false;
            }


        });
    }

    @Test
    public void simpleRequest(){
        PaymentInfoInterator testInteractor = getTestableInteractor();
        PaymentRequestMessage request = new PaymentRequestMessage(0);
        PaymentResponseMessage response = testInteractor.handleRequest(request);
        assertFalse(response.getMessage().isEmpty());
        assertTrue(response.getMessage().size() == 1);
        assertTrue(response.getMessage().get(0).get(PaymentKeys.NAME.getKeyName()).equals("Car Payment"));
        assertTrue(response.getMessage().get(0).get(PaymentKeys.AMOUNT.getKeyName()).equals("350.34"));
    }

}