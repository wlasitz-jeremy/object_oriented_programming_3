package exceptions;

/**
 * Thrown when the user does not provide enough command-line arguments to run
 * the application.
 */
public class InsufficientArgumentsException extends Exception {

	/**
	 * Unique identifier for serialization.
	 */
	private static final long serialVersionUID = 931108331991391735L;

	/**
	 * Constructor that accepts a message describing the exception.
	 * 
	 * @param message the message describing the exception
	 */
	public InsufficientArgumentsException(String message) {
		super(message);
	}

}
