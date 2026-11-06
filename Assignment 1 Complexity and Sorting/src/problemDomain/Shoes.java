package problemDomain;

/**
 * Represents shoes and calculates their weighted price difference using the
 * shoes rate.
 */

public final class Shoes extends Product {
	/**
	 * Constructor for the Shoes class that initializes the original and updated
	 * prices by calling the superclass constructor.
	 * 
	 * @param originalPrice the original price of the shoes
	 * @param updatedPrice  the updated price of the shoes
	 */
	public Shoes(double originalPrice, double updatedPrice) {
		// Call the superclass constructor to initialize the original and updated
		// prices.
		super(originalPrice, updatedPrice);
	}

	/**
	 * Calculates the real difference in price for Shoes, which is 40% of the
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
		// Calculate the weighted absolute difference for Shoes, which is 40% of the
		// absolute difference between the original and updated prices.
		return 0.4 * Math.abs(getOriginalPrice() - getUpdatedPrice());
	}

	/**
	 * Returns the product type as a string, which is "Shoes" for this class.
	 */
	/**
	 * Returns the product category name used in input records and result output.
	 * 
	 * @return the product category name
	 */

	@Override
	public String getProdType() {
		return "Shoes";
	}

}