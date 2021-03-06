package dhbw.fowler1.videostore;

import java.util.Vector;

public class Customer {
	private String _name;
	private Vector _rentals = new Vector();
	private double thisAmount = 0;

	public Customer(String name) {
		_name = name;
	}

	public String statement() {
		return "";
	}

	public void addRental(Rental arg) {
		_rentals.addElement(arg);
	}
	
	public Vector getRentals() {
		return _rentals;
	}

	public String getName() {
		return _name;
	}

	public double getAmount() {
		return thisAmount;
	}
	
	public void setAmount(double amount) {
		this.thisAmount = amount;
	}

	public void increaseAmount(double increment) {
		thisAmount += increment;
	}
	
	public void resetAmount() {
		thisAmount = 0;
	}
}
