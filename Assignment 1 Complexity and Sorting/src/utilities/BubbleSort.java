package utilities;

import java.util.Comparator;
import problemDomain.Product;

/**
 * Sorts product arrays in descending comparator order using repeated adjacent
 * comparisons.
 */

public class BubbleSort {
	/**
	 * Sorts an array of Product objects using the bubble sort algorithm based on
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
	public static void bubbleSort(Product[] products, Comparator<Product> comparator) {
		// Initialize a boolean variable to track whether any swaps were made during the
		// sorting process.
		boolean swapped = true;
		// Initialize a variable to track the end of the unsorted portion of the array.
		int end = products.length - 1;
		/**
		 * Continue sorting until no swaps are made or the end of the array is reached.
		 */
		while (swapped && end > 0) {
			swapped = false;
			/**
			 * Iterate through the array and compare adjacent elements, swapping them if
			 * they are in the wrong order based on the comparator.
			 */
			for (int i = 0; i < end; i++) {
				if (comparator.compare(products[i], products[i + 1]) < 0) {
					/**
					 * Swap the elements using a temporary variable.
					 */
					Product temp = products[i];
					products[i] = products[i + 1];
					products[i + 1] = temp;
					/**
					 * Set swapped to true to indicate that a swap has occurred, and the sorting
					 * process should continue.
					 */
					swapped = true;
				}
			}
			end--;
		}
	}
}