package utilities;

/**
 * Utility class used to control and display debugging output
 * based on a specified debug level.
 * <p>
 * Messages are only displayed if their level is less than or equal
 * to the current application debug level.
 * </p>
 */
public class Debug
{
	/** Current application debug level. */
	private int appLevel;

	/**
	 * Constructs a Debug object with a specified debug level.
	 *
	 * @param level externally defined debug level
	 */
	public Debug( int level )
	{
		this.appLevel = level;
	}

	/**
	 * Returns the current debug level.
	 *
	 * @return the active debug level
	 */
	public int getLevel()
	{
		return this.appLevel;
	}

	/**
	 * Sets the current debug level.
	 *
	 * @param level the new debug level
	 */
	public void setLevel( int level )
	{
		this.appLevel = level;
	}

	/**
	 * Outputs a debug message if the provided level is less than
	 * or equal to the current application debug level.
	 *
	 * @param level the priority level of the debug message
	 * @param msg the debug output message
	 */
	public void output( int level, String msg )
	{
		if( level <= this.appLevel )
		{
			System.out.println( msg );
		}
		else // debug level too low
		{
			// Do nothing
		}
	}
}