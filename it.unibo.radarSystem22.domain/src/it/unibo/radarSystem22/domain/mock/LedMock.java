package it.unibo.radarSystem22.domain.mock;

import it.unibo.radarSystem22.domain.interfaces.ILed;

public class LedMock implements ILed {


	private final boolean on=true;
	private final boolean off=false;
	
	private boolean status;
	
	
	public LedMock() {
		
		super();
		this.status = off;
	}



	@Override
	public void turnOn() {
		
		status=on;
		
	}

	@Override
	public void turnOff() {
		
		status=off;

	}

	@Override
	public boolean getState() {
		
		 return status;
		
	}

}
