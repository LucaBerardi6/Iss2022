package it.unibo.radarSystem22_4.appl.observer;

import java.util.Observable;

import it.unibo.radarSystem22.domain.Distance;
import it.unibo.radarSystem22.domain.interfaces.IDistance;
import it.unibo.radarSystem22.domain.interfaces.ISonar;
import it.unibo.radarSystem22.domain.utils.ColorsOut;
import it.unibo.radarSystem22_4.comm.ApplMessage;
import it.unibo.radarSystem22_4.comm.ProtocolType;
import it.unibo.radarSystem22_4.comm.interfaces.IApplMessage;
import it.unibo.radarSystem22_4.comm.proxy.ProxyAsClient;
import it.unibo.radarSystem22_4.comm.utils.CommUtils;


public class SonarObservable extends ProxyAsClient implements ISonar{
	public static    IApplMessage sonarActivate   ; 
	public static    IApplMessage sonarDeactivate ;  
	public static    IApplMessage getDistance  ; 
	public static    IApplMessage isActive     ;
	//
	// !!!!!! DA OBSERVER!!!!!!!!!!!!!:
	private DelegatedObservable obs;
	//
  	
 	public SonarObservable( String name, String host, String entry, ProtocolType protocol ) {
		super( name,  host,  entry, protocol );
		sonarActivate   = CommUtils.buildDispatch(name,"cmd", "activate",   "sonar");
		sonarDeactivate = CommUtils.buildDispatch(name,"cmd", "deactivate", "sonar");
		getDistance     = CommUtils.buildRequest(name, "ask", "getDistance","sonar");
		isActive        = CommUtils.buildRequest(name, "ask", "isActive",   "sonar");
		//
		//!!!!!! DA OBSERVER!!!!!!!!!!!!!:
		obs = new DelegatedObservable();
 	}
 	
 
	public DelegatedObservable getObservable() {return obs;}
	
 	@Override
	public void activate() {
	     if( protocol == ProtocolType.tcp || protocol == ProtocolType.udp ) {
 			sendCommandOnConnection(sonarActivate.toString());	
	     }
	   //ALTRI PROTOCOLLI ...	
	}

	@Override
	public void deactivate() {
	     if( protocol == ProtocolType.tcp  || protocol == ProtocolType.udp  ) {
 			sendCommandOnConnection(sonarDeactivate.toString());		
	    	close();
	     }
	}

	@Override
	public IDistance getDistance() {
		String answer = "0";
	    if( protocol == ProtocolType.tcp || protocol == ProtocolType.udp  ) {
	    	String reply = sendRequestOnConnection(getDistance.toString());
	    	ColorsOut.outappl(name+" |  getDistance reply=" + reply, ColorsOut.GREEN );
	    	answer = new ApplMessage(reply).msgContent();
	    }
	    
 		return new Distance( Integer.parseInt(answer) );
	}

	@Override
	public boolean isActive() {
		String answer = "";
	    if( protocol == ProtocolType.tcp || protocol == ProtocolType.udp  ) {
	    	String reply = sendRequestOnConnection( isActive.toString() );	    	
	    	ColorsOut.outappl(name+" |  isActive reply=" + reply, ColorsOut.GREEN );
	    	answer = new ApplMessage(reply).msgContent();
	    }
		return answer.equals( "true" );
	}
	
	
	public void notify (IDistance iDistance) {
		 //== METODO  NOTIFICHE AGLI OBSERVER
		 obs.setChanged();
		 obs.notifyObservers(iDistance);
		 }

 	
 }



