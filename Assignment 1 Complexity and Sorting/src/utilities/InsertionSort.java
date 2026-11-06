package utilities;

import java.util.Comparator;
import problemDomain.Product;

/**
 * Sorts product arrays in descending comparator order by inserting each item
 * into the sorted prefix.
 */

public class InsertionSort {
	/**
	 * Sorts an array of Product objects using the insertion sort algorithm based on
	 * the provided comparator in descending order.
	 * 
	 * @param products   The array of Product objects to be sorted.
	 * @param comparator The comparator used to determine the order of the Product
	 *                   objects.
	 */
	/**
	 * Sorts the supplied Product array in descending order according to the
	 * comparator.
	 * 
	 * @param products   the array of Product objects to sort
	 * @param comparator the comparator that defines descending product order
	 */
	public static void insertionSort(Product[] products, Comparator<Product> comparator) {
		/**
		 * Iterate through the array starting from the second element.
		 */
		for (int i = 1; i < products.length; i++) {
			/**
			 * Store the current product to be compared.
			 */
			Product c = products[i];
			/**
			 * Move elements of products[0..i-1] that are greater than c to one position
			 * ahead of their current position.
			 */
			int j = i - 1;
			/**
			 * Use the comparator to determine the order of products.
			 */
			while (j >= 0 && comparator.compare(products[j], c) < 0) {
				/**
				 * Shift the product to the right.
				 */
				products[j + 1] = products[j];
				j--;
			}
			/**
			 * Place the current product in its correct position.
			 */
			products[j + 1] = c;
		}
	}
}