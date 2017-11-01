package core.gateways;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import core.entities.Payment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

//Assuming "Payments" has (Payment Value, Payment due date, Payment Category,
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

	// Initialize input streams for each payment property
	private InputStream inputVal = null;
	private InputStream inputDate = null;
	private InputStream inputCat = null;
	private InputStream inputPaid = null;

	// Property for each of the payment properties
	Properties storeVal = new Properties();
	Properties storeDueDate = new Properties();
	Properties storeCategory = new Properties();
	Properties storePaid = new Properties();

	// Returns an arraylist of payments that are not paid (failed status) yet.
	@Override
	public List<Payment> getUnFinishedPayments() {
		// initialize arraylist that is returned at the end
		ArrayList<Payment> paylist = new ArrayList<Payment>();
		try {

			// inputstream
			inputPaid = new FileInputStream(paidFile);

			// load the properties file that holds the paid status for each payment
			storePaid.load(inputPaid);

			// store the keys into an array to get the id's of payments
			String[] idarray = new String[storePaid.keySet().size()];
			storePaid.keySet().toArray(idarray);

			// check each id to see if the value is false then add to payments list
			// by using the paymentByID() method to get the payment
			for (int i = 0; i < idarray.length; i++) {
				if (storePaid.getProperty(idarray[i]).equals("false")) {
					paylist.add(paymentByID(Integer.parseInt(idarray[i])));
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputPaid.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return paylist;
	}

	@Override
	public Payment paymentByID(int id) {
		try {
			String stringID = Integer.toString(id);

			// set input from file
			inputVal = new FileInputStream(valueFile);
			inputDate = new FileInputStream(dateFile);
			inputCat = new FileInputStream(categoryFile);
			inputPaid = new FileInputStream(paidFile);

			// load the property files from the file location
			storeVal.load(inputVal);
			storePaid.load(inputPaid);
			storeCategory.load(inputCat);
			storeDueDate.load(inputDate);

			// set to temporary variables using the stringID key from each property file
			// all values pulled from the property file are a string so they must be parsed
			// to
			// respective data type.
			double tempVal = Double.parseDouble(storeVal.getProperty(stringID));
			boolean tempStat = Boolean.parseBoolean(storePaid.getProperty(stringID));
			String tempCat = storeCategory.getProperty(stringID);
			String tempDue = storeDueDate.getProperty(stringID);

			// return a payment object
			Payment paymentTemp = new Payment(id, tempVal, tempStat, tempCat, tempDue);
			return paymentTemp;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputVal.close();
				inputDate.close();
				inputCat.close();
				inputPaid.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// if try block fails
		return null;
	}

	// store payment value, category, duedate, and whether its paid in seperate data
	// files.
	@Override
	public boolean savePayment(Payment payment, int id) {
		try {
			// set the output File : true so it appends the file rather than remake a new
			// file
			outputVal = new FileOutputStream(valueFile, true);
			outputDate = new FileOutputStream(dateFile, true);
			outputCat = new FileOutputStream(categoryFile, true);
			outputPaid = new FileOutputStream(paidFile, true);

			// set the values to be stored in the File
			storeVal.setProperty(Integer.toString(id), Double.toString(payment.getValue()));
			storeDueDate.setProperty(Integer.toString(id), payment.getDueDate());
			storeCategory.setProperty(Integer.toString(id), payment.getCategory());
			storePaid.setProperty(Integer.toString(id), Boolean.toString(payment.getStatus()));

			// save the properties File without extra comments after
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

	// returns the value of the payment with id
	public double grabValue(int id) {
		double tempval;
		tempval = paymentByID(id).getValue();
		return tempval;
	}

	// return the category of the payment with id
	public String grabCategory(int id) {
		String tempcat;
		tempcat = paymentByID(id).getCategory();
		return tempcat;
	}

	// return the duedate of the payment with id
	public String grabDate(int id) {
		String tempdate;
		tempdate = paymentByID(id).getDueDate();
		return tempdate;
	}

	// return whether payment was paid or not
	public boolean grabStatus(int id) {
		boolean tempstatus;
		tempstatus = paymentByID(id).getStatus();
		return tempstatus;
	}

}
