package it.unibo.radarSystem22.domain;

import static org.junit.Assert.assertTrue;

import it.unibo.radarSystem22.domain.interfaces.IDistance;
import it.unibo.radarSystem22.domain.interfaces.ISonar;
import it.unibo.radarSystem22.domain.interfaces.ISonarObservable;
import it.unibo.radarSystem22.domain.utils.BasicUtils;
import it.unibo.radarSystem22.domain.utils.ColorsOut;
import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;


class SonarConsumerForTestingObserver extends Thread{
private ISonar sonar;
private int delta;
	public SonarConsumerForTestingObserver( ISonarObservable sonar, int delta) {
		this.sonar = sonar;
		this.delta = delta;
	}
	@Override
	public void run() {
		int v0 = sonar.getDistance().getVal();
		ColorsOut.out("SonarConsumerForTestingObserver | initial value v0=" + v0);
		while( v0 == 90 ) {
			BasicUtils.delay(DomainSystemConfig.sonarDelay);
			v0 = sonar.getDistance().getVal();
			ColorsOut.out("SonarConsumerForTestingObserver | initial value =" + v0);
		}
 		while( sonar.isActive() ) {
 			BasicUtils.delay(DomainSystemConfig.sonarDelay/2); //per non perdere dati simulati
 			IDistance d = sonar.getDistance();
			int v       = d.getVal();
			ColorsOut.out("SonarConsumerForTestingObserver | v=" + v);
			
		}
	}
}
