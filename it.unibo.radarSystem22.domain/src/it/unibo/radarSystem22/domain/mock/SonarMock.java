package it.unibo.radarSystem22.domain.mock;

import it.unibo.radarSystem22.domain.Distance;
import it.unibo.radarSystem22.domain.interfaces.IDistance;
import it.unibo.radarSystem22.domain.interfaces.ISonar;

public class SonarMock implements ISonar {

	private boolean active;
	private int distance;
	
	public SonarMock() {
		this.active=false;
		this.distance=90;
	}
	
	@Override
	public void activate() {
		
		active=true;
		
		new Thread() {
			public void run() {
				while( active && distance>= 0 ) {
					
					distance--;
					
					try {
						Thread.sleep(250);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
						
				}
			
		    }
		}.start();
	}

	@Override
	public void deactivate() {
		
		active=false;

	}

	@Override
	public IDistance getDistance() {
		
		return new Distance(distance);
	}

	@Override
	public boolean isActive() {
		
		return active;
	}

}
