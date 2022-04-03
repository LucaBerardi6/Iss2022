package it.unibo.radarSystem22.domain.concrete;

import java.util.Observable;

import it.unibo.radarSystem22.domain.Distance;
import it.unibo.radarSystem22.domain.interfaces.IDistance;
import it.unibo.radarSystem22.domain.interfaces.IObserver;
import it.unibo.radarSystem22.domain.utils.ColorsOut;


public class Observer implements IObserver {
	
	private IDistance distance;

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof Distance) {
			 distance = (Distance)arg;
				ColorsOut.out("************************* OSSERVATORE:**************************\n!!! update distance= "+distance.getVal(),ColorsOut.RED);
			 } else {
				 ColorsOut.out("!!! update distance=Unkonwn change!",ColorsOut.RED);
			 }


		
	}

	@Override
	public void update(IDistance distance) {
		 this.distance = distance;
		// System.out.println("************************* OSSERVATORE:**************************\n!!! update distance= "+distance.getVal());
	ColorsOut.out("************************* OSSERVATORE:**************************\n!!! update distance= "+distance.getVal());
		
	}
	
	public int getDistance() {
		return distance.getVal();
		
	}

}
