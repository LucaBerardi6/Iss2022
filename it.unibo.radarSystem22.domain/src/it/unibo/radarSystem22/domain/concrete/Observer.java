package it.unibo.radarSystem22.domain.concrete;

import java.util.Observable;

import it.unibo.radarSystem22.domain.Distance;
import it.unibo.radarSystem22.domain.interfaces.IDistance;
import it.unibo.radarSystem22.domain.interfaces.IObserver;
import it.unibo.radarSystem22.domain.utils.ColorsOut;


public class Observer implements IObserver {
	
	private int distance;

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof IDistance) {
			update(arg.toString());
		
		} else {
				 ColorsOut.out("!!!Observer!!! update distance=Unkonwn change!");
			 }


		
	}

	@Override
	public void update(String sDistance) {
		 this.distance = Integer.parseInt(sDistance);
		// System.out.println("************************* OSSERVATORE:**************************\n!!! update distance= "+distance.getVal());
	ColorsOut.out("************** OBSERVER:**************\n!!! update distance= "+distance+"\n**************************************");
		
	}
	
	

}
