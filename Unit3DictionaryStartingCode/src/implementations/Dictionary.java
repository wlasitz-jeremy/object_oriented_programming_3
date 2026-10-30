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
    
    private static final int DEFAULT_CAPACITY = 10;
    
   

    public void create(int size) {
		// TODO Auto-generated method stub
		if (size <= 0) {
			size = DEFAULT_CAPACITY;
		}
		keys = new ArrayList<>(size);
		values = new ArrayList<>(size);
		
	}
 
	@Override
	public boolean insert(String key, Integer value) throws DuplicateKeyException {
		// TODO Auto-generated method stub
		if (keys.contains(key) || values.contains(value)) {
			throw new DuplicateKeyException();
		}
		if (key == null || value == null) {
			throw new NullPointerException();
		}
		keys.add(key);
		values.add(value);
		return true;
	}

	@Override
	public Integer remove(String key) {
		// TODO Auto-generated method stub
		 int i = this.keys.indexOf(key);
 
	        if (key == null) {
	            throw new NullPointerException("Value cannot be negative.");
	        }
 
	        if (i == -1) {
	            return null;
	        }
 
	        Integer removedValue = this.values.get(i);
 
	        this.keys.remove(i);
	        this.values.remove(i);
 
	        return removedValue;
	    }
 
	@Override
	public boolean update(String key, Integer value) {
		// TODO Auto-generated method stub
		int i = this.keys.indexOf(key);
 
	       if (key == null || value == null) {
	           throw new NullPointerException("Value cannot be negative.");
	       }
	       if (i == -1) {
	           return false;
	       }
	       this.values.set(i, value);
	       return true;
	    }
	
	@Override
	public Integer lookup(String key) {
		// TODO Auto-generated method stub
		if (key == null) {
			throw new NullPointerException();
		}
		if(keys.contains(key)) {
			int i = keys.indexOf(key);
			return values.get(i);
		}
		return null;
	}

}