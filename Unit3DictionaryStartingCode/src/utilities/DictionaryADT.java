package utilities;

import exceptions.DuplicateKeyException;

/**
 * Defines the contract for a Dictionary Abstract Data Type (ADT).
 * <p>
 * A dictionary stores key-value pairs and provides operations to insert,
 * remove, update, and retrieve values based on unique {@link String} keys.
 * Each key in the dictionary must be unique; duplicate keys are not permitted
 * and are signalled by a {@link DuplicateKeyException} on insertion.
 * This interface is intended as the foundation for a Dictionary implementation
 * used in CPRG 304 at SAIT.
 * </p>
 *
 * <p><strong>Design Note – Fixed Key and Value Types:</strong><br>
 * This interface is intentionally designed to map {@link String} keys to
 * {@link Integer} values only. A fully reusable, type-safe design would use
 * Java generics (e.g., {@code DictionaryADT<K, V>}), allowing any key and
 * value types to be stored in the dictionary. Generic programming is outside
 * the scope of CPRG 304 and has therefore been omitted. All key parameters
 * are fixed as {@link String} and all value parameters and return types are
 * fixed as {@link Integer} throughout this interface and its implementations.
 * </p>
 *
 * <p><strong>Note on missing-key return values:</strong><br>
 * Because value types are {@link Integer} (a reference type), methods that
 * fail to locate a key (e.g., {@link #remove(String)} and
 * {@link #lookup(String)}) return {@code null} to signal that the key was
 * not found. Callers must perform a {@code null} check on the return value
 * before use to avoid a {@link NullPointerException}.
 * </p>
 */
public interface DictionaryADT
{
    /**
     * Initializes this dictionary with the specified initial capacity.
     * <p>
     * This method must be called before any other operations are performed
     * on the dictionary. If the provided {@code size} is less than or equal
     * to {@code 0}, implementations may substitute a default capacity of
     * {@code 10}.
     * </p>
     *
     * @param size  the initial capacity of the dictionary; must be greater
     *              than {@code 0}
     */
    public void create( int size );

    /**
     * Inserts a new key-value pair into this dictionary.
     * <p>
     * The {@link String} key must be unique within the dictionary. If the
     * key already exists, a {@link DuplicateKeyException} is thrown and the
     * existing mapping is left unchanged. Use {@link #update(String, Integer)}
     * to modify the value of an existing key.
     * </p>
     *
     * @param key    the unique {@link String} key to insert;
     *               must not be {@code null}
     * @param value  the {@link Integer} value to associate with the key;
     *               must not be {@code null}
     * @return {@code true} if the key-value pair was successfully inserted
     * @throws DuplicateKeyException if a mapping for {@code key} already
     *                               exists in this dictionary
     * @throws NullPointerException  if {@code key} or {@code value} is
     *                               {@code null}
     */
    public boolean insert( String key, Integer value ) throws DuplicateKeyException;

    /**
     * Removes the key-value pair associated with the specified key from this
     * dictionary.
     * <p>
     * If the key exists, it and its associated value are removed and the
     * {@link Integer} value is returned. If the key is not found,
     * {@code null} is returned and the dictionary is left unchanged.
     * </p>
     *
     * @param key  the {@link String} key whose mapping is to be removed;
     *             must not be {@code null}
     * @return the {@link Integer} value that was associated with the removed
     *         key, or {@code null} if the key does not exist in this
     *         dictionary
     * @throws NullPointerException if {@code key} is {@code null}
     */
    public Integer remove( String key );

    /**
     * Replaces the value associated with the specified key in this dictionary.
     * <p>
     * If the key exists, its associated value is replaced with {@code value}
     * and {@code true} is returned. If the key is not found, the dictionary
     * is left unchanged and {@code false} is returned. Use
     * {@link #insert(String, Integer)} to add a new key-value pair.
     * </p>
     *
     * @param key    the {@link String} key whose associated value is to be
     *               replaced; must not be {@code null}
     * @param value  the new {@link Integer} value to associate with the key;
     *               must not be {@code null}
     * @return {@code true} if the value was successfully updated;
     *         {@code false} if the key does not exist in this dictionary
     * @throws NullPointerException if {@code key} or {@code value} is
     *                              {@code null}
     */
    public boolean update( String key, Integer value );

    /**
     * Retrieves the {@link Integer} value associated with the specified key
     * in this dictionary.
     * <p>
     * Performs a lookup by key and returns the associated {@link Integer}
     * value if found. If the key is not present in the dictionary,
     * {@code null} is returned. The dictionary is not modified by this call.
     * </p>
     *
     * @param key  the {@link String} key to look up;
     *             must not be {@code null}
     * @return the {@link Integer} value associated with {@code key}, or
     *         {@code null} if the key does not exist in this dictionary
     * @throws NullPointerException if {@code key} is {@code null}
     */
    public Integer lookup( String key );
}