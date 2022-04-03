package it.unibo.radarSystem22.domain;
import static org.junit.Assert.assertTrue;
import org.junit.*;


import it.unibo.radarSystem22.domain.concrete.Observer;
import it.unibo.radarSystem22.domain.interfaces.IObserver;
import it.unibo.radarSystem22.domain.interfaces.ISonar;
import it.unibo.radarSystem22.domain.interfaces.ISonarObservable;
import it.unibo.radarSystem22.domain.mock.SonarObservableMock;
import it.unibo.radarSystem22.domain.utils.BasicUtils;
import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;
 

public class TestSonarObservable {
	
	static int DTESTING1=20;
	static int DTESTING2=50;
	
	@Before
	public void up() {
		System.out.println("up");
	}
	
	@After
	public void down() {
		System.out.println("down");		
	}	
	
	@Test 
	public void testSonarMock() {
		DomainSystemConfig.simulation = true;
		DomainSystemConfig.testing    = false;
		DomainSystemConfig.sonarDelay = 10;		//quite fast generation ...
		int delta = 1;
		
		
		//ISonar sonar = DeviceFactory.createSonar();
		
		SonarObservableMock sonar = new SonarObservableMock();
		Observer obs1 = new Observer();
		Observer obs2 = new Observer();
		
		sonar.register(obs1);
		sonar.register(obs2);
		
		sonar.setDistance(DTESTING1);
		assertTrue(obs1.getDistance()==DTESTING1);
		assertTrue(obs2.getDistance()==DTESTING1);
		
		BasicUtils.delay(1000);
		
		sonar.setDistance(DTESTING2);
		assertTrue(obs1.getDistance()==DTESTING2);
		assertTrue(obs2.getDistance()==DTESTING2);
		
		/*new SonarConsumerForTesting( sonar, delta ).start();  //consuma
		sonar.activate();		
 		while( sonar.isActive() ) { BasicUtils.delay(1000);} //to avoid premature exit*/
 	}
	
 
}
