package manager;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import exceptions.InvalidFileDataException;
import problemDomain.*;

/**
 * Reads product records from a text file and creates the matching product
 * objects.
 */

public class ProductReader {

	/**
	 * Reads the declared number of product records and constructs the corresponding
	 * product objects.
	 * 
	 * @param filepath the path to the product data file
	 * @return the product objects read from the file
	 * @throws IOException if the operation cannot be completed
	 */

	public static Product[] ReadProduct(String filepath)
			throws InvalidFileDataException, FileNotFoundException, IOException {
		// Initialize variable to hold products in array.
		int numberOfProducts;

		// Open the file for reading
		try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {

			// Check to see if the first line in the file is null an throw an exception if
			// so.
			String productData = reader.readLine();
			if (productData == null) {
				throw new InvalidFileDataException("Invalid number of products! The data in: " + filepath
						+ " is/might be invalid/missing or corrupt. Please check the file and try again.");
			}
			// Try and parse the first line of the file throwing an exception if it is not a
			// number.
			try {
				numberOfProducts = Integer.parseInt(productData.trim());
			} catch (NumberFormatException e) {
				throw new InvalidFileDataException("Invalid number of products! The data in: " + filepath
						+ " is/might be invalid/missing or corrupt. Please check the file and try again.");
			}

			if (numberOfProducts <= 0) {
				throw new InvalidFileDataException(
						"Invalid number of products, products cannot be less than or equal to zero! The data in: "
								+ filepath
								+ " is/might be invalid/missing or corrupt. Please check the file and try again.");
			}
			// Create an array to hold the product objects
			Product[] products = new Product[numberOfProducts];
			// Read each product record and create the corresponding product object
			for (int i = 0; i < numberOfProducts; i++) {
				String line = reader.readLine();
				if (line == null) {
					throw new InvalidFileDataException(
							"Missing product data in: " + filepath + ". Please check the file and try again.");
				}
				String[] fields = line.trim().split(" ");
				if (fields.length != 3) {
					throw new InvalidFileDataException(
							"Invalid product row in: " + filepath + ". Please check the file and try again.");
				}
				String name = fields[0];

				// Initialize variables to hold original and updated prices.
				double originalPrice;
				double updatedPrice;

				// Try parsing the prices and throw an exception if there is an error with the
				// price values.
				try {
					originalPrice = Double.parseDouble(fields[1]);
					updatedPrice = Double.parseDouble(fields[2]);
				} catch (NumberFormatException e) {
					throw new InvalidFileDataException(
							"Invalid price in: " + filepath + ". Please check the file and try again.");
				}

				// Create the corresponding product object based on the name throwing a
				// FileNotFoundException if the name is not recognized
				switch (name) {
				case "Dresses":
					products[i] = new Dresses(originalPrice, updatedPrice);
					break;
				case "Jackets":
					products[i] = new Jackets(originalPrice, updatedPrice);
					break;
				case "Jeans":
					products[i] = new Jeans(originalPrice, updatedPrice);
					break;
				case "Shoes":
					products[i] = new Shoes(originalPrice, updatedPrice);
					break;
				case "Shorts":
					products[i] = new Shorts(originalPrice, updatedPrice);
					break;
				case "Skirts":
					products[i] = new Skirts(originalPrice, updatedPrice);
					break;
				case "TShirts":
					products[i] = new TShirts(originalPrice, updatedPrice);
					break;
				default:
					throw new InvalidFileDataException("Invalid product name: '" + name + "'. The data in: " + filepath
							+ " is/might be invalid/missing or corrupt. Please check the file and try again.");
				}

			}
			return products;
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException(
					"The file: " + filepath + " was not found. Please check the file name and path.");
		}

	}
}