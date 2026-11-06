package utilities;

import java.util.Comparator;
import problemDomain.Product;

/**
 * Sorts product arrays in descending comparator order by recursively dividing
 * and merging the data.
 */

public class MergeSort {
	/**
	 * Sorts an array of Product objects using the merge sort algorithm based on the
	 * provided comparator in descending order.
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
	public static void mergeSort(Product[] products, Comparator<Product> comparator) {
		/**
		 * Base case: if the array has 0 or 1 elements, it's already sorted.
		 */
		if (products.length <= 1) {
			return;
		}
		/**
		 * Split the array into two halves left and right.
		 */
		int middle = products.length / 2;
		Product[] left = new Product[middle];
		Product[] right = new Product[products.length - middle];
		/**
		 * Copy the elements into the left and right arrays respectively.
		 */
		for (int i = 0; i < products.length; i++) {
			if (i < middle) {
				left[i] = products[i];
			} else {
				right[i - middle] = products[i];
			}
		}
		/**
		 * Recursively sort both halves of the array.
		 */
		mergeSort(left, comparator);
		mergeSort(right, comparator);
		/**
		 * Merge the sorted halves back into the original array.
		 */
		merge(left, right, products, comparator);
	}

	/**
	 * Merges two sorted arrays (left and right) into a single sorted array
	 * (products) based on the provided comparator.
	 */
	/**
	 * Merges two comparator-ordered arrays into the destination array.
	 * 
	 * @param left       the left sorted partition
	 * @param right      the right sorted partition
	 * @param products   the array of Product objects to sort
	 * @param comparator the comparator that defines descending product order
	 */
	private static void merge(Product[] left, Product[] right, Product[] products, Comparator<Product> comparator) {
		/**
		 * Initialize indices for left, right, and output arrays.
		 */
		int leftIndex = 0;
		int rightIndex = 0;
		int outputIndex = 0;
		/**
		 * Compare elements from left and right arrays and merge them into the products
		 * array in sorted order.
		 */
		while (leftIndex < left.length && rightIndex < right.length) {
			/**
			 * Use the comparator to determine the order of elements. If the left element is
			 * less than or equal to the right element, add it to the products array;
			 * otherwise, add the right element.
			 */
			if (comparator.compare(left[leftIndex], right[rightIndex]) >= 0) {
				products[outputIndex++] = left[leftIndex++];
			} else {
				products[outputIndex++] = right[rightIndex++];
			}
		}
		/**
		 * If there are remaining elements in the left array, add them to the products
		 * array.
		 */
		while (leftIndex < left.length) {
			products[outputIndex++] = left[leftIndex++];
		}
		/**
		 * If there are remaining elements in the right array, add them to the products
		 * array.
		 */
		while (rightIndex < right.length) {
			products[outputIndex++] = right[rightIndex++];
		}
	}
}