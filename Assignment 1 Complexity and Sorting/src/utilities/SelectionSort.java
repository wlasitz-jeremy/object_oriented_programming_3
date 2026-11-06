package utilities;

import java.util.Comparator;
import problemDomain.Product;

/**
 * Sorts product arrays in descending comparator order by selecting the next
 * largest element.
 */

public class SelectionSort {
	/**
	 * Sorts an array of Product objects using the selection sort algorithm based on
	 * the provided comparator in descending order.
	 * 
	 * @param products   The array of Product objects to be sorted.
	 * @param comparator The comparator used to compare Product objects for sorting.
	 */
	/**
	 * Sorts the supplied Product array in descending order according to the
	 * comparator.
	 * 
	 * @param products   the array of Product objects to sort
	 * @param comparator the comparator that defines descending product order
	 */
	public static void selectionSort(Product[] products, Comparator<Product> comparator) {
		/**
		 * Iterate through the array to find the minimum element and place it at the
		 * beginning of the unsorted portion of the array.
		 */
		for (int start = 0; start < products.length - 1; start++) {
			/**
			 * Assume the first element of the unsorted portion is the minimum.
			 */
			int first = start;
			/**
			 * Iterate through the unsorted portion of the array to find the actual minimum
			 * element.
			 */
			for (int i = start + 1; i < products.length; i++) {
				/**
				 * Compare the current element with the assumed minimum using the provided
				 * comparator. If the current element is smaller, update the index of the
				 * minimum element.
				 */
				if (comparator.compare(products[i], products[first]) > 0) {
					first = i;
				}
			}
			/**
			 * Swap the found minimum element with the first element of the unsorted portion
			 * of the array.
			 */
			Product temp = products[start];
			products[start] = products[first];
			products[first] = temp;
		}
	}
}