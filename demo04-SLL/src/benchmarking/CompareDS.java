package benchmarking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * A benchmarking utility class used to compare the performance of
 * different data structures: arrays, ArrayList, and LinkedList.
 * 
 * The class measures the time required to iterate through each structure
 * and sum all elements.
 */
public class CompareDS
{
	/** Default number of elements used for testing. */
	public static final int TEST_SIZE = 1000;

	/** Maximum random value generated for dataset. */
	public static final int UPPER_BOUND = 1000;

	// Attributes
	private Integer[] arr;
	private ArrayList<Integer> aList;
	private LinkedList<Integer> lList;

	/**
	 * Constructs a CompareDS object and initializes all data structures
	 * with randomly generated integers.
	 * 
	 * @param TEST_SIZE number of elements to generate and store in each structure
	 */
	public CompareDS(int TEST_SIZE)
	{
		arr = new Integer[TEST_SIZE];
		aList = new ArrayList<>();
		lList = new LinkedList<>();
		generateNums();
	}

	/**
	 * Generates random integers and populates the array, ArrayList, and LinkedList.
	 */
	private void generateNums()
	{		
		Random rand = new Random();

		for (int i = 0; i < TEST_SIZE; i++)
		{
			int curr = rand.nextInt(UPPER_BOUND);
			arr[i] = curr;
			aList.add(curr);
			lList.add(curr);
		}
	}

	/**
	 * Computes the time required to sum all elements in a plain array.
	 * 
	 * @return execution time in nanoseconds
	 */
	public long arrayTime()
	{
		long start, stop;
		@SuppressWarnings( "unused" )
		int sum = 0;

		start = System.nanoTime();

		for (int i = 0; i < TEST_SIZE; i++)
		{
			sum += arr[i];
		}

		stop = System.nanoTime();
		return stop - start;
	}

	/**
	 * Computes the time required to sum all elements in an ArrayList.
	 * 
	 * @return execution time in nanoseconds
	 */
	public long arrayListTime()
	{
		long start, stop;
		@SuppressWarnings( "unused" )
		int sum = 0;

		start = System.nanoTime();

		for (int i = 0; i < TEST_SIZE; i++)
		{
			sum += aList.get(i);
		}

		stop = System.nanoTime();
		return stop - start;
	}

	/**
	 * Computes the time required to sum all elements in a LinkedList.
	 * 
	 * @return execution time in nanoseconds
	 */
	public long linkedListTime()
	{
		long start, stop;
		@SuppressWarnings( "unused" )
		int sum = 0;

		start = System.nanoTime();

		for (int i = 0; i < TEST_SIZE; i++)
		{
			sum += lList.get(i);
		}

		stop = System.nanoTime();
		return stop - start;
	}

	/**
	 * Main method used to run benchmarking comparisons between data structures.
	 * 
	 * @param args command-line arguments (not used)
	 */
	public static void main(String[] args)
	{
		CompareDS cs = new CompareDS(TEST_SIZE);

		System.out.println("This is time for Array: " + cs.arrayTime());
		System.out.println("This is time for ArrayList: " + cs.arrayListTime());
		System.out.println("This is time for LinkedList: " + cs.linkedListTime());
	}
}