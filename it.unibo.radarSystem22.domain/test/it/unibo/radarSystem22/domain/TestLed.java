package it.unibo.radarSystem22.domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.unibo.radarSystem22.domain.interfaces.ILed;
import it.unibo.radarSystem22.domain.mock.LedMock;



public class TestLed {
 
	@Before
	public void up() {
		System.out.println("up");
	}
	
	@After
	public void down() {
		System.out.println("down");		
	}	
	
	@Test 
	public void testLedMockOn() {
		
		System.out.println("test Led Mock on");
		ILed led= new LedMock();
		
		assertTrue( ! led.getState() );
		
 		led.turnOn();
		assertTrue(  led.getState() );
	
 
	
	}	
	
	@Test 
	public void testLedMockOff() {
		
		System.out.println("test Led Mock off");
		ILed led= new LedMock();
		
		assertTrue( ! led.getState() );
		
 		led.turnOn();
		assertTrue(  led.getState() );
	
 		led.turnOff();
		assertTrue(  ! led.getState() );	
	
	}	
	
	/*@Test 
	public void testLedMockError() {
		
		System.out.println("test Led Mock error");
		ILed led= new LedMock();
		
		assertTrue( ! led.getState() );
		
 		led.turnOn();
		assertTrue(  led.getState() );
	
 		led.turnOff();
		assertTrue(   led.getState() );	
	
	}*/	
	
	
	
 
}