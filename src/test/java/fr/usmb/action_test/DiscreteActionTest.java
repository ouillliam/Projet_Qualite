package fr.usmb.action_test;

import static org.junit.jupiter.api.Assertions.*;

import timer.PeriodicTimer;
import timer.Timer;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import action.DiscreteAction;

class DiscreteActionTest {
	private DiscreteAction discrete;
	private DiscreteAction discrete2;
	private Object object;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		object = new String("test");
		Timer timer = new PeriodicTimer(2);
		Timer timer2 = new PeriodicTimer(2);
		
		discrete = new DiscreteAction(object, "toUpperCase", timer);
		discrete2 = new DiscreteAction(object, "toUpperCase", timer2);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		
		assertTrue(discrete.hasNext());
		discrete.next();
		assertTrue(discrete.hasNext());
		
		discrete2.next();
		
		assertEquals(2,discrete2.getCurrentLapsTime());
		
		assertEquals(0,discrete.compareTo(discrete2));
		
		discrete.spendTime(1);
		
		assertTrue(discrete.hasNext());
		
		assertEquals(discrete.getObject(),object);
		
		assertEquals(-1,discrete.compareTo(discrete2));
	}

}
