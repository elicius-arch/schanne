package dhbw.fowler1.videostore;

import java.util.Enumeration;

public class CustomerHtml extends Customer {

	public CustomerHtml(String name) {
		super(name);
	}
	
	@Override
	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Enumeration rentals = getRentals().elements();
		String statement = "<H1>Rental Record for " + getName() + "</H1>";
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

}
