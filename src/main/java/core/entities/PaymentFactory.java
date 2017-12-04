package core.entities;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by ryan on 11/19/17.
 */
public class PaymentFactory {

    public static Payment updatePayment(Payment current, String paymentName, double amount, String dueDate) throws ParseException {
        return new Payment(current.getId(), paymentName, amount, dueDate);
    }

    public static Payment createNewPayment(int id, String name, double amount, Date dueDate) throws ParseException {
        return new Payment(id, name, amount, dueDate.toString());
    }
}
