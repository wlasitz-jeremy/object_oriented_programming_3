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
		// TODO Auto-generated method stub
		this.counter = count;
	}

	@Override
	public void incCounter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void decCounter() throws InvalidCounterException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getCounter() {
		return this.counter;
	}

	@Override
	public boolean isZero() {
		// TODO Auto-generated method stub
		return false;
	}

	
}
