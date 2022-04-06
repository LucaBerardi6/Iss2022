package it.unibo.radarSystem22.domain.mock;

import it.unibo.radarSystem22.domain.Distance;
import it.unibo.radarSystem22.domain.DistanceMeasured;
import it.unibo.radarSystem22.domain.interfaces.IDistance;
import it.unibo.radarSystem22.domain.interfaces.IDistanceMeasured;
import it.unibo.radarSystem22.domain.interfaces.IObserver;
import it.unibo.radarSystem22.domain.interfaces.ISonarObservable;
import it.unibo.radarSystem22.domain.models.SonarModel;
import it.unibo.radarSystem22.domain.utils.BasicUtils;
import it.unibo.radarSystem22.domain.utils.ColorsOut;
import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;

public class SonarObservableMock extends SonarModel implements ISonarObservable{
private int delta = 1;
private IDistanceMeasured distanceMesured = new DistanceMeasured();
private static int SIGNIFICATIVO=10;
private int precVal=0;

	@Override
	protected void sonarSetUp() {
		curVal = new Distance(90);	
		ColorsOut.out("SonarObservableMock | sonarSetUp curVal="+curVal);
	}
	
	@Override
	public IDistance getDistance() {
		return curVal;
	}	
	
	
	@Override
	protected void sonarProduce( ) {
		if( DomainSystemConfig.testing ) {	//produces always the same value
			updateDistance( DomainSystemConfig.testingDistance );			      
		}else {
			int v = curVal.getVal() - delta;
			updateDistance( v );			    
			stopped = ( v <= 0 );
		}
		
		setVal(curVal);
		BasicUtils.delay(DomainSystemConfig.sonarDelay);  //avoid fast generation
 	}
	
	private void setVal(IDistance curVal) {
		
		if(Math.abs(curVal.getVal()-precVal)>SIGNIFICATIVO) distanceMesured.setVal(curVal);
		precVal=curVal.getVal();
			
	}

	@Override
	public void register(IObserver obs) {
		distanceMesured.addObserver(obs);
		ColorsOut.out("******* SonarObservableMock ******* \n register a new Observer= "+obs+" observers: "+"\n*************************", ColorsOut.BLUE);
		
		
	}

	@Override
	public void unregister(IObserver obs) {
		distanceMesured.deleteObserver(obs);
		ColorsOut.out("******* SonarObservableMock ******* \n unregister a new Observer= "+obs+" observers: "+"\n*************************", ColorsOut.BLUE);
		
	}
}
