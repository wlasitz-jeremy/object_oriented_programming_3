package utilities;

import java.util.Comparator;
import problemDomain.Product;

/**
 * Sorts product arrays in descending comparator order using a comparator-based
 * heap.
 */

public class HeapSort {
	/**
	 * Sorts an array of Product objects using the heap sort algorithm based on the
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
	public static void heapSort(Product[] products, Comparator<Product> comparator) {
		// Get the size of the products array.
		int size = products.length;
		/**
		 * Build the heap by calling heapify on each non-leaf node starting from the
		 * last non-leaf node down to the root.
		 */
		for (int i = size / 2 - 1; i >= 0; i--) {
			heapify(products, size, i, comparator);
		}
		/**
		 * Extract elements from the heap one by one.
		 */
		for (int i = size - 1; i > 0; i--) {
			swap(products, 0, i);
			heapify(products, i, 0, comparator);
		}
	}

	/**
	 * Heapify function to maintain the heap property for the given root node.
	 */
	/**
	 * Restores the heap property for the subtree rooted at the specified index.
	 * 
	 * @param products   the array of Product objects to sort
	 * @param size       the number of heap elements
	 * @param root       the index of the heap subtree root
	 * @param comparator the comparator that defines descending product order
	 */
	private static void heapify(Product[] products, int size, int root, Comparator<Product> comparator) {
		/**
		 * Find the smallest among root, left child, and right child as we are
		 * implementing a min-heap to sort in descending order.
		 */
		int smallest = root;
		int left = 2 * root + 1;
		int right = 2 * root + 2;
		/**
		 * Compare the left child with the smallest.
		 */
		if (left < size && comparator.compare(products[left], products[smallest]) < 0) {
			smallest = left;
		}
		/**
		 * Compare the right child with the smallest.
		 */
		if (right < size && comparator.compare(products[right], products[smallest]) < 0) {
			smallest = right;
		}
		/**
		 * If the smallest is not the root, swap and continue heapifying.
		 */
		if (smallest != root) {
			swap(products, root, smallest);
			heapify(products, size, smallest, comparator);
		}
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
		 * Swap the elements at the specified indices using a temporary variable.
		 */
		Product temp = products[first];
		products[first] = products[second];
		products[second] = temp;
	}
}