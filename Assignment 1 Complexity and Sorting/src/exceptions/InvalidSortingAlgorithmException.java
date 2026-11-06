package exceptions;

/**
 * Thrown when an invalid sorting algorithm is specified.
 */
/**
 * Signals an application error when InvalidSortingAlgorithm.ToLower()
 * conditions are encountered.
 */
public class InvalidSortingAlgorithmException extends Exception {
	/**
	 * Unique identifier for serialization.
	 */
	private static final long serialVersionUID = -4463904926282921531L;

	/**
	 * Constructor that accepts a message describing the exception.
	 * 
	 * @param message The message describing the exception.
	 */
	public InvalidSortingAlgorithmException(String message) {
		super(message);
	}

}