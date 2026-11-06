package application;

import exceptions.DuplicateArgumentException;
import exceptions.ExcessiveArgumentsException;
import exceptions.InsufficientArgumentsException;
import exceptions.InvalidArgumentException;
import exceptions.InvalidComparisonTypeException;
import exceptions.InvalidFileDataException;
import exceptions.InvalidFileTypeException;
import exceptions.InvalidSortingAlgorithmException;

/**
 * Stores and validates the file, comparison, and sorting options supplied to
 * the application.
 */

public class CommandLineArgs {
	private static final String COMPARE_TYPES = "oud";
	private static final String SORT_TYPES = "bsimqz";

	private String fileName;
	private char compareType;
	private char sortType;

	private CommandLineArgs() {
	}

	/**
	 * Returns the path of the product input file.
	 * 
	 * @return the input file path
	 */

	public String getFileName() {
		return fileName;
	}

	/**
	 * Returns the selected comparison type.
	 * 
	 * @return the selected comparison type
	 */
	public char getCompareType() {
		return compareType;
	}

	/**
	 * Returns the selected sorting algorithm code.
	 * 
	 * @return the selected sorting algorithm code
	 */
	public char getSortType() {
		return sortType;
	}

	/**
	 * @throws IllegalArgumentException with a user friendly message if args are
	 *                                  invalid
	 */
	/**
	 * Parses the command-line flags, validates required values, and returns the
	 * application options.
	 * 
	 * @param args the command-line arguments supplied by the user
	 * @return validated command-line options
	 * @throws InvalidFileTypeException         if the file type is invalid or
	 *                                          missing
	 * @throws InvalidSortingAlgorithmException if the sorting algorithm is invalid
	 *                                          or missing
	 * @throws InvalidComparisonTypeException   if the comparison type is invalid or
	 *                                          missing
	 * @throws InsufficientArgumentsException   if not all required arguments are
	 *                                          provided
	 * @throws ExcessiveArgumentsException      if more than the allowed arguments
	 *                                          are provided
	 * @throws InvalidArgumentException         if an unrecognized argument is
	 *                                          provided
	 * @throws DuplicateArgumentException       if a duplicate argument is provided
	 * 
	 * @throws InvalidFileDataException         if the data in the file is corrupt
	 *                                          or missing
	 */
	public static CommandLineArgs parse(String[] args)
			throws InvalidFileTypeException, InvalidSortingAlgorithmException, InvalidComparisonTypeException,
			InsufficientArgumentsException, ExcessiveArgumentsException, InvalidArgumentException,
			DuplicateArgumentException, InvalidFileDataException {
		CommandLineArgs result = new CommandLineArgs();

		// Check for missing arguments and throw an exception with instructions if
		// none are provided.
		if (args == null || args.length == 0) {
			throw new InsufficientArgumentsException(
					"No arguments provided, -f(file_name/path) -t(compare_type) and -s(sorting_algorithm) are all required.");
		}

		// Check for insufficient arguments and throw an exception with instructions if
		// not all required flags are present.
		if (args.length < 3) {
			throw new InsufficientArgumentsException(
					"Insufficient arguments provided, -f(file_name/path) -t(compare_type) and -s(sorting_algorithm) are all required.");
		}

		// Check for insufficient arguments and throw an exception with instructions if
		// not all required flags are present.
		if (args.length > 3) {
			throw new ExcessiveArgumentsException(
					"Excessive arguments have been provided, -f(file_name/path) -t(compare_type) and -s(sorting_algorithm) are the only three allowed.");
		}

		// Track which required flags have been provided to ensure all are present.
		boolean hasF = false, hasT = false, hasS = false;

		// Iterate through each argument, normalize it, and validate its format and
		// value.
		for (String raw : args) {
			/**
			 * Normalize en/em dashes (copy-pasted from Word/PDF) to a plain hyphen
			 */
			String arg = raw.trim().replace('\u2013', '-').replace('\u2014', '-');

			// Check that the argument starts with a hyphen and has at least one character
			// following it. If not, throw an exception with usage instructions.
			if (arg.length() < 2 || arg.charAt(0) != '-') {
				throw new InvalidArgumentException(
						"Invalid argument type \"" + raw + "\": arguments must start with -f, -t or -s.");
			}

			// Extract the flag character and the value from the argument. The flag is the
			// second character, and the value is everything after it.
			char flag = Character.toLowerCase(arg.charAt(1));
			String value = arg.substring(2).trim();

			/**
			 * Strip surrounding quotes if the shell left them in
			 */
			if (value.length() >= 2 && value.startsWith("\"") && value.endsWith("\"")) {
				value = value.substring(1, value.length() - 1);
			}

			// Validate the flag and value based on the expected options. If the flag is
			// recognized, check for duplicates and validate the value. If the flag is
			// unrecognized, throw an exception with usage instructions.
			switch (flag) {
			case 'f':
				if (hasF)
					throw new DuplicateArgumentException("-f");
				if (value.isEmpty()) {
					throw new InvalidFileTypeException();
				}
				result.fileName = value;
				hasF = true;
				break;

			case 't':
				if (hasT)
					throw new DuplicateArgumentException("-t");
				result.compareType = parseChoice(value, COMPARE_TYPES, "-t",
						"o (original price), u (updated price) or d (real difference)");
				hasT = true;
				break;

			case 's':
				if (hasS)
					throw new DuplicateArgumentException("-s");
				result.sortType = parseChoice(value, SORT_TYPES, "-s",
						"b (bubble), s (selection), i (insertion), m (merge), q (quick) or z (heap)");
				hasS = true;
				break;

			// If the flag is not recognized, throw an exception with usage instructions.
			default:
				throw new InvalidArgumentException(
						"Invalid argument \"" + raw + "\": arguments must start with -f, -t or -s.\n");
			}
		}

		// After processing all arguments, check that all required flags were provided.
		// If any are missing, throw an exception with usage instructions.
		if (!hasF)
			throw new InvalidFileTypeException();
		if (!hasT)
			throw new InvalidComparisonTypeException("Missing required option -t(compare_type).\n");
		if (!hasS)
			throw new InvalidSortingAlgorithmException("Missing required option -s(sorting_algorithm).\n");

		return result;
	}

