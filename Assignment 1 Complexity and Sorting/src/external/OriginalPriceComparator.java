package external;

import java.util.Comparator;

import problemDomain.Product;

/**
 * Comparator for original product price
 */
/**
 * Orders products by original price in ascending order.
 */
public class OriginalPriceComparator implements Comparator<Product> {
	/**
	 * Compares two Product objects based on their original price in ascending
	 * order.
	 */
	/**
	 * Compares two products using the price criterion represented by this
	 * comparator.
	 * 
	 * @param p1 the first product to compare
	 * @param p2 the second product to compare
	 * @return a negative value, zero, or a positive value according to this
	 *         comparison criterion
	 */

	@Override
	public int compare(Product p1, Product p2) {
		// Compare the original prices of the two products
		return Double.compare(p1.getOriginalPrice(), p2.getOriginalPrice());
	}

}