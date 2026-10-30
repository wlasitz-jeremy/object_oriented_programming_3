package exceptions;

/**
 * Exception thrown when an attempt is made to insert a duplicate key
 * into a data structure that requires unique keys.
 * <p>
 * This is a checked exception, meaning it must be explicitly handled
 * or declared by methods that may throw it.
 * </p>
 */
public class DuplicateKeyException extends Exception
{
	/**
	 * Serial version UID for serialization compatibility.
	 */
	private static final long serialVersionUID = 682267963980463371L;

	/**
	 * Constructs a {@code DuplicateKeyException} with no detail message.
	 */
	public DuplicateKeyException()
	{
		super();
	}

	/**
	 * Constructs a {@code DuplicateKeyException} with a specified detail message.
	 *
	 * @param message a description of the error cause
	 */
	public DuplicateKeyException( String message )
	{
		super( message );
	}
}