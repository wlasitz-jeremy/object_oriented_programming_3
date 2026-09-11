package complexityStrings;

/**
 * Demonstrates the performance differences between
 * {@link String}, {@link StringBuffer}, and {@link StringBuilder}
 * when repeatedly concatenating characters.
 * <p>
 * This class measures and compares the execution time required
 * to append characters using each approach.
 * </p>
 */

public class Concat
{
	/** Default number of concatenation operations performed. */
	public static final int TEST_SIZE = 10000;

	/** String object used for concatenation testing. */
	private String str;

	/** StringBuffer object used for concatenation testing. */
	private StringBuffer sBuf;

	/** StringBuilder object used for concatenation testing. */
	private StringBuilder sBuild;

	/** Number of concatenation operations to perform. */
	private int size;

	/**
	 * Constructs a Concat object with default settings.
	 */
	public Concat()
	{
	}

	/**
	 * Constructs a Concat object with a specified test size.
	 *
	 * @param size the number of concatenation operations to perform
	 */
	public Concat( int size )
	{
		this.size = size;
	}

	/**
	 * Measures the time required to concatenate characters
	 * using a {@link String} object.
	 * <p>
	 * Since Strings are immutable, each concatenation creates
	 * a new object, making this approach less efficient.
	 * </p>
	 *
	 * @return the elapsed execution time in milliseconds
	 */
	public long stringTime()
	{
		char letter;
		int i;
		long start, stop;

		start = System.currentTimeMillis();

		for( letter = 'a', i = 0; i < size; i++, letter++ )
		{
			str = str + letter;

			if( i % 25 == 0 )
			{
				letter = 'a';
			}
		}

		stop = System.currentTimeMillis();

		return stop - start;
	}

	/**
	 * Measures the time required to concatenate characters
	 * using a {@link StringBuffer} object.
	 * <p>
	 * StringBuffer is mutable and thread-safe, making it
	 * more efficient than String for repeated concatenation.
	 * </p>
	 *
	 * @return the elapsed execution time in milliseconds
	 */
	public long stringBufferTime()
	{
		char letter;
		int i;
		long start, stop;

		sBuf = new StringBuffer( size );

		start = System.currentTimeMillis();

		for( letter = 'a', i = 0; i < size; i++, letter++ )
		{
			sBuf.append( letter );

			if( i % 25 == 0 )
			{
				letter = 'a';
			}
		}

		stop = System.currentTimeMillis();

		return stop - start;
	}

	/**
	 * Measures the time required to concatenate characters
	 * using a {@link StringBuilder} object.
	 * <p>
	 * StringBuilder is mutable and generally faster than
	 * StringBuffer because it is not synchronized.
	 * </p>
	 *
	 * @return the elapsed execution time in milliseconds
	 */
	public long stringBuilderTime()
	{
		char letter;
		int i;
		long start, stop;

		sBuild = new StringBuilder( size );

		start = System.currentTimeMillis();

		for( letter = 'a', i = 0; i < size; i++, letter++ )
		{
			sBuild.append( letter );

			if( i % 25 == 0 )
			{
				letter = 'a';
			}
		}

		stop = System.currentTimeMillis();

		return stop - start;
	}

	/**
	 * Main method used to execute the concatenation performance test.
	 *
	 * @param args command-line arguments (not used)
	 */
	public static void main( String[] args )
	{
		Concat cs = new Concat( TEST_SIZE );

		System.out.println( "This is time for string: " + cs.stringTime() );
		System.out.println( "This is time for StringBuffer: " + cs.stringBufferTime() );
		System.out.println( "This is time for StringBuilder: " + cs.stringBuilderTime() );
	}
}