package it.unibo.radarSystem22.domain.interfaces;

 
import java.util.Observer;

@SuppressWarnings("deprecation")
public interface IDistanceMeasured extends IDistance{
	public void setVal( IDistance d );
	public IDistance getDistance(   );
	public void addObserver(Observer o); 
	public void deleteObserver( Observer obs );		 
}
