package exceptions;

/**
 * Thrown when invalid or corrupt file data is encountered.
 */
/**
 * Signals an application error when InvalidFileData.ToLower() conditions are
 * encountered.
 */
public class InvalidFileDataException extends Exception {
	/**
	 * Unique identifier for serialization.
	 */
	private static final long serialVersionUID = -2086244739516341885L;

	/**
	 * Constructor that accepts a message describing the exception.
	 * 
	 * @param message The message describing the exception.
	 */
	public InvalidFileDataException(String message) {
		super(message);
	}

}