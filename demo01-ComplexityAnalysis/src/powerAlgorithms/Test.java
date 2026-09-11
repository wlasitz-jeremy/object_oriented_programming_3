package powerAlgorithms;

/**
 * Driver class used to compare the performance of two power algorithms:
 * {@link SimplePowerAlgorithm} and {@link SmartPowerAlgorithm}.
 * <p>
 * This program parses command-line arguments, executes both algorithms,
 * measures their execution times, and outputs debugging information
 * based on the selected debug level.
 * </p>
 */

import utilities.Debug;

public class Test
{
	/** Base value used in exponentiation calculations. */
	private static int base;

	/** Exponent value used in exponentiation calculations. */
	private static int power;

	/** Debug level controlling program output detail. */
	private static int debugLevel;

	/**
	 * Main entry point of the application.
	 *
	 * @param args command-line arguments:
	 *             <ul>
	 *               <li>args[0] = base value</li>
	 *               <li>args[1] = exponent value</li>
	 *               <li>args[2] = debug level</li>
	 *             </ul>
	 */
	public static void main( String[] args )
	{
		long start, stop;

		// Parse command-line arguments.
		new Test().parseArgs( args );

		// Create debug utility using the selected debug level.
		Debug debug = new Debug( debugLevel );

		// Create algorithm instances.
		SimplePowerAlgorithm s1 = new SimplePowerAlgorithm();
		SmartPowerAlgorithm s2 = new SmartPowerAlgorithm();

		// Execute and time the simple power algorithm.
		start = System.nanoTime();
		s1.calculate( base, power, debug );
		stop = System.nanoTime();

		debug.output( 1, "Time for Simple: " + ( stop - start ) );

		// Execute and time the smart power algorithm.
		start = System.nanoTime();
		s2.calculate( base, power, debug );
		stop = System.nanoTime();

		debug.output( 1, "Time for Smart: " + ( stop - start ) );
	}

	/**
	 * Parses command-line arguments and stores them as class variables.
	 * <p>
	 * Expected arguments:
	 * </p>
	 * <ol>
	 *   <li>Base value</li>
	 *   <li>Exponent value</li>
	 *   <li>Debug level</li>
	 * </ol>
	 *
	 * @param args the command-line arguments provided to the program
	 */
	private void parseArgs( String[] args )
	{
		if( args.length < 3 )
		{
			System.out.println( "Not enough arguments." );
			System.out.println( "Usage: base power debug_level" );
			return;
		}

		// Display arguments for testing/debugging purposes.
		System.out.println( "Testing args..." );

		for( int i = 0; i < args.length; i++ )
		{
			System.out.println( args[i] );
		}

		System.out.println( "Done testing args!\n" );

		Test.base = Integer.parseInt( args[0] );
		Test.power = Integer.parseInt( args[1] );
		Test.debugLevel = Integer.parseInt( args[2] );
	}
}