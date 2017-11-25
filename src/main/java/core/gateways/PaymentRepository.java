package core.gateways;

import core.entities.Payment;

import java.text.ParseException;
import java.util.List;

/**
 * Created by ryan on 10/13/17.
 * The Contract that the data storage needs to follow
 */
public interface PaymentRepository {
	
    /**
     * Get payments that are not paid
     * 
     * @return a list of payments that are unpaid
     * @throws ParseException 
     * @throws NumberFormatException 
     */
    List<Payment> getUnFinishedPayments() throws NumberFormatException, ParseException;

    /**
     * Get a payment by looking it up with its unique id
     * Each payment holds a value,category,due date,and paid boolean
     * 
     * @param id
     * @return Payment
     * @throws ParseException 
     */
    Payment paymentByID(int id) throws ParseException;

    /**
     * Store a payment with a unique id
     * 
     * @param payment
     * @param id
     * @return true if the payment was saved otherwise false
     */
    boolean savePayment(Payment payment, int id);

}
