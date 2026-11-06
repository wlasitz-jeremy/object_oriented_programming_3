package external;

import java.util.Comparator;
import problemDomain.Product;

/**
 * Comparator for sorting products based on the real difference between original and updated prices.
 */
/**
 * Orders products by their product-specific real price difference in ascending
 * order.
 */
public class RealDifferenceComparator implements Comparator<Product> {
	/**
	 * Compare two products based on the real difference between their original and
	 * updated prices.
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
		// Compare the real price differences of the two products
		return Double.compare(p1.calcRealDiff(), p2.calcRealDiff());
	}
}