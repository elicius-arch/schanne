package dhbw.fowler1.videostore;

public class Movie {
	public static final int CHILDRENS = 2;
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;
	public static final int TEST = 5;

	private String _title;
	private int _priceCode;

	public Movie(String title, int priceCode) {
		_title = title;
		_priceCode = priceCode;
	}

	public int getPriceCode() {
		return _priceCode;
	}

	public void setPriceCode(int _priceCode) {
		this._priceCode = _priceCode;
	}

	public String getTitle() {
		return _title;
	}

	public double getPrice(Rental rental, double amount) {
		return 0;
	}

	public static Movie of(int priceCode, String title) {
		switch (priceCode) {
		case CHILDRENS:
			return new ChildrenMovie(title, CHILDRENS);
		case REGULAR:
			return new RegularMovie(title, REGULAR);
		case NEW_RELEASE:
			return new NewReleaseMovie(title, NEW_RELEASE);
		default:
			return null;
		}
	}
}
