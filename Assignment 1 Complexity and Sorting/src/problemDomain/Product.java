package problemDomain;

/**
 * Represents a product with original and updated prices and defines
 * product-specific price difference behavior.
 */

public abstract class Product implements Comparable<Product> {

	/**
	 * Product price attributes
	 */
	private final double originalPrice;
	private final double updatedPrice;

	/**
	 * Parameterized Constructor
	 */
	public Product(double originalPrice, double updatedPrice) {
		this.originalPrice = originalPrice;
		this.updatedPrice = updatedPrice;
	}

	/**
	 * Getters and Setters
	 */
	/**
	 * Returns the product original price.
	 * 
	 * @return the original price
	 */
	public final double getOriginalPrice() {
		return originalPrice;
	}

	/**
	 * Returns the product updated price.
	 * 
	 * @return the updated price
	 */
	public final double getUpdatedPrice() {
		return updatedPrice;
	}

	/**
	 * Product Specific abstract methods to be implemented for all product types
	 */
	/**
	 * Calculates the product-specific weighted absolute difference between the
	 * original and updated prices.
	 * 
	 * @return the weighted absolute price difference
	 */
	public abstract double calcRealDiff();

	/**
	 * Returns the product category name used in input records and result output.
	 * 
	 * @return the product category name
	 */
	public abstract String getProdType();

	/**
	 * Natural ordering uses the calculated difference
	 */
	/**
	 * Compares products using their calculated real price differences.
	 * 
	 * @param other the product to compare with this product
	 * @return a negative value, zero, or a positive value according to the real
	 *         price difference
	 */

	@Override
	public final int compareTo(Product other) {
		// Compare products based on their calculated real price differences using
		// Double.compare for proper handling of floating-point values.
		return Double.compare(calcRealDiff(), other.calcRealDiff());
	}

	/**
	 * Override toString method to provide a string representation of the Product
	 * object
	 */
	/**
	 * Returns a string containing the product price and derived information.
	 * 
	 * @return a descriptive string representation of this product
	 */

	@Override
	public String toString() {
		// Return a string representation of the Product object, including original
		// price, updated price, and calculated differences.
		return "Product [originalPrice=" + originalPrice + ", updatedPrice=" + updatedPrice + ", getOriginalPrice()="
				+ getOriginalPrice() + ", getUpdatedPrice()=" + getUpdatedPrice() + ", calcRealDiff()=" + calcRealDiff()
				+ ", getProdType()=" + getProdType() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

}