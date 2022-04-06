package it.unibo.radarSystem22.domain.concrete;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import it.unibo.radarSystem22.domain.Distance;
import it.unibo.radarSystem22.domain.DistanceMeasured;
import it.unibo.radarSystem22.domain.interfaces.IDistance;
import it.unibo.radarSystem22.domain.interfaces.IDistanceMeasured;
import it.unibo.radarSystem22.domain.interfaces.IObserver;
import it.unibo.radarSystem22.domain.interfaces.ISonarObservable;
import it.unibo.radarSystem22.domain.models.SonarModel;
import it.unibo.radarSystem22.domain.utils.ColorsOut;
import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;





public class SonarObservableConcrete extends SonarModel implements ISonarObservable{
	private  BufferedReader reader ;
	private Process p ;
	private IDistanceMeasured distanceMesured = new DistanceMeasured();
	private static int SIGNIFICATIVO=10;
	private int precVal=0;
	
	/*
	 * curVal è usata come valore della distanza corrente misurata
	 */
	@Override
	protected void sonarSetUp() {//called by SonarModel constructor
		curVal = new Distance(90);	
		distanceMesured.setVal(curVal);
	}

	
	@Override
	public void activate() {
		ColorsOut.out("SonarObservableConcrete | activate ");
 		if( p == null ) { 
 	 		try {
 				p       = Runtime.getRuntime().exec("sudo ./SonarAlone");
 		        reader  = new BufferedReader( new InputStreamReader(p.getInputStream()));
 		        ColorsOut.out("SonarObservableConcrete | sonarSetUp done");
 	       	}catch( Exception e) {
 	       		ColorsOut.outerr("SonarObservableConcrete | sonarSetUp ERROR " + e.getMessage() );
 	    	}
 		}
 		super.activate();
 	}
	
	@Override
	protected void sonarProduce( ) {
        try {
			String data = reader.readLine();
			if( data == null ) return;
			int v = Integer.parseInt(data);
			ColorsOut.out("SonarObservableConcrete | v=" + v );
			int lastSonarVal = curVal.getVal();
			if( lastSonarVal != v && v < DomainSystemConfig.sonarDistanceMax) {	
				//Eliminiamo dati del tipo 3430 //TODO: filtri in sottosistemi a stream
  	 			updateDistance( v );
  	 			
  	 			setVal(curVal);
			}
       }catch( Exception e) {
       		ColorsOut.outerr("SonarObservableConcrete |  " + e.getMessage() );
       }		
	}
	
	private void setVal(IDistance curVal) {
		
		if(Math.abs(curVal.getVal()-precVal)>SIGNIFICATIVO) distanceMesured.setVal(curVal);
		precVal=curVal.getVal();
	}
 
	@Override
	public void deactivate() {
		ColorsOut.out("SonarObservableConcrete | deactivate", ColorsOut.GREEN);
	    curVal = new Distance(90);
		if( p != null ) {
			p.destroy();  //Block the runtime process
			p=null;
		}
		super.deactivate();
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
