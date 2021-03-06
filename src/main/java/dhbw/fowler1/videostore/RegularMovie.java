package dhbw.fowler1.videostore;

public class RegularMovie extends Movie {
	
	

	public RegularMovie(String title, int priceCode) {
		super(title, priceCode);
	}
	
	@Override
	public double getPrice(Rental rental, double amount) {
		amount += 2;
		if (rental.getDaysRented() > 2)
			amount += (rental.getDaysRented() - 2) * 1.5;
		return amount;
	}
	
}
