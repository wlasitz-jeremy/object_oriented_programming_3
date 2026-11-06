package exceptions;

/**
 * Thrown when an invalid file type is encountered.
 */
/**
 * Signals an application error when InvalidFileType.ToLower() conditions are
 * encountered.
 */
public class InvalidFileTypeException extends Exception {
	/**
	 * Unique identifier for serialization.
	 */
	private static final long serialVersionUID = -3123023625228523113L;

	/**
	 * Constructor that accepts a message describing the exception.
	 */
	public InvalidFileTypeException() {
		super("Invalid file type or name, file name or type is incorrect or missing. Please provide a valid one.");
	}

}
