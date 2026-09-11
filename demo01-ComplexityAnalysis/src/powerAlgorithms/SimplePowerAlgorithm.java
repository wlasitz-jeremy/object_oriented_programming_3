package powerAlgorithms;

import utilities.Debug;

/**
 * Implements a simple iterative power algorithm.
 * <p>
 * This algorithm calculates the value of a base raised to a given
 * exponent by repeatedly multiplying the base value.
 * </p>
 */
public class SimplePowerAlgorithm
{

	/**
	 * Calculates the power of a number using repeated multiplication.
	 * <p>
	 * Intermediate calculation steps and the final result are optionally
	 * displayed using the provided {@link Debug} utility.
	 * </p>
	 *
	 * @param base the base value to raise
	 * @param power the exponent value
	 * @param debug the debug utility used for output messages
	 */
	public void calculate( int base, int power, Debug debug )
	{
		int x = 1;

		for( int i = 0; i < power; i++ )
		{
			x *= base;

			debug.output( 3, "Simple -- " + x );
		}

		debug.output( 2, "Simple answer is: " + x );
	}
}