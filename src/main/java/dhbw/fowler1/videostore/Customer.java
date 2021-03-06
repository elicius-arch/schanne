package dhbw.fowler1.videostore;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {
	private String _name;
	private Vector _rentals = new Vector();

	public Customer(String name) {
		_name = name;
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Enumeration rentals = _rentals.elements();
		String statement = "Rental Record for " + getName() + "\n";
		while (rentals.hasMoreElements()) {
			double thisAmount = 0;
			Rental eachRental = (Rental) rentals.nextElement();

			// determine amount for each line
			switch (eachRental.getMovie().getPriceCode()) {
			case Movie.REGULAR:
				thisAmount += 2;
				if (eachRental.getDaysRented() > 2)
					thisAmount += (eachRental.getDaysRented() - 2) * 1.5;
				break;
			case Movie.NEW_RELEASE:
				thisAmount += eachRental.getDaysRented() * 3;
				break;
			case Movie.CHILDRENS:
				thisAmount += 1.5;
				if (eachRental.getDaysRented() > 3)
					thisAmount += (eachRental.getDaysRented() - 3) * 1.5;
				break;
			}

			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if ((eachRental.getMovie().getPriceCode() == Movie.NEW_RELEASE) && eachRental.getDaysRented() > 1)
				frequentRenterPoints++;

			// show figures for this rental
			statement += "\t" + eachRental.getMovie().getTitle() + "\t" + String.valueOf(thisAmount) + "\n";
			totalAmount += thisAmount;
		}
		addFooterLines(statement, totalAmount, frequentRenterPoints);
		return statement;
	}

	private String addFooterLines(String result, double totalAmount, int frequentRenterPoints) {
		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
		return result;
	}

	public void addRental(Rental arg) {
		_rentals.addElement(arg);
	}

	public String getName() {
		return _name;
	}
}
