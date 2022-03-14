package it.unibo.radarSystem22.domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.unibo.radarSystem22.domain.interfaces.ISonar;
import it.unibo.radarSystem22.domain.mock.SonarMock;

public class TestSonar {


	@Before
	public void up() {
		System.out.println("up test Sonar");
	}
	
	@After
	public void down() {
		System.out.println("down test Sonar");		
	}	
	
	@Test 
	public void testSonarMockOnOff() {
		
		System.out.println("test Sonar Mock on");
		ISonar sonar= new SonarMock();
		
		assertTrue( ! sonar.isActive() );
		
		sonar.activate();
	
		assertTrue( sonar.isActive() );
		
		sonar.deactivate();
		
		assertTrue( ! sonar.isActive() );
		
 
	
	}	
	
	@Test 
	public void testSonarMockDistance() throws InterruptedException {
		
		System.out.println("test Sonar Mock Distance");
		ISonar sonar= new SonarMock();
	
		
	
		assertTrue( sonar.getDistance().getVal()==90 );
		
		
		sonar.activate();
		//
		Thread.sleep(250);
		assertTrue( sonar.getDistance().getVal()==89 );
		//
		Thread.sleep(1000);
		assertTrue( sonar.getDistance().getVal()==85 );
		//
		Thread.sleep(1000);
		assertTrue( sonar.getDistance().getVal()==81 );
		//
		sonar.deactivate();
		assertTrue( sonar.getDistance().getVal()==81 );
		assertTrue( ! sonar.isActive() );
		
 
	
	}	
	
	/*@Test 
	public void testSonarMockDistanceError() throws InterruptedException {
		
		System.out.println("test Sonar Mock error");
		ISonar sonar= new SonarMock();
	
		
	
		assertTrue( sonar.getDistance().getVal()==90 );
		
		
		sonar.activate();
		//
		Thread.sleep(250);
		assertTrue( sonar.getDistance().getVal()==90 );
		//
		Thread.sleep(1000);
		assertTrue( sonar.getDistance().getVal()==86 );
		//
		Thread.sleep(1000);
		assertTrue( sonar.getDistance().getVal()==80 );
		//
		sonar.deactivate();
		assertTrue( sonar.getDistance().getVal()==81 );
		assertTrue( ! sonar.isActive() );
		
 
	
	}	*/
	
	
	
}
