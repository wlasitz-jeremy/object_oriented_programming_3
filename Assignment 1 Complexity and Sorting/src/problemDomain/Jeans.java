package problemDomain;

/**
 * Represents a pair of jeans and calculates its weighted price difference using
 * the jeans rate.
 */

public final class Jeans extends Product {
	/**
	 * Constructor for the Jeans class that initializes the original and updated
	 * prices of the product.
	 * 
	 * @param originalPrice the original price of the jeans
	 * @param updatedPrice  the updated price of the jeans
	 */
	public Jeans(double originalPrice, double updatedPrice) {
		// Call the constructor of the superclass (Product) to initialize the original
		// and updated prices.
		super(originalPrice, updatedPrice);
	}

	/**
	 * Calculates the real difference in price for Jeans, which is half the absolute
	 * difference between the original and updated prices.
	 */
	/**
	 * Calculates the product-specific weighted absolute difference between the
	 * original and updated prices.
	 * 
	 * @return the weighted absolute price difference
	 */

	@Override
	public double calcRealDiff() {
		// Calculate the weighted absolute difference for jeans, which is half the
		// absolute difference between the original and updated prices.
		return 0.5 * Math.abs(getOriginalPrice() - getUpdatedPrice());
	}

	/**
	 * Returns the product type as a string, which is "Jeans" for this class.
	 */
	/**
	 * Returns the product category name used in input records and result output.
	 * 
	 * @return the product category name
	 */

	@Override
	public String getProdType() {
		return "Jeans";
	}

}