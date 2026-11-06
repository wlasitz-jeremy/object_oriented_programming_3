package exceptions;

/**
 * Thrown when an invalid argument is specified.
 */
public class InvalidArgumentException extends Exception {
	/**
	 * Unique identifier for serialization.
	 */
	private static final long serialVersionUID = 7149332767148787436L;

	/**
	 * Constructs a new InvalidArgumentException with the specified detail message.
	 *
	 * @param message the detail message
	 */
	public InvalidArgumentException(String message) {
		super(message);
	}

}
