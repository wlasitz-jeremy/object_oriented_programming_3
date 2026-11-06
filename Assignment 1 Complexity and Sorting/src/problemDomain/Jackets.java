package problemDomain;

/**
 * Represents a jacket and calculates its weighted price difference using the
 * jacket rate.
 */

public final class Jackets extends Product {
	/**
	 * Constructor for the Jackets class that initializes the original and updated
	 * prices by calling the superclass constructor.
	 * 
	 * @param originalPrice the original price of the jacket
	 * @param updatedPrice  the updated price of the jacket
	 */
	public Jackets(double originalPrice, double updatedPrice) {
		// Call the constructor of the superclass (Product) to initialize the original
		// and updated prices.
		super(originalPrice, updatedPrice);
	}

	/**
	 * Calculates the real difference in price for jackets, which is 70% of the
	 * absolute difference between the original and updated prices.
	 */
	/**
	 * Calculates the product-specific weighted absolute difference between the
	 * original and updated prices.
	 * 
	 * @return the weighted absolute price difference
	 */

	@Override
	public double calcRealDiff() {
		// Calculate the weighted absolute difference for jackets, which is 70% of the
		// absolute difference between the original and updated prices.
		return 0.7 * Math.abs(getOriginalPrice() - getUpdatedPrice());
	}

	/**
	 * Returns the product type as a string, which is "Jackets" for this class.
	 */
	/**
	 * Returns the product category name used in input records and result output.
	 * 
	 * @return the product category name
	 */

	@Override
	public String getProdType() {
		return "Jackets";
	}

}