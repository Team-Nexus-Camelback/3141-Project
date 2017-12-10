
import static org.junit.Assert.*;

import java.io.File;
import java.text.ParseException;
import java.util.Date;
import java.text.DateFormat;

import org.junit.Test;

import core.entities.Payment;
import core.gateways.PaymentStorage;

public class PaymentStorageTest {

	//ID will always need to be unique for each payment entered
	private int idtest = 4;		//so change this every run at least
	private double valuetest = 399.04;
	private boolean statustest = false;
	private String categorytest = "Gas";
	private Date now = new Date();
	DateFormat shortdf = DateFormat.getDateInstance(DateFormat.SHORT);
	private String duedatetest = shortdf.format(now);
	
	@Test
	public void SaveTest() throws ParseException {
		Payment payment = new Payment(idtest, categorytest, valuetest, DateFormat.getDateInstance(DateFormat.SHORT).parse(duedatetest));
		PaymentStorage store = new PaymentStorage();
		store.savePayment(payment, payment.getId());
		
		File testvalues = new File("values.data");
		File testdue = new File("due.data");
		File testcat = new File("category.data");
		File testpay = new File("paid.data");
		assertTrue(testvalues.exists());
		assertTrue(testdue.exists());
		assertTrue(testcat.exists());
		assertTrue(testpay.exists());
	}
	
	@Test
	public void LoadTest() throws ParseException {
		PaymentStorage store = new PaymentStorage();
		Payment paymentTemp;
		paymentTemp = store.paymentByID(idtest);
		assertTrue(paymentTemp.getId() == idtest);
		assertTrue(paymentTemp.getAmount() == valuetest);
		assertTrue(paymentTemp.isPaid() == statustest);
		assertTrue(paymentTemp.getPaymentName().compareTo(categorytest) == 0);
		assertTrue(paymentTemp.getDueDate().compareTo(duedatetest) == 0);
	}
	
	@Test
	public void listTest() throws NumberFormatException, ParseException {
		PaymentStorage store = new PaymentStorage();
		assertTrue(store.getUnFinishedPayments().get(0).isPaid() == false);
	}
}