	/**
	 * Validates and normalizes a single comparison or sorting option.
	 * 
	 * @param value       the option value to validate
	 * @param allowed     the set of accepted option characters
	 * @param flag        the command-line flag being processed
	 * @param description the expected option description
	 * @return the normalized option character
	 * @throws InvalidArgumentException         if the value is invalid or not in
	 *                                          the allowed set
	 * @throws InvalidComparisonTypeException   if the comparison type is not valid
	 * @throws InvlidSoertingAlgorithmException if the sorting algorithm is not
	 *                                          valid
	 */

	private static char parseChoice(String value, String allowed, String flag, String description)
			throws InvalidComparisonTypeException, InvalidSortingAlgorithmException, InvalidArgumentException {

		// Rejects values that are not exactly one character or not in the allowed
		// values.
		if (value.length() != 1 || allowed.indexOf(Character.toLowerCase(value.charAt(0))) < 0) {

			// Throws a specific exception for invalid comparison, sorting or argument
			// choices.
			if ("-t".equals(flag)) {
				throw new InvalidComparisonTypeException(
						"Invalid comparison type \"" + value + "\" for " + flag + ". Expected " + description + ".");
			}
			if ("-s".equals(flag)) {
				throw new InvalidSortingAlgorithmException(
						"Invalid sorting algorithm \"" + value + "\" for " + flag + ". Expected " + description + ".");
			}
			throw new InvalidArgumentException(
					"Invalid argument \"" + value + "\" for " + flag + ". Expected " + description + ".");
		}

		return Character.toLowerCase(value.charAt(0));
	}

}