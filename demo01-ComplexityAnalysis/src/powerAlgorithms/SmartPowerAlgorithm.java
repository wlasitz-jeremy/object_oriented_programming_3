package powerAlgorithms;

import utilities.Debug;

/**
 * Implements an optimized power algorithm using exponentiation by squaring.
 * <p>
 * This algorithm efficiently calculates the value of a base raised
 * to a given exponent by repeatedly squaring the base and reducing
 * the exponent by half.
 * </p>
 * <p>
 * Compared to simple repeated multiplication, this approach reduces
 * the number of required multiplications and improves performance.
 * </p>
 */
public class SmartPowerAlgorithm
{

	/**
	 * Calculates the power of a number using exponentiation by squaring.
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
		int y = base;
		int z = power;

		while( z > 0 )
		{
			// If z is odd, multiply x by the current value of y.
			if( z % 2 == 1 ) // z / 2 -> remainder == 1
			{
				x *= y;
			}

			// Divide exponent by 2.
			z /= 2;

			// Square the base.
			y *= y;

			debug.output( 3, "Smart -- " + x );
		}

		debug.output( 2, "Smart answer is: " + x );
	}
}