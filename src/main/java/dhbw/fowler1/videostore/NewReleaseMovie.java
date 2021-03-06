package dhbw.fowler1.videostore;

public class NewReleaseMovie extends Movie {

	public NewReleaseMovie(String title, int priceCode) {
		super(title, priceCode);
	}
	
	@Override
	public double getPrice(Rental rental, double amount) {
		amount += rental.getDaysRented() * 3;
		return amount;
	}

}
