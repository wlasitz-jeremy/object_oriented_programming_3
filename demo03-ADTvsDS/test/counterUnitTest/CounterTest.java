package counterUnitTest;

import exceptions.InvalidCounterException;
import implementation.Counter;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * JUnit 4 test suite for the Counter class.
 * Tests all methods defined in the CounterADT interface.
 *
 * <p>A fresh {@link Counter} instance is created before each test via
 * {@link #setUp()}. Unless a test explicitly calls
 * {@link Counter#createCounter(int)}, the counter starts uninitialised.</p>
 */
public class CounterTest
{
    // -----------------------------------------------------------------------
    // Fields & Setup
    // -----------------------------------------------------------------------

    /** The Counter instance under test – re-created fresh before every test. */
    private Counter counter;

    /**
     * Creates a fresh {@link Counter} instance before each test method.
     * @throws InvalidCounterException 
     */
    @Before
    public void setUp() throws InvalidCounterException
    {
        counter = new Counter();
    }


    // -----------------------------------------------------------------------
    // createCounter(int)
    // -----------------------------------------------------------------------

    /** Creating with zero initialises the counter to 0. */
    @Test
    public void testCreateCounter_zero_counterIsZero()
    {
        try
        {
            counter.createCounter( 0 );
        }
        catch( InvalidCounterException e )
        {
            fail( "Should not have thrown InvalidCounterException for value 0." );
        }
        assertEquals( 0, counter.getCounter() );
    }

    /** Creating with a positive value initialises the counter correctly. */
    @Test
    public void testCreateCounter_positiveValue_counterMatchesValue()
    {
        try
        {
            counter.createCounter( 5 );
        }
        catch( InvalidCounterException e )
        {
            fail( "Should not have thrown InvalidCounterException for value 5." );
        }
        assertEquals( 5, counter.getCounter() );
    }

    /** Creating with a large positive value initialises the counter correctly. */
    @Test
    public void testCreateCounter_largePositiveValue_counterMatchesValue()
    {
        try
        {
            counter.createCounter( 10000 );
        }
        catch( InvalidCounterException e )
        {
            fail( "Should not have thrown InvalidCounterException for value 10000." );
        }
        assertEquals( 10000, counter.getCounter() );
    }

    /** Creating with a negative value throws InvalidCounterException. */
    @Test( expected = InvalidCounterException.class )
    public void testCreateCounter_negativeValue_throwsInvalidCounterException()
            throws InvalidCounterException
    {
        counter.createCounter( -1 );
    }

    /** Creating with a large negative value throws InvalidCounterException. */
    @Test( expected = InvalidCounterException.class )
    public void testCreateCounter_largeNegativeValue_throwsInvalidCounterException()
            throws InvalidCounterException
    {
        counter.createCounter( -9999 );
    }

    /** Calling createCounter a second time resets the counter to the new value. */
    @Test
    public void testCreateCounter_calledTwice_resetsToNewValue()
    {
        try
        {
            counter.createCounter( 5 );
            counter.createCounter( 20 );
        }
        catch( InvalidCounterException e )
        {
            fail( "Should not have thrown InvalidCounterException." );
        }
        assertEquals( 20, counter.getCounter() );
    }


    // -----------------------------------------------------------------------
    // setCounter(int)
    // -----------------------------------------------------------------------

    /** Setting the counter to zero is permitted. */
    @Test
    public void testSetCounter_zero_counterIsZero()
    {
        try
        {
            counter.createCounter( 5 );
            counter.setCounter( 0 );
        }
        catch( InvalidCounterException e )
        {
            fail( "Should not have thrown InvalidCounterException for value 0." );
        }
        assertEquals( 0, counter.getCounter() );
    }

    /** Setting the counter to a positive value updates it correctly. */
    @Test
    public void testSetCounter_positiveValue_counterMatchesValue()
    {
        try
        {
            counter.createCounter( 0 );
            counter.setCounter( 42 );
        }
        catch( InvalidCounterException e )
        {
            fail( "Should not have thrown InvalidCounterException for value 42." );
        }
        assertEquals( 42, counter.getCounter() );
    }

    /** Setting the counter to a large positive value updates it correctly. */
    @Test
    public void testSetCounter_largePositiveValue_counterMatchesValue()
    {
        try
        {
            counter.createCounter( 0 );
            counter.setCounter( 99999 );
        }
        catch( InvalidCounterException e )
        {
            fail( "Should not have thrown InvalidCounterException for value 99999." );
        }
        assertEquals( 99999, counter.getCounter() );
    }

    /** Setting the counter to a negative value throws InvalidCounterException. */
    @Test( expected = InvalidCounterException.class )
    public void testSetCounter_negativeValue_throwsInvalidCounterException()
            throws InvalidCounterException
    {
        counter.createCounter( 5 );
        counter.setCounter( -1 );
    }

    /** Setting the counter to a large negative value throws InvalidCounterException. */
    @Test( expected = InvalidCounterException.class )
    public void testSetCounter_largeNegativeValue_throwsInvalidCounterException()
            throws InvalidCounterException
    {
        counter.createCounter( 5 );
        counter.setCounter( -100 );
    }

    /** Existing value is unchanged after an invalid setCounter call. */
    @Test
    public void testSetCounter_negativeValue_existingValueUnchanged()
    {
        try
        {
            counter.createCounter( 7 );
        }
        catch( InvalidCounterException e )
        {
            fail( "createCounter should not throw." );
        }
        try
        {
            counter.setCounter( -5 );
            fail( "Should have thrown InvalidCounterException." );
        }
        catch( InvalidCounterException e )
        {
            // expected
        }
        assertEquals( "Counter value should be unchanged after invalid setCounter.",
                7, counter.getCounter() );
    }

    /** Calling setCounter multiple times always reflects the last value. */
    @Test
    public void testSetCounter_calledMultipleTimes_retainsLastValue()
    {
        try
        {
            counter.createCounter( 0 );
            counter.setCounter( 10 );
            counter.setCounter( 20 );
            counter.setCounter( 30 );
        }
        catch( InvalidCounterException e )
        {
            fail( "Should not have thrown InvalidCounterException." );
        }
        assertEquals( 30, counter.getCounter() );
    }


    // -----------------------------------------------------------------------
    // incCounter()
    // -----------------------------------------------------------------------

    /** Incrementing from 0 results in 1. */
    @Test
    public void testIncCounter_fromZero_counterBecomesOne()
    {
        try
        {
            counter.createCounter( 0 );
        }
        catch( InvalidCounterException e )
        {
            fail( "createCounter should not throw." );
        }
        counter.incCounter();
        assertEquals( 1, counter.getCounter() );
    }

    /** Incrementing from a positive value increases it by 1. */
    @Test
    public void testIncCounter_fromPositiveValue_incrementsByOne()
    {
        try
        {
            counter.createCounter( 5 );
        }
        catch( InvalidCounterException e )
        {
            fail( "createCounter should not throw." );
        }
        counter.incCounter();
        assertEquals( 6, counter.getCounter() );
    }

    /** Incrementing multiple times increases the counter by the correct total. */
    @Test
    public void testIncCounter_calledMultipleTimes_incrementsCorrectly()
    {
        try
        {
            counter.createCounter( 0 );
        }
        catch( InvalidCounterException e )
        {
            fail( "createCounter should not throw." );
        }
        counter.incCounter();
        counter.incCounter();
        counter.incCounter();
        assertEquals( 3, counter.getCounter() );
    }

    /** Incrementing does not affect isZero when counter was 0 before. */
    @Test
    public void testIncCounter_fromZero_isZeroReturnsFalse()
    {
        try
        {
            counter.createCounter( 0 );
        }
        catch( InvalidCounterException e )
        {
            fail( "createCounter should not throw." );
        }
        counter.incCounter();
        assertFalse( counter.isZero() );
    }


    // -----------------------------------------------------------------------
    // decCounter()
    // -----------------------------------------------------------------------

    /** Decrementing from 1 results in 0. */
    @Test
    public void testDecCounter_fromOne_counterBecomesZero()
    {
        try
        {
            counter.createCounter( 1 );
            counter.decCounter();
        }
        catch( InvalidCounterException e )
        {
            fail( "Should not have thrown InvalidCounterException." );
        }
        assertEquals( 0, counter.getCounter() );
    }

    /** Decrementing from a positive value decreases it by 1. */
    @Test
    public void testDecCounter_fromPositiveValue_decrementsByOne()
    {
        try
        {
            counter.createCounter( 5 );
            counter.decCounter();
        }
        catch( InvalidCounterException e )
        {
            fail( "Should not have thrown InvalidCounterException." );
        }
        assertEquals( 4, counter.getCounter() );
    }

    /** Decrementing multiple times decreases the counter by the correct total. */
    @Test
    public void testDecCounter_calledMultipleTimes_decrementsCorrectly()
    {
        try
        {
            counter.createCounter( 10 );
            counter.decCounter();
            counter.decCounter();
            counter.decCounter();
        }
        catch( InvalidCounterException e )
        {
            fail( "Should not have thrown InvalidCounterException." );
        }
        assertEquals( 7, counter.getCounter() );
    }

    /** Decrementing when counter is 0 throws InvalidCounterException. */
    @Test( expected = InvalidCounterException.class )
    public void testDecCounter_fromZero_throwsInvalidCounterException()
            throws InvalidCounterException
    {
        counter.createCounter( 0 );
        counter.decCounter();
    }

    /** Counter value is unchanged after an invalid decrement attempt. */
    @Test
    public void testDecCounter_fromZero_counterRemainsZero()
    {
        try
        {
            counter.createCounter( 0 );
        }
        catch( InvalidCounterException e )
        {
            fail( "createCounter should not throw." );
        }
        try
        {
            counter.decCounter();
            fail( "Should have thrown InvalidCounterException." );
        }
        catch( InvalidCounterException e )
        {
            // expected
        }
        assertEquals( "Counter should remain 0 after invalid decrement.",
                0, counter.getCounter() );
    }

    /** Decrementing to zero makes isZero return true. */
    @Test
    public void testDecCounter_toZero_isZeroReturnsTrue()
    {
        try
        {
            counter.createCounter( 1 );
            counter.decCounter();
        }
        catch( InvalidCounterException e )
        {
            fail( "Should not have thrown InvalidCounterException." );
        }
        assertTrue( counter.isZero() );
    }


    // -----------------------------------------------------------------------
    // getCounter()
    // -----------------------------------------------------------------------

    /** getCounter returns 0 after createCounter(0). */
    @Test
    public void testGetCounter_afterCreateWithZero_returnsZero()
    {
        try
        {
            counter.createCounter( 0 );
        }
        catch( InvalidCounterException e )
        {
            fail( "createCounter should not throw." );
        }
        assertEquals( 0, counter.getCounter() );
    }

    /** getCounter returns the value set by createCounter. */
    @Test
    public void testGetCounter_afterCreate_returnsInitialValue()
    {
        try
        {
            counter.createCounter( 15 );
        }
        catch( InvalidCounterException e )
        {
            fail( "createCounter should not throw." );
        }
        assertEquals( 15, counter.getCounter() );
    }

    /** getCounter returns the value set by setCounter. */
    @Test
    public void testGetCounter_afterSet_returnsSetValue()
    {
        try
        {
            counter.createCounter( 0 );
            counter.setCounter( 25 );
        }
        catch( InvalidCounterException e )
        {
            fail( "Should not have thrown InvalidCounterException." );
        }
        assertEquals( 25, counter.getCounter() );
    }

    /** getCounter called repeatedly returns the same value each time. */
    @Test
    public void testGetCounter_calledRepeatedly_returnsSameValue()
    {
        try
        {
            counter.createCounter( 7 );
        }
        catch( InvalidCounterException e )
        {
            fail( "createCounter should not throw." );
        }
        assertEquals( 7, counter.getCounter() );
        assertEquals( 7, counter.getCounter() );
        assertEquals( 7, counter.getCounter() );
    }

    /** getCounter does not modify the counter value. */
    @Test
    public void testGetCounter_doesNotModifyCounter()
    {
        try
        {
            counter.createCounter( 8 );
        }
        catch( InvalidCounterException e )
        {
            fail( "createCounter should not throw." );
        }
        counter.getCounter();
        assertEquals( 8, counter.getCounter() );
    }


    // -----------------------------------------------------------------------
    // isZero()
    // -----------------------------------------------------------------------

    /** isZero returns true when counter is initialised to 0. */
    @Test
    public void testIsZero_counterIsZero_returnsTrue()
    {
        try
        {
            counter.createCounter( 0 );
        }
        catch( InvalidCounterException e )
        {
            fail( "createCounter should not throw." );
        }
        assertTrue( counter.isZero() );
    }

    /** isZero returns false when counter is initialised to a positive value. */
    @Test
    public void testIsZero_counterIsPositive_returnsFalse()
    {
        try
        {
            counter.createCounter( 3 );
        }
        catch( InvalidCounterException e )
        {
            fail( "createCounter should not throw." );
        }
        assertFalse( counter.isZero() );
    }

    /** isZero returns true after setCounter(0). */
    @Test
    public void testIsZero_afterSetToZero_returnsTrue()
    {
        try
        {
            counter.createCounter( 5 );
            counter.setCounter( 0 );
        }
        catch( InvalidCounterException e )
        {
            fail( "Should not have thrown InvalidCounterException." );
        }
        assertTrue( counter.isZero() );
    }

    /** isZero returns false after incrementing from zero. */
    @Test
    public void testIsZero_afterIncrement_returnsFalse()
    {
        try
        {
            counter.createCounter( 0 );
        }
        catch( InvalidCounterException e )
        {
            fail( "createCounter should not throw." );
        }
        counter.incCounter();
        assertFalse( counter.isZero() );
    }

    /** isZero returns true after decrementing to zero. */
    @Test
    public void testIsZero_afterDecrementToZero_returnsTrue()
    {
        try
        {
            counter.createCounter( 1 );
            counter.decCounter();
        }
        catch( InvalidCounterException e )
        {
            fail( "Should not have thrown InvalidCounterException." );
        }
        assertTrue( counter.isZero() );
    }

    /** isZero does not modify the counter. */
    @Test
    public void testIsZero_doesNotModifyCounter()
    {
        try
        {
            counter.createCounter( 0 );
        }
        catch( InvalidCounterException e )
        {
            fail( "createCounter should not throw." );
        }
        counter.isZero();
        assertEquals( 0, counter.getCounter() );
    }


    // -----------------------------------------------------------------------
    // toString()
    // -----------------------------------------------------------------------

    /** toString returns a non-null string. */
    @Test
    public void testToString_returnsNonNull()
    {
        try
        {
            counter.createCounter( 0 );
        }
        catch( InvalidCounterException e )
        {
            fail( "createCounter should not throw." );
        }
        assertNotNull( counter.toString() );
    }

    /** toString returns a non-empty string. */
    @Test
    public void testToString_returnsNonEmptyString()
    {
        try
        {
            counter.createCounter( 5 );
        }
        catch( InvalidCounterException e )
        {
            fail( "createCounter should not throw." );
        }
        assertFalse( counter.toString().isEmpty() );
    }

    /** toString contains the current counter value. */
    @Test
    public void testToString_containsCurrentValue()
    {
        try
        {
            counter.createCounter( 42 );
        }
        catch( InvalidCounterException e )
        {
            fail( "createCounter should not throw." );
        }
        assertTrue( "toString() should contain the counter value '42'.",
                counter.toString().contains( "42" ) );
    }

    /** toString reflects the updated value after an increment. */
    @Test
    public void testToString_afterIncrement_reflectsNewValue()
    {
        try
        {
            counter.createCounter( 9 );
        }
        catch( InvalidCounterException e )
        {
            fail( "createCounter should not throw." );
        }
        counter.incCounter();
        assertTrue( "toString() should contain '10' after increment.",
                counter.toString().contains( "10" ) );
    }

    /** toString reflects the updated value after a setCounter. */
    @Test
    public void testToString_afterSetCounter_reflectsNewValue()
    {
        try
        {
            counter.createCounter( 0 );
            counter.setCounter( 77 );
        }
        catch( InvalidCounterException e )
        {
            fail( "Should not have thrown InvalidCounterException." );
        }
        assertTrue( "toString() should contain '77' after setCounter.",
                counter.toString().contains( "77" ) );
    }


    // -----------------------------------------------------------------------
    // Interaction Tests
    // -----------------------------------------------------------------------

    /** Inc then dec returns counter to its original value. */
    @Test
    public void testInteraction_incThenDec_returnsToOriginalValue()
    {
        try
        {
            counter.createCounter( 5 );
            counter.incCounter();
            counter.decCounter();
        }
        catch( InvalidCounterException e )
        {
            fail( "Should not have thrown InvalidCounterException." );
        }
        assertEquals( 5, counter.getCounter() );
    }

    /** Dec then inc returns counter to its original value. */
    @Test
    public void testInteraction_decThenInc_returnsToOriginalValue()
    {
        try
        {
            counter.createCounter( 5 );
            counter.decCounter();
            counter.incCounter();
        }
        catch( InvalidCounterException e )
        {
            fail( "Should not have thrown InvalidCounterException." );
        }
        assertEquals( 5, counter.getCounter() );
    }

    /** setCounter then incCounter produces the correct value. */
    @Test
    public void testInteraction_setThenInc_correctValue()
    {
        try
        {
            counter.createCounter( 0 );
            counter.setCounter( 10 );
            counter.incCounter();
        }
        catch( InvalidCounterException e )
        {
            fail( "Should not have thrown InvalidCounterException." );
        }
        assertEquals( 11, counter.getCounter() );
    }

    /** setCounter then decCounter produces the correct value. */
    @Test
    public void testInteraction_setThenDec_correctValue()
    {
        try
        {
            counter.createCounter( 0 );
            counter.setCounter( 10 );
            counter.decCounter();
        }
        catch( InvalidCounterException e )
        {
            fail( "Should not have thrown InvalidCounterException." );
        }
        assertEquals( 9, counter.getCounter() );
    }

    /** createCounter resets all state from previous operations. */
    @Test
    public void testInteraction_createResetsAllState()
    {
        try
        {
            counter.createCounter( 50 );
            counter.incCounter();
            counter.incCounter();
            counter.createCounter( 0 );
        }
        catch( InvalidCounterException e )
        {
            fail( "Should not have thrown InvalidCounterException." );
        }
        assertEquals( 0, counter.getCounter() );
        assertTrue( counter.isZero() );
    }

    /** Full workflow: create, set, inc, dec, getCounter, isZero. */
    @Test
    public void testInteraction_fullWorkflow_correctAtEachStep()
    {
        try
        {
            counter.createCounter( 0 );
            assertEquals( 0, counter.getCounter() );
            assertTrue( counter.isZero() );

            counter.setCounter( 5 );
            assertEquals( 5, counter.getCounter() );
            assertFalse( counter.isZero() );

            counter.incCounter();
            assertEquals( 6, counter.getCounter() );

            counter.decCounter();
            counter.decCounter();
            assertEquals( 4, counter.getCounter() );

            counter.setCounter( 0 );
            assertTrue( counter.isZero() );
        }
        catch( InvalidCounterException e )
        {
            fail( "Full workflow should not throw InvalidCounterException." );
        }
    }
}