import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import core.entities.Payment;
import core.gateways.Storage;

public class StorageTest {

	//ID will always need to be unique for each payment entered
	private int idtest = 4;		//so change this every run at least
	private double valuetest = 399.04;
	private boolean statustest = false;
	private String categorytest = "Gas";
	private String duedatetest = "03212001";
	
	@Test
	public void SaveTest() {
		Payment payment = new Payment(idtest, valuetest, statustest, categorytest, duedatetest);
		Storage store = new Storage();
		store.savePayment(payment, payment.getID());
		
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
	public void LoadTest() {
		Storage store = new Storage();
		Payment paymentTemp;
		paymentTemp = store.paymentByID(idtest);
		assertTrue(paymentTemp.getID() == idtest);
		assertTrue(paymentTemp.getValue() == valuetest);
		assertTrue(paymentTemp.getStatus() == statustest);
		assertTrue(paymentTemp.getCategory().compareTo(categorytest) == 0);
		assertTrue(paymentTemp.getDueDate().compareTo(duedatetest) == 0);
	}
	
	@Test
	public void listTest() {
		Storage store = new Storage();
		assertTrue(store.getUnFinishedPayments().get(0).getStatus() == false);
	}
}
