package problemDomain;

/**
 * Represents a T-shirt and calculates its weighted price difference using the
 * T-shirt rate.
 */

public final class TShirts extends Product {
	/**
	 * Constructor for TShirts class that initializes the original and updated
	 * prices using the superclass constructor.
	 * 
	 * @param originalPrice the original price of the T-shirt
	 * @param updatedPrice  the updated price of the T-shirt
	 */
	public TShirts(double originalPrice, double updatedPrice) {
		// Call the superclass constructor to initialize the original and updated
		// prices.
		super(originalPrice, updatedPrice);
	}

	/**
	 * Calculates the real difference in price for TShirts, which is 20% of the
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
		// Calculate the weighted absolute difference for TShirts, which is 20% of the
		// absolute difference between the original and updated prices.
		return 0.2 * Math.abs(getOriginalPrice() - getUpdatedPrice());
	}

	/**
	 * Returns the product type as a string, which is "TShirts" for this class.
	 */
	/**
	 * Returns the product category name used in input records and result output.
	 * 
	 * @return the product category name
	 */

	@Override
	public String getProdType() {
		return "TShirts";
	}

}