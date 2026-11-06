package utilities;

import java.util.Comparator;
import problemDomain.Product;

/**
 * Sorts product arrays in descending comparator order using median-of-three
 * pivot selection and partitioning.
 */

public class QuickSort {

	/**
	 * Sorts an array of Product objects using the quick sort algorithm based on the
	 * provided comparator in descending order.
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
	public static void quickSort(Product[] products, Comparator<Product> comparator) {
		/**
		 * Call the recursive quick_sort method with the initial start and end indices.
		 */
		quick_sort(products, 0, products.length - 1, comparator);
	}

	/**
	 * Recursive quick_sort method that implements the quick sort algorithm
	 */
	/**
	 * Partitions and recursively sorts the specified portion of the array.
	 * 
	 * @param products   the array of Product objects to sort
	 * @param start      the first index in the array segment
	 * @param end        the last index in the array segment
	 * @param comparator the comparator that defines descending product order
	 */
	private static void quick_sort(Product[] products, int start, int end, Comparator<Product> comparator) {
		/**
		 * Base case: if the start index is greater than or equal to the end index,
		 * return as the array is already sorted.
		 */
		if (start >= end) {
			return;
		}
		/**
		 * Choose the pivot using the median-of-three method to improve performance on
		 * sorted or nearly sorted arrays.
		 */
		Product pivot = getMedian(products[start], products[start + (end - start) / 2], products[end], comparator);
		int left = start;
		int right = end;
		/**
		 * Partition the array around the pivot.
		 */
		while (left <= right) {
			/**
			 * Move the left pointer to the right until an element greater than or equal to
			 * the pivot is found.
			 */
			while (comparator.compare(products[left], pivot) > 0) {
				left++;
			}
			/**
			 * Move the right pointer to the left until an element less than or equal to the
			 * pivot is found.
			 */
			while (comparator.compare(products[right], pivot) < 0) {
				right--;
			}
			/**
			 * If the left pointer is less than or equal to the right pointer, swap the
			 * elements at the left and right pointers and move both pointers inward.
			 */
			if (left <= right) {
				swap(products, left, right);
				left++;
				right--;
			}
		}
		/**
		 * Recursively sort the left partition of the array.
		 */
		if (start < right) {
			quick_sort(products, start, right, comparator);
		}
		/**
		 * Recursively sort the right partition of the array.
		 */
		if (left < end) {
			quick_sort(products, left, end, comparator);
		}
	}

	/**
	 * Method to find the median of three elements (first, middle, last) using the
	 * provided comparator.
	 */
	/**
	 * Returns the median of the first, middle, and last products under the supplied
	 * comparator.
	 * 
	 * @param first      the first index or product involved
	 * @param middle     the middle product used for pivot selection
	 * @param last       the last product used for pivot selection
	 * @param comparator the comparator that defines descending product order
	 * @return the median product under the comparator
	 */
	private static Product getMedian(Product first, Product middle, Product last, Comparator<Product> comparator) {
		/**
		 * Compare the first and middle elements.
		 */
		if (comparator.compare(first, middle) < 0) {
			/**
			 * If the first element is less than the middle element, compare the middle and
			 * last elements.
			 */
			if (comparator.compare(middle, last) < 0) {
				return middle;
			}
			return comparator.compare(first, last) < 0 ? last : first;
		}
		/**
		 * If the first element is greater than or equal to the middle element, compare
		 * the first and last elements.
		 */
		if (comparator.compare(first, last) < 0) {
			return first;
		}
		/**
		 * If the first element is greater than or equal to the last element, compare
		 * the middle and last elements.
		 */
		return comparator.compare(middle, last) < 0 ? last : middle;
	}

	/**
	 * Swap function to exchange two elements in the array using a temporary
	 * variable.
	 */
	/**
	 * Exchanges the products stored at the two specified array positions.
	 * 
	 * @param products the array of Product objects to sort
	 * @param first    the first index or product involved
	 * @param second   the second index or product involved
	 */
	private static void swap(Product[] products, int first, int second) {
		/**
		 * Use a temporary variable to hold one of the elements while swapping.
		 */
		Product temp = products[first];
		products[first] = products[second];
		products[second] = temp;
	}
}