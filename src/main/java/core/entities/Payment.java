package core.entities;

/**
 * Created by ryan on 10/13/17.
 * The class representation of a 'bill', or 'payment' withing the system
 */
public class Payment {
    // TODO Cody you can start designing this class of other entities
	
	//EXAMPLE PAYMENT METHODS AND PROPERTIES
	
	private int id = 0;
	private double Value = 0;
	private boolean Status = false;
	private String category = null;
	private String Due = null;
	
	public Payment(int id, double Value, boolean Status, String category, String Due) {
		this.id = id;
		this.Value = Value;
		this.Status = Status;
		this.category = category;
		this.Due = Due;
	}
	
	//returns the id of the payment
	public int getID() {
		return this.id;
	}
	
	//returns the value of payment
	public double getValue() {
		return this.Value;
	}
	
	//return whether the payment was made or not
	public boolean getStatus() {
		return this.Status;
	}
	
	//returns the category of the payment example: Food, Gas
	public String getCategory() {
		return this.category;
	}
	
	//returns the due date for the payment stored as an int value mm/dd/yyyy ex: 03201995 
	public String getDueDate() {
		return this.Due;
	}
}
