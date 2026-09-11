package counterADT;

import exceptions.InvalidCounterException;

/**
 * Defines the public contract for a Counter ADT implementation.
 * <p>
 * A counter is a non-negative integer value that supports basic
 * operations such as incrementing, decrementing, setting, and querying
 * its current value.
 * </p>
 */
public interface CounterADT
{
	/**
	 * Initializes the counter with a specified value.
	 *
	 * @param count the initial counter value (optional, default is 0)
	 * @throws InvalidCounterException if a negative value is provided
	 */
	public void createCounter( int count ) throws InvalidCounterException;
	
	/**
	 * Sets the counter to a specified value.
	 *
	 * @param count the new counter value
	 * @throws InvalidCounterException if a negative value is provided
	 */
	public void setCounter( int count ) throws InvalidCounterException;
	
	/**
	 * Increments the counter value by 1.
	 */
	public void incCounter();
	
	/**
	 * Decrements the counter value by 1.
	 *
	 * @throws InvalidCounterException if the counter is already at 0
	 */
	public void decCounter() throws InvalidCounterException;
	
	/**
	 * Retrieves the current counter value.
	 *
	 * @return the current value of the counter
	 */
	public int getCounter();
	
	/**
	 * Returns a string representation of the counter value.
	 *
	 * @return the counter value as a String
	 */
	public String toString();
	
	/**
	 * Checks whether the counter is currently zero.
	 *
	 * @return true if the counter is zero, false otherwise
	 */
	public boolean isZero();
}