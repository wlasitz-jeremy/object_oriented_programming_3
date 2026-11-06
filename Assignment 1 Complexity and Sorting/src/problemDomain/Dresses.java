package problemDomain;

/**
 * Represents a dress and calculates its weighted price difference using the
 * dress rate.
 */

public final class Dresses extends Product {
	/**
	 * Constructor for the Dresses class that initializes the original and updated
	 * prices of the product.
	 * 
	 * @param originalPrice the original price of the product
	 * @param updatedPrice  the updated price of the product
	 */
	public Dresses(double originalPrice, double updatedPrice) {
		// Call the constructor of the superclass (Product) to initialize the original
		// and updated prices
		super(originalPrice, updatedPrice);
	}

	/**
	 * Calculates the real difference in price for Dresses, which is 60% of the
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
		// Calculate the weighted absolute difference for Dresses, which is 60% of the
		// absolute difference between the original and updated prices
		return 0.6 * Math.abs(getOriginalPrice() - getUpdatedPrice());
	}

	/**
	 * Returns the product type as a string, which is "Dresses" for this class.
	 */
	/**
	 * Returns the product category name used in input records and result output.
	 * 
	 * @return the product category name
	 */

	@Override
	public String getProdType() {
		return "Dresses";
	}

}