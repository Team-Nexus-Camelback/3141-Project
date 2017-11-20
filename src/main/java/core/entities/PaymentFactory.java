package core.entities;

import java.text.ParseException;

/**
 * Created by ryan on 11/19/17.
 */
public class PaymentFactory {

    public static Payment updatePayment(Payment current, String paymentName, double amount, String dueDate) throws ParseException {
        return new Payment(current.getId(), paymentName, amount, dueDate);
    }
}
