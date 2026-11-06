package exceptions;

/**
 * Thrown when the user does not provide enough command-line arguments to run
 * the application.
 */
public class ExcessiveArgumentsException extends Exception {

	/**
	 * Unique identifier for serialization.
	 */
	private static final long serialVersionUID = 5494919064984340329L;

	/**
	 * Constructor that accepts a message describing the exception.
	 * 
	 * @param message the message describing the exception
	 */
	public ExcessiveArgumentsException(String message) {
		super(message);
	}

}
