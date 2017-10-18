package core.gateways;

import java.util.List;
import java.util.Properties;
import core.entities.Payment;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

//Assuming "Payments" is a data structure that have a (Payment Value, Payment due date, Payment Category,
//and a Payment Has Been Paid) properties.
//This class will store this data using a payment id that is linked with each of the values in seperate
//property files.

//for example if payment id: 123 has a value of $100, is due 03/19/2017, with Food Category, and has not been paid
//then the id of 123 will be linked with the value of 100 in the "values.data" file, linked with 03192017 in the 
//"due.data" file and linked with Food in the "category.data" file and linked with false in the "paid.data" file.

public class Storage implements PaymentRepository {

	// File names that will hold the various values for each payment
	// Each unique payment id will have an item in each data file
	private String valueFile = "values.data";
	private String dateFile = "due.data";
	private String categoryFile = "category.data";
	private String paidFile = "paid.data";

	// Initialize Output Streams for each payment property
	private OutputStream outputVal = null;
	private OutputStream outputDate = null;
	private OutputStream outputCat = null;
	private OutputStream outputPaid = null;

	// Property for each of the payment properties
	Properties storeVal = new Properties();
	Properties storeDueDate = new Properties();
	Properties storeCategory = new Properties();
	Properties storePaid = new Properties();

	@Override
	public List<Payment> getUnFinishedPayments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Payment paymentByID(int id) {
		// TODO: Implement
		Payment paymentTemp;
		return null;
	}

	// store payment value, category, duedate, and whether its paid in seperate data
	// files.
	@Override
	public boolean savePayment(Payment payment, int id) {
		try {
			// set the output File
			outputVal = new FileOutputStream(valueFile, true);
			outputDate = new FileOutputStream(dateFile,true);
			outputCat = new FileOutputStream(categoryFile,true);
			outputPaid = new FileOutputStream(paidFile,true);
			
			// set the values to be stored in the File
			storeVal.setProperty(Integer.toString(id), Double.toString(payment.getValue()));
			storeDueDate.setProperty(Integer.toString(id), Integer.toString(payment.getDueDate()));
			storeCategory.setProperty(Integer.toString(id), payment.getCategory());
			storePaid.setProperty(Integer.toString(id), Boolean.toString(payment.getStatus()));
			
			// save the properties File
			storeVal.store(outputVal, null);
			storeDueDate.store(outputDate, null);
			storeCategory.store(outputCat, null);
			storePaid.store(outputPaid, null);
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
			
		} finally {
			// close the outputs after done appending the file
			try {
				outputVal.close();
				outputDate.close();
				outputCat.close();
				outputPaid.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	// remove a payment from the data by using its id
	public void removePayment(int id) {
		// TODO: method to remove payments using a payment id
	}

	// returns the value of the payment with id
	public double grabValue(int id) {
		// TODO: Implement
		return 0.0;
	}

	// return the category of the payment with id
	public String grabCategory(int id) {
		// TODO: Implement
		return null;
	}

	// return the duedate of the payment with id
	public int grabDate(int id) {
		// TODO: Implement
		return 0;
	}

	// return whether payment was paid or not
	public boolean grabStatus(int id) {
		// TODO: Implement
		return false;
	}

}
