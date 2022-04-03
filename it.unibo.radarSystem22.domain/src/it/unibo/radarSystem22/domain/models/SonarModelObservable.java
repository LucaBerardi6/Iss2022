package it.unibo.radarSystem22.domain.models;
 
import it.unibo.radarSystem22.domain.Distance;
import it.unibo.radarSystem22.domain.broker.Broker;
import it.unibo.radarSystem22.domain.concrete.SonarObservableConcrete;
import it.unibo.radarSystem22.domain.concrete.SonarConcrete;
import it.unibo.radarSystem22.domain.interfaces.*;
import it.unibo.radarSystem22.domain.mock.SonarMock;
import it.unibo.radarSystem22.domain.mock.SonarObservableMock;
import it.unibo.radarSystem22.domain.utils.ColorsOut;
import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;



public abstract class SonarModelObservable  implements ISonarObservable{  
protected  IDistance curVal = new Distance(90);	 
protected boolean stopped   = true;
protected Broker broker   = new Broker();
 	
	public static ISonarObservable create() {
		if( DomainSystemConfig.simulation )  return createSonarObservableMock();
		else  return createSonarObservableConcrete();		
	}

	public static ISonarObservable createSonarObservableMock() {
		ColorsOut.out("createSonarObservableMock", ColorsOut.BLUE);
		return new SonarObservableMock();
	}	
	public static ISonarObservable createSonarObservableConcrete() {
		ColorsOut.out("createSonarObservableConcrete", ColorsOut.BLUE);
		return new SonarObservableConcrete();
	}	
	
	protected SonarModelObservable() {//hidden costructor, to force setup
		ColorsOut.out("SonarModelObservable | calling (specialized) sonarSetUp ", ColorsOut.BLUE );
		sonarSetUp();   
	}
	
	protected void updateDistance( int d ) {
		curVal = new Distance( d );
		ColorsOut.out("SonarModelObservable | updateDistance "+ d, ColorsOut.BLUE);
		// pubblicazione al Broker:
		broker.publish(curVal);
	}	

	protected abstract void sonarSetUp() ;
 	protected abstract void sonarProduce() ;
 	
 	
 	@Override
	public void register( IObserver obs ) {
		//ColorsOut.out("SonarModel | isActive "+ (! stopped), ColorsOut.GREEN);
		broker.subscribe(obs);
 		
	}
 	
 	@Override
	public void unregister( IObserver obs ) {
		//ColorsOut.out("SonarModel | isActive "+ (! stopped), ColorsOut.GREEN);
		broker.unSubscribe(obs);
 		
	}
 	

	@Override
	public boolean isActive() {
		//ColorsOut.out("SonarModel | isActive "+ (! stopped), ColorsOut.GREEN);
		return ! stopped;
	}
	
	@Override
	public IDistance getDistance() {
		return curVal;
	}
	
	@Override
	public void activate() {
		curVal = new Distance( 90 );
 		ColorsOut.out("SonarModel | activate" );
		stopped = false;
		new Thread() {
			public void run() {
				while( ! stopped  ) {
					//Colors.out("SonarModel | call produce", Colors.GREEN);
					sonarProduce(  );
					//Utils.delay(RadarSystemConfig.sonarDelay);
				}
				ColorsOut.out("SonarModel | ENDS" );
		    }
		}.start();
	}
 	
	@Override
	public void deactivate() {
		ColorsOut.out("SonarModel | deactivate", ColorsOut.BgYellow );
		stopped = true;
	}

}

  

