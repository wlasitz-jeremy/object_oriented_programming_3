package exceptions;

/**
 * Thrown when an invalid comparison type is specified. 
 */
/**
 * Signals an application error when InvalidComparisonType.ToLower() conditions
 * are encountered.
 */
public class InvalidComparisonTypeException extends Exception {
	/**
	 * Unique identifier for serialization.
	 */
	private static final long serialVersionUID = -4484996527520614932L;

	/**
	 * Constructor that accepts a message describing the exception.
	 * 
	 * @param message The message describing the exception.
	 */
	public InvalidComparisonTypeException(String message) {
		super(message);
	}

}