package api;

import core.gateways.PaymentStorage;

import models.Payment;
import org.junit.jupiter.api.Test;


import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by ryan on 12/4/17.
 */
class PaymentManagerTest {

    @Test
    public void simpleSaveTest(){
        PaymentManager.getInstance().setRepo(new PaymentStorage());
        Payment testPayment = new Payment(0, "Taxes", 200, new Date(2017,4,20));
        PaymentManager.getInstance().savePayment(testPayment);
        List<Payment> payments = PaymentManager.getInstance().getUnfinishedPayments();
        assertTrue(payments.get(0).getAmount() == testPayment.getAmount());
    }
    @Test
    public void simpleGetTest(){
        PaymentManager.getInstance().setRepo(new PaymentStorage());
        List<Payment> payments = PaymentManager.getInstance().getUnfinishedPayments();
        assertTrue(payments.get(0) != null);
        assertTrue(payments.get(0).getAmount() == 200);
    }

}