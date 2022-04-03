package it.unibo.radarSystem22.domain.broker;

import java.util.ArrayList;
import java.util.List;

import it.unibo.radarSystem22.domain.interfaces.IDistance;
import it.unibo.radarSystem22.domain.interfaces.IObserver;

public class Broker {

	private List<IObserver> observers=new ArrayList<IObserver>();;
	
	
	
	public void publish(IDistance distance) {
		
		for(IObserver observer : observers) {
			
			observer.update(distance);
		}
	}
	
	public void subscribe(IObserver observer) {
		
		 observers.add(observer);
	}
	
	public void unSubscribe(IObserver observer) {
		
		 observers.remove(observer);
	}
	
	
}
