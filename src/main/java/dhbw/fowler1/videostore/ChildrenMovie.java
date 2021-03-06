package dhbw.fowler1.videostore;

public class ChildrenMovie extends Movie {

	public ChildrenMovie(String title, int priceCode) {
		super(title, priceCode);
	}
	
	@Override
	public double getPrice(Rental rental, double amount) {
		amount += 1.5;
		if (rental.getDaysRented() > 3)
			amount += (rental.getDaysRented() - 3) * 1.5;
		return amount;
	}

}
