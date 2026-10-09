package driver;

import java.util.Date;
import java.util.Random;
import java.util.Scanner;

/**
 * Demonstrates the use of linear search and binary search algorithms
 * on a sorted array of integers.
 * <p>
 * The program generates a sorted array of random integers, prompts
 * the user for a target value, and searches for the value using both
 * linear search and binary search while measuring execution time.
 * </p>
 */
public class Driver
{

	/** The number of elements stored in the array. */
	public static final int SIZE = 100;

	/** The upper bound for randomly generated increments. */
	public static final int UPPER_BOUND = 10;
	
	public static int linearSearch(Integer[] nums, int key) {
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == key) {
				count += 1;
				return i;
			}
		}
		if (count == 0) {
			return -1;
		}
		return key;
	}
	
	public static int binarySearch(Integer[] nums, int key) {
		int left = 0;
		int right = nums.length - 1;
		int middle = left + (right - left) / 2;
		while (left <= right) {
			middle = left + (right - left) / 2;
			if (nums[middle] == key) {
				return middle;
			}
			else if (nums[middle] > key) {
				right = middle - 1;
			}
			else {
				left = middle + 1;
			}	
		}
		return -1;
	}
	
	/**
	 * The main entry point of the application.
	 *
	 * @param args command-line arguments (not used)
	 */
	static Scanner sc= new Scanner(System.in);
	
	public static void main( String[] args )
	{
		
		/** Array containing sorted integer values. */
		Integer[] nums = new Integer[SIZE];

		/** Random number generator used to populate the array. */
		Random rand = new Random();

		int randnum = rand.nextInt( UPPER_BOUND );
		nums[0] = randnum;

		// Populate the array with increasing values.
		for( int i = 1; i < SIZE; i++ )
		{
			randnum = rand.nextInt( UPPER_BOUND );
			nums[i] = nums[i - 1] + randnum;
			System.out.println( nums[i] );
		}
		
		Date myDate = new Date();
		System.out.println("Jeremy Wlasitz " + myDate);
		System.out.println("\nPlease enter an integer value to search for: ");
		int userInput = sc.nextInt();
		long start, stop;
		start = System.nanoTime();
		int ls = linearSearch(nums, userInput);
		stop = System.nanoTime();
		if (ls == -1) {
			System.out.println("Target value was not found using linear search.");
		}
		else {
			System.out.println("Target value was found at index: " + ls);
		}
		System.out.println("Linear search time: " + (stop - start) + "ns.");
		start = System.nanoTime();
		int bs = binarySearch(nums, userInput);
		stop = System.nanoTime();
		if (bs == -1) {
			System.out.println("Target value was not found using binary search.");
		} else {
			System.out.println("Target value was found at index: " + bs);
		}
		System.out.println("Binary search time: " + (stop - start) + "ns.");
	}
}