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
	
	static int DTESTING2=20;
	static int DTESTING1=50;
	
	@Before
	public void up() {
		System.out.println("up");
	}
	
	@After
	public void down() {
		System.out.println("down");		
	}	
	
	
	@Test 
	public void testSonarMockObservable() {
		DomainSystemConfig.simulation = true;
		DomainSystemConfig.testing    = true;
		DomainSystemConfig.sonarDelay = 1000;	
		DomainSystemConfig.testingDistance= 90;
		int delta = 1;
		
		ISonarObservable sonar = DeviceFactory.createSonarObservable();
		
		Observer obs1 = new Observer();
		sonar.register(obs1);
		
	
		new SonarConsumerForTestingObserver( sonar, delta ).start();  //consuma
		sonar.activate();		
 		//while( sonar.isActive() ) { BasicUtils.delay(1000);} //to avoid premature exit
		BasicUtils.delay(3000);
		DomainSystemConfig.testingDistance= DTESTING1;
		BasicUtils.delay(3000);
		DomainSystemConfig.testingDistance= DTESTING2;
		BasicUtils.delay(3000);
		DomainSystemConfig.testingDistance= 0;
 	}
	
 
}
