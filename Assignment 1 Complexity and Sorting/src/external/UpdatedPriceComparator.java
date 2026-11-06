package external;

import java.util.Comparator;

import problemDomain.Product;

/**
 * Comparator for updated product price
 */
/**
 * Orders products by updated price in ascending order.
 */
public class UpdatedPriceComparator implements Comparator<Product> {
	/**
	 * Compares two Product objects based on their updated price in ascending order.
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
		// Compare the updated prices of the two products
		return Double.compare(p1.getUpdatedPrice(), p2.getUpdatedPrice());
	}

}