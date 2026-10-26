package implementation;

import counterADT.CounterADT;
import exceptions.InvalidCounterException;

public class Counter implements CounterADT{
	
	// Attributes
	private int counter;
	
	@Override
	public void createCounter(int count) throws InvalidCounterException {
		if (count < 0) throw new InvalidCounterException();
		this.counter = count;
	}

	@Override
	public void setCounter(int count) throws InvalidCounterException {
		if (count < 0) throw new InvalidCounterException();
		this.counter = count;
	}

	@Override
	public void incCounter() {
		this.counter++;
	}

	@Override
	public void decCounter() throws InvalidCounterException {
		if (this.counter == 0) throw new InvalidCounterException();
		this.counter--;
	}

	@Override
	public int getCounter() {
		return this.counter;
	}

	@Override
	public boolean isZero() {
		if (this.counter == 0) return true;
		return false;
	}

	public String toString() {
		return "Counter value is: " + this.counter;
	}
}
