package exceptions;

/**
 * Thrown when a duplicate argument is specified.
 */
public class DuplicateArgumentException extends Exception {

	/**
	 * Unique identifier for serialization.
	 */
	private static final long serialVersionUID = 5946977893032508452L;

	/**
	 * Constructor that accepts a message describing the exception.
	 * 
	 * @param flag the duplicate argument that caused the exception
	 */
	public DuplicateArgumentException(String flag) {
		super("Duplicate argument: " + flag + ". It was provided more than once.");
	}

}
