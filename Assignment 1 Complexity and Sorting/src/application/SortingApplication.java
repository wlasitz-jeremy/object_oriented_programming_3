package application;

import java.io.IOException;
import java.util.Comparator;

import exceptions.InvalidComparisonTypeException;
import exceptions.InvalidFileDataException;
import exceptions.InvalidFileTypeException;
import exceptions.InvalidSortingAlgorithmException;
import external.OriginalPriceComparator;
import external.RealDifferenceComparator;
import external.UpdatedPriceComparator;
import manager.ProductReader;
import problemDomain.Product;
import utilities.BubbleSort;
import utilities.HeapSort;
import utilities.InsertionSort;
import utilities.MergeSort;
import utilities.QuickSort;
import utilities.SelectionSort;

/**
 * Loads products, selects the requested comparison and sorting strategy, and
 * reports sorting time.
 */

public class SortingApplication {

	/**
	 * Reads product data, selects the requested comparator and sorting algorithm,
	 * measures the sort, and returns the sorted array.
	 * 
	 * @param options the parsed command-line options
	 * @return the run result
	 * @throws InvalidFileTypeException         if the operation cannot be completed
	 * @throws InvalidSortingAlgorithmException if the operation cannot be completed
	 * @throws IOException                      if the operation cannot be completed
	 * @throws InvalidComparisonTypeException   if the operation cannot be completed
	 * @throws InvalidFileDataException         if the data in the file is invalid
	 *                                          or corrupt
	 */

	public static Product[] run(CommandLineArgs options) throws InvalidFileTypeException,
			InvalidSortingAlgorithmException, InvalidComparisonTypeException, InvalidFileDataException, IOException {

		// Initialize the algorithm name and timing variables for measuring the sort
		// time output.
		String Algorithm;
		long start, stop;

		/**
		 * Check if the file name ends with ".txt"
		 * 
		 * @throws InvalidFileTypeException if the file name does not end with ".txt"
		 */
		try {
			if (!options.getFileName().endsWith(".txt")) {
				throw new InvalidFileTypeException();
			}
		} catch (InvalidFileTypeException e) {
			System.out.println(e.getMessage());
			return null;
		}
		/**
		 * Read the products from the file.
		 */
		Product[] products = ProductReader.ReadProduct(options.getFileName());
		if (products == null) {
			return null;
		}
		/**
		 * Check if the compare type is valid and if it isn't throws a user defined
		 * exception.
		 */
		Comparator<Product> comparator = switch (options.getCompareType()) {
		case 'o' -> new OriginalPriceComparator();
		case 'u' -> new UpdatedPriceComparator();
		case 'd' -> new RealDifferenceComparator();
		default -> throw new InvalidComparisonTypeException("Invalid compare type: " + options.getCompareType()
				+ ". Valid options are: o for original price, u for updated price, and d for real difference.");
		};

		/**
		 * Check if the sort type is valid and if it isn't throws a user defined
		 * exception.
		 */
		try {
			if ("bismqz".indexOf(options.getSortType()) == -1) {
				throw new InvalidSortingAlgorithmException("Invalid sort type: " + options.getSortType()
						+ ". Valid options are: b for bubble-sort, i for insertioin-sort, s for selection-sort, m for merge-sort, q quick-sort and z for heap-sort.");
			}
		} catch (InvalidSortingAlgorithmException e) {
			System.out.println(e.getMessage());
			return null;
		}

		/**
		 * Sort the products based on the sort type and comparator. Measure the time
		 * taken to sort the products and print it to the console.
		 */
		switch (options.getSortType()) {
		case 'b':
			Algorithm = "Bubble";
			start = System.nanoTime();
			BubbleSort.bubbleSort(products, comparator);
			stop = System.nanoTime();
			System.out.println(Algorithm + " Sort run time was: " + (stop - start) + " nanoseconds.\n");
			break;
		case 'i':
			Algorithm = "Insertion";
			start = System.nanoTime();
			InsertionSort.insertionSort(products, comparator);
			stop = System.nanoTime();
			System.out.println(Algorithm + " Sort run time was: " + (stop - start) + " nanoseconds.\n");
			break;
		case 's':
			Algorithm = "Selection";
			start = System.nanoTime();
			SelectionSort.selectionSort(products, comparator);
			stop = System.nanoTime();
			System.out.println(Algorithm + " Sort run time was: " + (stop - start) + " nanoseconds.\n");
			break;
		case 'q':
			Algorithm = "Quick";
			start = System.nanoTime();
			QuickSort.quickSort(products, comparator);
			stop = System.nanoTime();
			System.out.println(Algorithm + " Sort run time was: " + (stop - start) + " nanoseconds.\n");
			break;
		case 'm':
			Algorithm = "Merge";
			start = System.nanoTime();
			MergeSort.mergeSort(products, comparator);
			stop = System.nanoTime();
			System.out.println(Algorithm + " Sort run time was: " + (stop - start) + " nanoseconds.\n");
			break;
		case 'z':
			Algorithm = "Heap";
			start = System.nanoTime();
			HeapSort.heapSort(products, comparator);
			stop = System.nanoTime();
			System.out.println(Algorithm + " Sort run time was: " + (stop - start) + " nanoseconds.\n");
			break;
		}
		return products;
	}
}