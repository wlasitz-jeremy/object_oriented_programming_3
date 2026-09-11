package exceptions;

/**
 * Exception thrown when an invalid operation is performed on a Counter.
 * <p>
 * This includes attempts to set the counter to a negative value or
 * decrement the counter below zero.
 * </p>
 */
@SuppressWarnings( "serial" )
public class InvalidCounterException extends Exception
{
	/**
	 * Constructs an InvalidCounterException with no detail message.
	 */
	public InvalidCounterException()
	{
		super();
	}

	/**
	 * Constructs an InvalidCounterException with a specific error message.
	 *
	 * @param message error message describing the cause of the exception
	 */
	public InvalidCounterException( String message )
	{
		super( message );
	}
}