package driver;

import java.util.Date;
import java.util.Random;

import sortUtils.MergeSort;

/**
 * Demonstrates the use of a sorting algorithm on an array of integers.
 * <p>
 * The p
 * The program generates an array of random integers, sorts it using a
 * user-selected algorithm provided via command-line, and prints the sorted
 * output.
 * </p>
 */
public class Driver
{

	/** Number of elements in the array. */
	public static final int SIZE = 100;

	/** Upper bound for random number generation. */
	public static final int UPPER_BOUND = 1000;

	/**
	 * Main entry point of the program.
	 *
	 * @param args command-line arguments selecting sorting algorithm:
	 *             <ul>
	 *               <li>b = bubble sort</li>
	 *               <li>i = insertion sort</li>
	 *               <li>s = selection sort</li>
	 *               <li>m = merge sort</li>
	 *               <li>q = quick sort</li>
	 *             </ul>
	 */
	public static void main( String[] args )
	{

		/** Array of random integers to be sorted. */
		Integer[] nums = new Integer[SIZE];

		/** Random number generator. */
		Random rand = new Random();

		Date myDate = new Date();
		
		// Populate array with random values.
		for( int i = 0; i < SIZE; i++ )
		{
			nums[i] = rand.nextInt( UPPER_BOUND );
			System.out.print( nums[i] + " " );
		}
		
		
		String algorithm = "";
		
		switch (args[0].charAt(0)) {
		case 'b':
			algorithm = "bubble";
			break;
		case 'i':
			algorithm = "insertion";
			break;
		case 's':
			algorithm = "selection";
			break;	
		case 'q':
			algorithm = "quick";
			break;
		case 'm':
			algorithm = "merge";
			MergeSort.mergeSort(nums, nums.length - 1, 0);
			break;
			
		}
		System.out.println("\n\nJeremy Wlasitz " + myDate);
		System.out.println("Sorted using merge: ");
		MergeSort.mergeSort(nums, 0, nums.length - 1);
		for( int i = 0; i < SIZE; i++ )
		{

			System.out.print( nums[i] + " " );
		}
		
	}

}