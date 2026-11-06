package problemDomain;

/**
 * Represents shorts and calculates their weighted price difference using the
 * shorts rate.
 */

public final class Shorts extends Product {
	/**
	 * Constructor for the Shorts class that initializes the original and updated
	 * prices.
	 * 
	 * @param originalPrice the original price of the shorts
	 * @param updatedPrice  the updated price of the shorts
	 */
	public Shorts(double originalPrice, double updatedPrice) {
		// Calls the constructor of the superclass Product to initialize the original
		// and updated prices.
		super(originalPrice, updatedPrice);
	}

	/**
	 * Calculates the real difference in price for Shorts, which is 30% of the
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
		// Calculates the weighted absolute difference for Shorts, which is 30% of the
		// absolute difference between the original and updated prices.
		return 0.3 * Math.abs(getOriginalPrice() - getUpdatedPrice());
	}

	/**
	 * Returns the product type as "Shorts".
	 */
	/**
	 * Returns the product category name used in input records and result output.
	 * 
	 * @return the product category name
	 */

	@Override
	public String getProdType() {
		return "Shorts";
	}

}