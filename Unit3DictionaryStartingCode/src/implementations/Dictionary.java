package implementations;

import exceptions.DuplicateKeyException;
import utilities.DictionaryADT;

import java.util.ArrayList;

/**
 * An {@link ArrayList}-backed implementation of the {@link DictionaryADT}
 * interface, for use in CPRG 304 at SAIT.
 * <p>
 * This class stores {@link String}-to-{@link Integer} key-value pairs using
 * two parallel {@link ArrayList} instances — one for keys and one for values.
 * A key at index {@code i} in {@link #keys} always corresponds to the value
 * at index {@code i} in {@link #values}. All operations perform a linear scan
 * of {@link #keys} using {@link String#equals(Object)} to locate a match,
 * then use the same index to access or modify the corresponding entry in
 * {@link #values}. All keys must be unique; inserting a duplicate key throws
 * a {@link DuplicateKeyException}.
 * </p>
 *
 * <p>{@link #create(int)} must be called before any other method to
 * initialize the internal lists. Calling {@link #create(int)} a second time
 * resets the dictionary, discarding all previously stored entries.</p>
 */
public class Dictionary implements DictionaryADT
{
    // -----------------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------------

    /**
     * The internal {@link ArrayList} of {@link String} keys.
     * The key at index {@code i} corresponds to the value at index {@code i}
     * in {@link #values}.
     */
    private ArrayList<String> keys;

    /**
     * The internal {@link ArrayList} of {@link Integer} values.
     * The value at index {@code i} corresponds to the key at index {@code i}
     * in {@link #keys}.
     */
    private ArrayList<Integer> values;

	@Override
	public void create(int size) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean insert(String key, Integer value) throws DuplicateKeyException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Integer remove(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(String key, Integer value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Integer lookup(String key) {
		// TODO Auto-generated method stub
		return null;
	}

}