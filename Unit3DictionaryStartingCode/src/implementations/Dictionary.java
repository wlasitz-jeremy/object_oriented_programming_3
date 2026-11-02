package implementations;

import exceptions.DuplicateKeyException;
import utilities.DictionaryADT;

import java.util.ArrayList;

/**
 * An {@link ArrayList}-backed implementation of the {@link DictionaryADT}
 * interface, for use in CPRG 304 at SAIT.
 * <p>
 * This class stores {@link String}-to-{@link Integer} key-value pairs using two
 * parallel {@link ArrayList} instances — one for keys and one for values. A key
 * at index {@code i} in {@link #keys} always corresponds to the value at index
 * {@code i} in {@link #values}. All operations perform a linear scan of
 * {@link #keys} using {@link String#equals(Object)} to locate a match, then use
 * the same index to access or modify the corresponding entry in
 * {@link #values}. All keys must be unique; inserting a duplicate key throws a
 * {@link DuplicateKeyException}.
 * </p>
 *
 * <p>
 * {@link #create(int)} must be called before any other method to initialize the
 * internal lists. Calling {@link #create(int)} a second time resets the
 * dictionary, discarding all previously stored entries.
 * </p>
 */
public class Dictionary implements DictionaryADT {
	// -----------------------------------------------------------------------
	// Attributes
	// -----------------------------------------------------------------------

	/**
	 * The internal {@link ArrayList} of {@link String} keys. The key at index
	 * {@code i} corresponds to the value at index {@code i} in {@link #values}.
	 */
	private ArrayList<String> keys;

	/**
	 * The internal {@link ArrayList} of {@link Integer} values. The value at index
	 * {@code i} corresponds to the key at index {@code i} in {@link #keys}.
	 */
	private ArrayList<Integer> values;

	private static final int DEFAULT_CAPACITY = 10;

	@Override
	public void create(int size) {
		if (size <= 0) {
			size = DEFAULT_CAPACITY;
		}
		keys = new ArrayList<>(size);
		values = new ArrayList<>(size);

	}

	@Override
	public boolean insert(String key, Integer value) throws DuplicateKeyException {
		if (key == null) {
			throw new NullPointerException("Key cannot be null");
		} else if (value == null) {
			throw new NullPointerException("Value cannot be null");
		} else if (keys.contains(key)) {
			throw new DuplicateKeyException("Key value pair already exists");
		}

		keys.add(key);
		values.add(value);
		return true;
	}

	@Override
	public Integer remove(String key) {
		if (key == null) {
			throw new NullPointerException("Key cannot be null");
		} else if (keys.contains(key)) {
			int index = keys.indexOf(key);
			Integer value = values.get(index);
			keys.remove(index);
			values.remove(index);
			return value;
		}
		return null;
	}

	@Override
	public boolean update(String key, Integer value) {
		if (key == null) {
			throw new NullPointerException("Key cannot be null");
		} else if (value == null) {
			throw new NullPointerException("Value cannot be null");
		} else if (keys.contains(key)) {
			int index = keys.indexOf(key);
			values.set(index, value);
			return true;
		}
		return false;
	}

	@Override
	public Integer lookup(String key) {
		if (key == null) {
			throw new NullPointerException("Key cannot be null");
		} else if (keys.contains(key)) {
			int index = keys.indexOf(key);
			return values.get(index);
		} 
			return null;
	}

}