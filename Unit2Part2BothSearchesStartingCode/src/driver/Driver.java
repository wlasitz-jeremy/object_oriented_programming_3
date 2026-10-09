package driver;

import java.util.Random;

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

	/**
	 * The main entry point of the application.
	 *
	 * @param args command-line arguments (not used)
	 */
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

	}
	
}