package dhbw.fowler1.videostore;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {
	private String _name;
	private Vector _rentals = new Vector();
	private double thisAmount = 0;

	public Customer(String name) {
		_name = name;
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Enumeration rentals = _rentals.elements();
		String statement = "Rental Record for " + getName() + "\n";
		while (rentals.hasMoreElements()) {
			resetAmount();
			Rental eachRental = (Rental) rentals.nextElement();

			// determine amount for each line
			switch (eachRental.getMovie().getPriceCode()) {
			case Movie.REGULAR:
				increaseAmount(2);
				if (eachRental.getDaysRented() > 2)
					increaseAmount((eachRental.getDaysRented() - 2) * 1.5);
				break;
			case Movie.NEW_RELEASE:
				increaseAmount(eachRental.getDaysRented() * 3);
				break;
			case Movie.CHILDRENS:
				increaseAmount(1.5);
				if (eachRental.getDaysRented() > 3)
					increaseAmount((eachRental.getDaysRented() - 3) * 1.5);
				break;
			}

			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if ((eachRental.getMovie().getPriceCode() == Movie.NEW_RELEASE) && eachRental.getDaysRented() > 1)
				frequentRenterPoints++;

			// show figures for this rental
			statement += "\t" + eachRental.getMovie().getTitle() + "\t" + String.valueOf(getAmount()) + "\n";
			totalAmount += getAmount();
		}
		statement = addFooterLines(statement, totalAmount, frequentRenterPoints);
		return statement;
	}
	
	public String htmlStatement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Enumeration rentals = _rentals.elements();
		String statement = "<H1>Rental Record for " + getName() + "</H1>";
		while (rentals.hasMoreElements()) {
			resetAmount();
			Rental eachRental = (Rental) rentals.nextElement();

			// determine amount for each line
			switch (eachRental.getMovie().getPriceCode()) {
			case Movie.REGULAR:
				increaseAmount(2);
				if (eachRental.getDaysRented() > 2)
					increaseAmount((eachRental.getDaysRented() - 2) * 1.5);
				break;
			case Movie.NEW_RELEASE:
				increaseAmount(eachRental.getDaysRented() * 3);
				break;
			case Movie.CHILDRENS:
				increaseAmount(1.5);
				if (eachRental.getDaysRented() > 3)
					increaseAmount((eachRental.getDaysRented() - 3) * 1.5);
				break;
			}

			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if ((eachRental.getMovie().getPriceCode() == Movie.NEW_RELEASE) && eachRental.getDaysRented() > 1)
				frequentRenterPoints++;

			// show figures for this rental
			statement += "<P>\t" + eachRental.getMovie().getTitle() + "\t" + String.valueOf(getAmount()) + "</P>";
			totalAmount += getAmount();
		}
		statement = addFooterLinesForHtml(statement, totalAmount, frequentRenterPoints);
		return statement;
	}

	private String addFooterLinesForHtml(String result, double totalAmount, int frequentRenterPoints) {
		result += "<P>Amount owed is " + String.valueOf(totalAmount) + "</P>";
		result += "<P>You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points</P>";
		return result;
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

	public double getAmount() {
		return thisAmount;
	}

	public void increaseAmount(double increment) {
		thisAmount += increment;
	}
	
	public void resetAmount() {
		thisAmount = 0;
	}
}
