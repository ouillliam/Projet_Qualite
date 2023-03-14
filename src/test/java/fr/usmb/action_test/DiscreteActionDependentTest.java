package fr.usmb.action_test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import action.DiscreteActionDependent;
import timer.OneShotTimer;
import timer.PeriodicTimer;
import timer.Timer;

class DiscreteActionDependentTest {

	private DiscreteActionDependent discreteDependent;
	private DiscreteActionDependent discreteDependent2;
	private Object object;
	private Timer timer;
	private Timer timer2;
	private Timer timer3;
	private Timer timer4;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		this.object = new String("test");
		this.timer = new PeriodicTimer(3);
		this.timer2 = new OneShotTimer(3);
		
		this.timer3 = new PeriodicTimer(3);
		this.timer4 = new OneShotTimer(3);
		
		
		this.discreteDependent = new DiscreteActionDependent(object, "toUpperCase", timer);
		this.discreteDependent2 = new DiscreteActionDependent(object, "toUpperCase", timer2);
		
		discreteDependent.addDependence(object, "toLowerCase", timer3);
		discreteDependent2.addDependence(object, "toLowerCase", timer4);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void hasNext() {

		assertTrue(discreteDependent.hasNext());
		discreteDependent.next();
		assertTrue(discreteDependent.hasNext());
		discreteDependent.next();
		assertTrue(discreteDependent.hasNext());
		
		assertTrue(discreteDependent2.hasNext());
		discreteDependent2.next();
		assertTrue(discreteDependent2.hasNext());
		discreteDependent2.next();
		assertFalse(discreteDependent2.hasNext());
	}
	
	@Test
	void compareto() {
		
		discreteDependent.next();
		discreteDependent2.next();
		
		discreteDependent.updateTimeLaps();
		discreteDependent2.updateTimeLaps();
		
		discreteDependent.spendTime(1);
		discreteDependent2.spendTime(1);
		
		assertEquals(0,discreteDependent.compareTo(discreteDependent2));
		discreteDependent.spendTime(1);
		assertEquals(-1,discreteDependent.compareTo(discreteDependent2));
	}
	
	@Test
	void getter() {
		discreteDependent.next();
		
		try {
			assertEquals(object.getClass().getDeclaredMethod("toUpperCase", new Class<?>[0]),discreteDependent2.getMethod());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		discreteDependent2.next();
		discreteDependent2.updateTimeLaps();
		
		assertEquals(3,discreteDependent2.getCurrentLapsTime());
		
		try {
			assertEquals(object.getClass().getDeclaredMethod("toLowerCase", new Class<?>[0]),discreteDependent2.getMethod());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		discreteDependent2.next();
		
		assertEquals(discreteDependent.getObject(),object);
		
	}
	
	@Test
	void updateTimeLaps() {
		
		Object objectTest = new String("test");
		PeriodicTimer timerTest = new PeriodicTimer(3);
		
		DiscreteActionDependent discreteDependentTest = new DiscreteActionDependent(objectTest, "toUpperCase", timerTest);
		
		NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> {
			discreteDependentTest.updateTimeLaps();
        });
		
	}
	
	

}
