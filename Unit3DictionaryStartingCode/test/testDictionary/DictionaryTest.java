package testDictionary;

import exceptions.DuplicateKeyException;
import implementations.Dictionary;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * JUnit 4 test suite for the Dictionary class.
 * Tests all methods defined in the DictionaryADT interface.
 *
 * <p>A fresh {@link Dictionary} instance is created and initialized with a
 * capacity of {@code 10} before each test via {@link #setUp()}. Unless a
 * test explicitly calls {@link #populateDictionary()}, the dictionary starts
 * empty.</p>
 *
 * <p>Standard key-value pairs used across most tests
 * (populated via {@link #populateDictionary()}):</p>
 * <pre>
 *   "Apple"  -> 1
 *   "Banana" -> 2
 *   "Cherry" -> 3
 *   "Date"   -> 4
 *   "Elder"  -> 5
 * </pre>
 */
public class DictionaryTest
{
    // -----------------------------------------------------------------------
    // Fields & Setup
    // -----------------------------------------------------------------------

    /** The Dictionary instance under test – re-created fresh before every test. */
    private Dictionary dict;

    /**
     * Creates and initializes a fresh {@link Dictionary} with capacity
     * {@code 10} before each test method.
     */
    @Before
    public void setUp()
    {
        dict = new Dictionary();
        dict.create( 10 );
    }

    /**
     * Inserts 5 standard key-value pairs into the dictionary.
     * Used by tests that require a pre-populated dictionary.
     */
    private void populateDictionary()
    {
        try
        {
            dict.insert( "Apple",  1 );
            dict.insert( "Banana", 2 );
            dict.insert( "Cherry", 3 );
            dict.insert( "Date",   4 );
            dict.insert( "Elder",  5 );
        }
        catch( DuplicateKeyException e )
        {
            fail( "populateDictionary() should not throw DuplicateKeyException." );
        }
    }


    // -----------------------------------------------------------------------
    // create(int)
    // -----------------------------------------------------------------------

    /** A newly created dictionary accepts insertions normally. */
    @Test
    public void testCreate_validSize_dictionaryAcceptsInsert()
    {
        try
        {
            assertTrue( dict.insert( "Apple", 1 ) );
        }
        catch( DuplicateKeyException e )
        {
            fail( "Should not have thrown DuplicateKeyException." );
        }
    }

    /** Creating with size 1 still permits at least one insertion. */
    @Test
    public void testCreate_sizeOne_permitsOneInsertion()
    {
        dict.create( 1 );
        try
        {
            assertTrue( dict.insert( "Apple", 1 ) );
        }
        catch( DuplicateKeyException e )
        {
            fail( "Should not have thrown DuplicateKeyException." );
        }
    }

    /** Creating with a large capacity accepts many insertions. */
    @Test
    public void testCreate_largeSize_acceptsManyInsertions()
    {
        dict.create( 100 );
        try
        {
            for( int i = 0; i < 50; i++ )
            {
                dict.insert( "Key" + i, i );
            }
        }
        catch( DuplicateKeyException e )
        {
            fail( "Should not have thrown DuplicateKeyException." );
        }
    }

    /** Calling create a second time resets the dictionary. */
    @Test
    public void testCreate_calledTwice_resetsDictionary()
    {
        try
        {
            dict.insert( "Apple", 1 );
        }
        catch( DuplicateKeyException e )
        {
            fail( "Should not have thrown DuplicateKeyException." );
        }
        dict.create( 10 );
        assertNull( "Dictionary should be empty after second create().",
                dict.lookup( "Apple" ) );
    }


    // -----------------------------------------------------------------------
    // insert(String, Integer)
    // -----------------------------------------------------------------------

    /** Inserting a valid key-value pair returns true. */
    @Test
    public void testInsert_validPair_returnsTrue()
    {
        try
        {
            assertTrue( dict.insert( "Apple", 1 ) );
        }
        catch( DuplicateKeyException e )
        {
            fail( "Should not have thrown DuplicateKeyException." );
        }
    }

    /** Inserted value is immediately retrievable via lookup. */
    @Test
    public void testInsert_validPair_valueRetrievableViaLookup()
    {
        try
        {
            dict.insert( "Apple", 1 );
        }
        catch( DuplicateKeyException e )
        {
            fail( "Should not have thrown DuplicateKeyException." );
        }
        assertEquals( Integer.valueOf( 1 ), dict.lookup( "Apple" ) );
    }

    /** Multiple distinct keys can all be inserted successfully. */
    @Test
    public void testInsert_multipleDistinctKeys_allInsertedSuccessfully()
    {
        populateDictionary();
        assertEquals( Integer.valueOf( 1 ), dict.lookup( "Apple" ) );
        assertEquals( Integer.valueOf( 2 ), dict.lookup( "Banana" ) );
        assertEquals( Integer.valueOf( 3 ), dict.lookup( "Cherry" ) );
        assertEquals( Integer.valueOf( 4 ), dict.lookup( "Date" ) );
        assertEquals( Integer.valueOf( 5 ), dict.lookup( "Elder" ) );
    }

    /** Inserting a duplicate key throws DuplicateKeyException. */
    @Test( expected = DuplicateKeyException.class )
    public void testInsert_duplicateKey_throwsDuplicateKeyException()
            throws DuplicateKeyException
    {
        dict.insert( "Apple", 1 );
        dict.insert( "Apple", 99 );
    }

    /** Existing value is unchanged after a duplicate insertion attempt. */
    @Test
    public void testInsert_duplicateKey_existingValueUnchanged()
    {
        try
        {
            dict.insert( "Apple", 1 );
        }
        catch( DuplicateKeyException e )
        {
            fail( "First insert should not throw." );
        }
        try
        {
            dict.insert( "Apple", 99 );
            fail( "Second insert should have thrown DuplicateKeyException." );
        }
        catch( DuplicateKeyException e )
        {
            // expected
        }
        assertEquals( "Original value should be unchanged after duplicate insert attempt.",
                Integer.valueOf( 1 ), dict.lookup( "Apple" ) );
    }

    /** Inserting a null key throws NullPointerException. */
    @Test( expected = NullPointerException.class )
    public void testInsert_nullKey_throwsNullPointerException()
            throws DuplicateKeyException
    {
        dict.insert( null, 1 );
    }

    /** Inserting a null value throws NullPointerException. */
    @Test( expected = NullPointerException.class )
    public void testInsert_nullValue_throwsNullPointerException()
            throws DuplicateKeyException
    {
        dict.insert( "Apple", null );
    }

    /** Inserting a value of zero is permitted. */
    @Test
    public void testInsert_zeroValue_insertedSuccessfully()
    {
        try
        {
            dict.insert( "Zero", 0 );
        }
        catch( DuplicateKeyException e )
        {
            fail( "Should not have thrown DuplicateKeyException." );
        }
        assertEquals( Integer.valueOf( 0 ), dict.lookup( "Zero" ) );
    }

    /** Inserting a negative value is permitted. */
    @Test
    public void testInsert_negativeValue_insertedSuccessfully()
    {
        try
        {
            dict.insert( "Negative", -42 );
        }
        catch( DuplicateKeyException e )
        {
            fail( "Should not have thrown DuplicateKeyException." );
        }
        assertEquals( Integer.valueOf( -42 ), dict.lookup( "Negative" ) );
    }


    // -----------------------------------------------------------------------
    // remove(String)
    // -----------------------------------------------------------------------

    /** Removing an existing key returns its associated value. */
    @Test
    public void testRemove_existingKey_returnsAssociatedValue()
    {
        populateDictionary();
        assertEquals( Integer.valueOf( 1 ), dict.remove( "Apple" ) );
    }

    /** After removal, the key is no longer retrievable via lookup. */
    @Test
    public void testRemove_existingKey_keyNoLongerInDictionary()
    {
        populateDictionary();
        dict.remove( "Apple" );
        assertNull( "Removed key should no longer be present.",
                dict.lookup( "Apple" ) );
    }

    /** Removing a key does not affect other keys in the dictionary. */
    @Test
    public void testRemove_existingKey_otherKeysUnaffected()
    {
        populateDictionary();
        dict.remove( "Apple" );
        assertEquals( Integer.valueOf( 2 ), dict.lookup( "Banana" ) );
        assertEquals( Integer.valueOf( 3 ), dict.lookup( "Cherry" ) );
    }

    /** Removing a key that does not exist returns null. */
    @Test
    public void testRemove_missingKey_returnsNull()
    {
        populateDictionary();
        assertNull( dict.remove( "Mango" ) );
    }

    /** Removing from an empty dictionary returns null. */
    @Test
    public void testRemove_emptyDictionary_returnsNull()
    {
        assertNull( dict.remove( "Apple" ) );
    }

    /** Removing the same key twice returns null on the second call. */
    @Test
    public void testRemove_calledTwiceOnSameKey_secondCallReturnsNull()
    {
        populateDictionary();
        dict.remove( "Apple" );
        assertNull( "Second removal of same key should return null.",
                dict.remove( "Apple" ) );
    }

    /** After removing all inserted keys, all lookups return null. */
    @Test
    public void testRemove_allKeys_dictionaryEffectivelyEmpty()
    {
        populateDictionary();
        dict.remove( "Apple" );
        dict.remove( "Banana" );
        dict.remove( "Cherry" );
        dict.remove( "Date" );
        dict.remove( "Elder" );
        assertNull( dict.lookup( "Apple" ) );
        assertNull( dict.lookup( "Banana" ) );
        assertNull( dict.lookup( "Cherry" ) );
        assertNull( dict.lookup( "Date" ) );
        assertNull( dict.lookup( "Elder" ) );
    }

    /** Removing a null key throws NullPointerException. */
    @Test( expected = NullPointerException.class )
    public void testRemove_nullKey_throwsNullPointerException()
    {
        dict.remove( null );
    }

    /** A removed key can be re-inserted without a DuplicateKeyException. */
    @Test
    public void testRemove_thenReinsert_succeedsWithoutException()
    {
        populateDictionary();
        dict.remove( "Apple" );
        try
        {
            assertTrue( dict.insert( "Apple", 99 ) );
        }
        catch( DuplicateKeyException e )
        {
            fail( "Re-inserting a removed key should not throw DuplicateKeyException." );
        }
        assertEquals( Integer.valueOf( 99 ), dict.lookup( "Apple" ) );
    }


    // -----------------------------------------------------------------------
    // update(String, Integer)
    // -----------------------------------------------------------------------

    /** Updating an existing key returns true. */
    @Test
    public void testUpdate_existingKey_returnsTrue()
    {
        populateDictionary();
        assertTrue( dict.update( "Apple", 99 ) );
    }

    /** After update, lookup returns the new value. */
    @Test
    public void testUpdate_existingKey_newValueRetrievableViaLookup()
    {
        populateDictionary();
        dict.update( "Apple", 99 );
        assertEquals( Integer.valueOf( 99 ), dict.lookup( "Apple" ) );
    }

    /** Updating a key does not affect other keys in the dictionary. */
    @Test
    public void testUpdate_existingKey_otherKeysUnaffected()
    {
        populateDictionary();
        dict.update( "Apple", 99 );
        assertEquals( Integer.valueOf( 2 ), dict.lookup( "Banana" ) );
        assertEquals( Integer.valueOf( 3 ), dict.lookup( "Cherry" ) );
    }

    /** Updating a key that does not exist returns false. */
    @Test
    public void testUpdate_missingKey_returnsFalse()
    {
        populateDictionary();
        assertFalse( dict.update( "Mango", 99 ) );
    }

    /** Updating on an empty dictionary returns false. */
    @Test
    public void testUpdate_emptyDictionary_returnsFalse()
    {
        assertFalse( dict.update( "Apple", 99 ) );
    }

    /** Updating a missing key does not add it to the dictionary. */
    @Test
    public void testUpdate_missingKey_doesNotInsertKey()
    {
        populateDictionary();
        dict.update( "Mango", 99 );
        assertNull( "Update on missing key should not insert it.",
                dict.lookup( "Mango" ) );
    }

    /** Updating a key with the same value it already holds returns true. */
    @Test
    public void testUpdate_sameValueAsExisting_returnsTrue()
    {
        populateDictionary();
        assertTrue( dict.update( "Apple", 1 ) );
        assertEquals( Integer.valueOf( 1 ), dict.lookup( "Apple" ) );
    }

    /** Updating a key to a zero value is permitted. */
    @Test
    public void testUpdate_toZeroValue_updatesSuccessfully()
    {
        populateDictionary();
        dict.update( "Apple", 0 );
        assertEquals( Integer.valueOf( 0 ), dict.lookup( "Apple" ) );
    }

    /** Updating a key to a negative value is permitted. */
    @Test
    public void testUpdate_toNegativeValue_updatesSuccessfully()
    {
        populateDictionary();
        dict.update( "Apple", -10 );
        assertEquals( Integer.valueOf( -10 ), dict.lookup( "Apple" ) );
    }

    /** Updating a null key throws NullPointerException. */
    @Test( expected = NullPointerException.class )
    public void testUpdate_nullKey_throwsNullPointerException()
    {
        dict.update( null, 99 );
    }

    /** Updating with a null value throws NullPointerException. */
    @Test( expected = NullPointerException.class )
    public void testUpdate_nullValue_throwsNullPointerException()
    {
        populateDictionary();
        dict.update( "Apple", null );
    }

    /** Updating the same key multiple times retains the last value. */
    @Test
    public void testUpdate_calledMultipleTimes_retainsLastValue()
    {
        populateDictionary();
        dict.update( "Apple", 10 );
        dict.update( "Apple", 20 );
        dict.update( "Apple", 30 );
        assertEquals( Integer.valueOf( 30 ), dict.lookup( "Apple" ) );
    }


    // -----------------------------------------------------------------------
    // lookup(String)
    // -----------------------------------------------------------------------

    /** Lookup returns the correct value for an existing key. */
    @Test
    public void testLookup_existingKey_returnsCorrectValue()
    {
        populateDictionary();
        assertEquals( Integer.valueOf( 1 ), dict.lookup( "Apple" ) );
    }

    /** Lookup returns correct values for all inserted keys. */
    @Test
    public void testLookup_allInsertedKeys_returnCorrectValues()
    {
        populateDictionary();
        assertEquals( Integer.valueOf( 1 ), dict.lookup( "Apple" ) );
        assertEquals( Integer.valueOf( 2 ), dict.lookup( "Banana" ) );
        assertEquals( Integer.valueOf( 3 ), dict.lookup( "Cherry" ) );
        assertEquals( Integer.valueOf( 4 ), dict.lookup( "Date" ) );
        assertEquals( Integer.valueOf( 5 ), dict.lookup( "Elder" ) );
    }

    /** Lookup returns null for a key that does not exist. */
    @Test
    public void testLookup_missingKey_returnsNull()
    {
        populateDictionary();
        assertNull( dict.lookup( "Mango" ) );
    }

    /** Lookup returns null on an empty dictionary. */
    @Test
    public void testLookup_emptyDictionary_returnsNull()
    {
        assertNull( dict.lookup( "Apple" ) );
    }

    /** Lookup does not modify the dictionary. */
    @Test
    public void testLookup_doesNotModifyDictionary()
    {
        populateDictionary();
        dict.lookup( "Apple" );
        assertEquals( Integer.valueOf( 1 ), dict.lookup( "Apple" ) );
    }

    /** Lookup called repeatedly returns the same value each time. */
    @Test
    public void testLookup_calledRepeatedly_returnsSameValue()
    {
        populateDictionary();
        assertEquals( Integer.valueOf( 2 ), dict.lookup( "Banana" ) );
        assertEquals( Integer.valueOf( 2 ), dict.lookup( "Banana" ) );
        assertEquals( Integer.valueOf( 2 ), dict.lookup( "Banana" ) );
    }

    /** Lookup returns null for a key that was removed. */
    @Test
    public void testLookup_afterRemove_returnsNull()
    {
        populateDictionary();
        dict.remove( "Apple" );
        assertNull( dict.lookup( "Apple" ) );
    }

    /** Lookup returns updated value after an update. */
    @Test
    public void testLookup_afterUpdate_returnsNewValue()
    {
        populateDictionary();
        dict.update( "Apple", 99 );
        assertEquals( Integer.valueOf( 99 ), dict.lookup( "Apple" ) );
    }

    /** Lookup with a null key throws NullPointerException. */
    @Test( expected = NullPointerException.class )
    public void testLookup_nullKey_throwsNullPointerException()
    {
        dict.lookup( null );
    }


    // -----------------------------------------------------------------------
    // Interaction Tests
    // -----------------------------------------------------------------------

    /** Insert then lookup finds the value immediately. */
    @Test
    public void testInteraction_insertThenLookup_valueFound()
    {
        try
        {
            dict.insert( "Apple", 42 );
        }
        catch( DuplicateKeyException e )
        {
            fail( "Should not have thrown DuplicateKeyException." );
        }
        assertEquals( Integer.valueOf( 42 ), dict.lookup( "Apple" ) );
    }

    /** Insert then remove then lookup returns null. */
    @Test
    public void testInteraction_insertThenRemoveThenLookup_returnsNull()
    {
        try
        {
            dict.insert( "Apple", 1 );
        }
        catch( DuplicateKeyException e )
        {
            fail( "Should not have thrown DuplicateKeyException." );
        }
        dict.remove( "Apple" );
        assertNull( dict.lookup( "Apple" ) );
    }

    /** Insert then update then lookup returns the updated value. */
    @Test
    public void testInteraction_insertThenUpdateThenLookup_returnsUpdatedValue()
    {
        try
        {
            dict.insert( "Apple", 1 );
        }
        catch( DuplicateKeyException e )
        {
            fail( "Should not have thrown DuplicateKeyException." );
        }
        dict.update( "Apple", 99 );
        assertEquals( Integer.valueOf( 99 ), dict.lookup( "Apple" ) );
    }

    /** Remove then insert on the same key succeeds and returns new value. */
    @Test
    public void testInteraction_removeThenInsert_newValueReturned()
    {
        populateDictionary();
        dict.remove( "Apple" );
        try
        {
            dict.insert( "Apple", 100 );
        }
        catch( DuplicateKeyException e )
        {
            fail( "Should not have thrown DuplicateKeyException after remove." );
        }
        assertEquals( Integer.valueOf( 100 ), dict.lookup( "Apple" ) );
    }

    /** Update on missing key does not interfere with existing keys. */
    @Test
    public void testInteraction_updateMissingKey_existingKeysUnaffected()
    {
        populateDictionary();
        dict.update( "Mango", 99 );
        assertEquals( Integer.valueOf( 1 ), dict.lookup( "Apple" ) );
        assertEquals( Integer.valueOf( 2 ), dict.lookup( "Banana" ) );
    }

    /** Multiple updates on the same key always reflect the latest value. */
    @Test
    public void testInteraction_multipleUpdates_latestValueRetained()
    {
        populateDictionary();
        dict.update( "Cherry", 30 );
        dict.update( "Cherry", 300 );
        dict.update( "Cherry", 3000 );
        assertEquals( Integer.valueOf( 3000 ), dict.lookup( "Cherry" ) );
    }

    /** Inserting after create() reset does not find previously inserted keys. */
    @Test
    public void testInteraction_createResetsState_previousKeysNotFound()
    {
        populateDictionary();
        dict.create( 10 );
        assertNull( "Keys from before create() should not be present.",
                dict.lookup( "Apple" ) );
        assertNull( dict.lookup( "Banana" ) );
    }

    /** Full workflow: insert, update, lookup, remove, re-insert. */
    @Test
    public void testInteraction_fullWorkflow_correctAtEachStep()
    {
        try
        {
            // insert
            dict.insert( "Apple", 1 );
            assertEquals( Integer.valueOf( 1 ), dict.lookup( "Apple" ) );

            // update
            dict.update( "Apple", 50 );
            assertEquals( Integer.valueOf( 50 ), dict.lookup( "Apple" ) );

            // remove
            assertEquals( Integer.valueOf( 50 ), dict.remove( "Apple" ) );
            assertNull( dict.lookup( "Apple" ) );

            // re-insert
            dict.insert( "Apple", 200 );
            assertEquals( Integer.valueOf( 200 ), dict.lookup( "Apple" ) );
        }
        catch( DuplicateKeyException e )
        {
            fail( "Full workflow should not throw DuplicateKeyException." );
        }
    }
}