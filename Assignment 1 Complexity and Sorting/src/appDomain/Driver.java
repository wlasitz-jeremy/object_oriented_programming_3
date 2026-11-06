package appDomain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import application.CommandLineArgs;
import application.SortingApplication;
import exceptions.DuplicateArgumentException;
import exceptions.ExcessiveArgumentsException;
import exceptions.InsufficientArgumentsException;
import exceptions.InvalidArgumentException;
import exceptions.InvalidComparisonTypeException;
import exceptions.InvalidFileDataException;
import exceptions.InvalidFileTypeException;
import exceptions.InvalidSortingAlgorithmException;
import problemDomain.Product;

/**
 * Runs the product sorting application, handles invalid command-line input and
 * missing files, then displays the sorted products.
 */

public class Driver {

	/**
	 * Starts the application by parsing its arguments, validating the input file,
	 * sorting the products, and printing the results.
	 * 
	 * @param args the command-line arguments supplied by the user
	 * @throws FileNotFoundException            if the operation cannot be completed
	 * @throws IOException                      if the operation cannot be completed
	 * @throws InvalidFileTypeException         if the file type is invalid
	 * @throws InvalidSortingAlgorithmException if the sorting algorithm is invalid
	 * @throws InvalidComparisonTypeException   if the comparison type is invalid
	 * @throws InvalidFileDataException         if the data in the file is invalid
	 *                                          or corrupt
	 * @throws InsufficientArgumentsException   if the required command-line
	 *                                          arguments are missing
	 * @throws ExcessiveArgumentsException      if more than the allowed
	 *                                          command-line arguments are provided
	 * @throws InvalidArgumentException         if an unrecognized command-line
	 *                                          argument is provided
	 * @throws DuplicateArgumentException       if a duplicate argument is provided
	 */

	public static void main(String[] args)
			throws FileNotFoundException, IOException, InvalidFileTypeException, InvalidSortingAlgorithmException,
			InvalidComparisonTypeException, InvalidFileDataException, InsufficientArgumentsException,
			ExcessiveArgumentsException, InvalidArgumentException, DuplicateArgumentException {
		CommandLineArgs options;

		// Parse the command-line arguments and handle any errors based on custom
		// exceptions.
		try {
			options = CommandLineArgs.parse(args);
		} catch (InvalidFileTypeException e) {
			System.out.println(e.getMessage());
			return;
		} catch (InvalidFileDataException e) {
			System.out.println(e.getMessage());
			return;
		} catch (InvalidComparisonTypeException e) {
			System.out.println(e.getMessage());
			return;
		} catch (InvalidSortingAlgorithmException e) {
			System.out.println(e.getMessage());
			return;
		} catch (InvalidArgumentException e) {
			System.out.println(e.getMessage());
			return;
		} catch (InsufficientArgumentsException e) {
			System.out.println(e.getMessage());
			return;
		} catch (ExcessiveArgumentsException e) {
			System.out.println(e.getMessage());
			return;
		} catch (DuplicateArgumentException e) {
			System.out.println(e.getMessage());
			return;
		}

		File file = new File(options.getFileName());

		// Check if the file exists and throw a custom exception when it does not.
		try {
			if (!file.exists()) {
				throw new FileNotFoundException();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find the file \"" + options.getFileName() + "\". Check the file name and path.");
			return;
		}

		// Run the sorting application and handle any errors.
		Product[] products;
		try {
		    products = SortingApplication.run(options);
		} catch (InvalidFileDataException e) {
		    System.out.println(e.getMessage());
		    return;
		}
		if (products == null) {
			return;
		}

		printProducts(products, options.getCompareType());
	}

	/**
	 * Prints all products for small results and representative positions for larger
	 * result sets.
	 * 
	 * @param products    the array of Product objects to sort
	 * @param compareType the selected comparison type
	 */

	private static void printProducts(Product[] products, char compareType) {
		// Print the products based on the number of products and the selected
		// comparison type.
		if (products.length == 0) {
			System.out.println("No products to display.");
			return;
		}

		// Print all products if there are 10 or fewer, otherwise print representative
		// positions based on the number of products.
		if (products.length <= 10) {
			for (int i = 0; i < products.length; i++) {
				printProduct(products[i], compareType, ordinalLabel(i + 1));
			}
			return;
		}

		// Print representative positions for larger result sets, with a different
		// interval based on the number of products.
		int interval = products.length <= 1000 ? 100 : 1000;
		printProduct(products[0], compareType, "First element is:");
		for (int i = interval - 1; i < products.length - 1; i += interval) {
			printProduct(products[i], compareType, ordinalLabel(i + 1));
		}
		if (products.length > 1) {
			printProduct(products[products.length - 1], compareType, "Last element is:");
		}
	}

	/**
	 * Builds the display label for a one-based item position.
	 * 
	 * @param itemNumber the one-based position of the product
	 * @return the ordinal label for the requested product position
	 */

	private static String ordinalLabel(int itemNumber) {
		// Determine the appropriate ordinal suffix for the item number and return the
		// formatted label.
		String suffix = itemNumber == 1 ? "st" : itemNumber == 2 ? "nd" : itemNumber == 3 ? "rd" : "th";
		return itemNumber + "-" + suffix + " element:";
	}

	/**
	 * Prints the selected price value and product type for one result position.
	 * 
	 * @param product       the product whose display data is printed
	 * @param compareType   the selected comparison type
	 * @param positionLabel the label identifying this product position
	 */

	private static void printProduct(Product product, char compareType, String positionLabel) {
		// Determine the label and value to display based on the selected comparison
		// type.
		String label;
		double value;
		// Use a switch statement to handle the different comparison types and set the
		// appropriate label and value for display.
		switch (compareType) {
		case 'o':
			label = "Original Price";
			value = product.getOriginalPrice();
			break;
		case 'u':
			label = "Updated Price";
			value = product.getUpdatedPrice();
			break;
		case 'd':
			label = "Real Difference";
			value = product.calcRealDiff();
			break;
		default:
			return;
		}
		// Print the product type and the selected price value with appropriate
		// formatting.
		System.out.printf("%-22s products.%-12s %s: %s%n", positionLabel, product.getProdType(), label,
				compareType == 'd' ? Double.toString(value) : String.format("%.2f", value));
	}

}