package it.unibo.radarSystem22.domain;

import java.util.Observable;
import java.util.Observer;

import it.unibo.radarSystem22.domain.interfaces.IDistance;
import it.unibo.radarSystem22.domain.interfaces.IDistanceMeasured;
import it.unibo.radarSystem22.domain.utils.ColorsOut;
 

@SuppressWarnings("deprecation")
public class DistanceMeasured extends Observable implements IDistanceMeasured{
private IDistance distance;


	@Override
	public void setVal( IDistance d ) {
		distance= d;
		notify (distance);
		
 		ColorsOut.out("******* DistanceMeasured ******* \n val= "+d.toString()+" observers: "+ countObservers()+"\n*************************", ColorsOut.YELLOW);
			
	}
	
	private void notify (IDistance iDistance) {
		 //== METODO  NOTIFICHE AGLI OBSERVER
		 setChanged();
		 notifyObservers(iDistance);
		 }
	
	
	
	@Override
	public IDistance getDistance(  ) {
		return distance;
	}	
	@Override
	public int getVal() { return distance.getVal(); }
	
	@Override
	public String toString() {
		return ""+ getVal();
	}
  	
	@Override
	public void addObserver(Observer obs) {

		super.addObserver(obs);
		ColorsOut.out("******* DistanceMeasured ******* \n adding a new Observer= "+obs+" observers: "+ countObservers()+"\n*************************", ColorsOut.YELLOW);
		}
	
	@Override
	public void deleteObserver(Observer obs) {

		super.deleteObserver(obs);
		ColorsOut.out("******* DistanceMeasured ******* \n deleting a new Observer= "+obs+" observers: "+ countObservers()+"\n*************************", ColorsOut.YELLOW);
		}
 
}
