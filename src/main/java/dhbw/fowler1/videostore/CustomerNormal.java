package dhbw.fowler1.videostore;

import java.util.Enumeration;

public class CustomerNormal extends Customer {

	public CustomerNormal(String name) {
		super(name);
	}
	
	@Override
	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Enumeration rentals = getRentals().elements();
		String statement = "Rental Record for " + getName() + "\n";
		while (rentals.hasMoreElements()) {
			resetAmount();
			Rental eachRental = (Rental) rentals.nextElement();

			setAmount(eachRental.getMovie().getPrice(eachRental, getAmount()));

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
	
	private String addFooterLines(String result, double totalAmount, int frequentRenterPoints) {
		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
		return result;
	}

}
