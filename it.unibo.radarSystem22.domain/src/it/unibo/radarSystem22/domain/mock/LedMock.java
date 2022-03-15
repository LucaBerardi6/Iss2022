package it.unibo.radarSystem22.domain.mock;

import it.unibo.radarSystem22.domain.interfaces.ILed;
import it.unibo.radarSystem22.domain.models.LedModel;
import it.unibo.radarSystem22.domain.utils.ColorsOut;

public class LedMock extends LedModel implements ILed {


	private final boolean on=true;
	private final boolean off=false;
	
	private boolean status;
	
	
	public LedMock() {
		
		super();
		this.status = off;
	}

	@Override
	  protected void ledActivate(boolean val) {    showState(); }

	  protected void showState(){
	    ColorsOut.outappl("LedMock state=" + getState(), ColorsOut.MAGENTA );
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
