package fr.usmb.action_test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.TreeSet;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import action.DiscreteActionOnOffDependent;
import timer.OneShotTimer;
import timer.PeriodicTimer;
import timer.Timer;

class DiscreteActionOnOffDependentTest {
	
	private DiscreteActionOnOffDependent discreteOnOff;
	private DiscreteActionOnOffDependent discreteOnOff2;
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
		this.timer2 = new PeriodicTimer(2);
		
		this.timer3 = new OneShotTimer(3);
		this.timer4 = new OneShotTimer(2);
		
		
		this.discreteOnOff = new DiscreteActionOnOffDependent(object, "toUpperCase", timer, "toLowerCase", timer2);
		
		this.discreteOnOff2 = new DiscreteActionOnOffDependent(object, "toUpperCase", timer3, "toLowerCase", timer4);
	}

	@AfterEach
	void tearDown() throws Exception {
		
	}

	@Test
	void hasNext() {

		assertTrue(discreteOnOff.hasNext());
		discreteOnOff.next();
		assertTrue(discreteOnOff.hasNext());
		discreteOnOff.next();
		assertTrue(discreteOnOff.hasNext());
		
		assertTrue(discreteOnOff2.hasNext());
		discreteOnOff2.next();
		assertTrue(discreteOnOff2.hasNext());
		discreteOnOff2.next();
		assertFalse(discreteOnOff2.hasNext());
	}
	
	@Test
	void compareto() {
		discreteOnOff.next();
		discreteOnOff2.next();
		assertEquals(0,discreteOnOff.compareTo(discreteOnOff2));
		discreteOnOff.spendTime(1);
		assertEquals(-1,discreteOnOff.compareTo(discreteOnOff2));
	}
	
	@Test
	void getter() {
		discreteOnOff.next();
		discreteOnOff2.next();
		
		assertEquals(3,discreteOnOff2.getCurrentLapsTime());
		try {
			assertEquals(object.getClass().getDeclaredMethod("toUpperCase", new Class<?>[0]),discreteOnOff2.getMethod());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		discreteOnOff2.next();
		assertEquals(2,discreteOnOff2.getCurrentLapsTime());
		try {
			assertEquals(object.getClass().getDeclaredMethod("toLowerCase", new Class<?>[0]),discreteOnOff2.getMethod());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		discreteOnOff2.next();
		
		assertEquals(discreteOnOff.getObject(),object);
		
		
	}
	
	@Test
	void TreeSet() {
		TreeSet<Integer> tree = new TreeSet<>();
		TreeSet<Integer> tree2 = new TreeSet<>();
		
		tree.add(1); tree.add(5);
		tree2.add(1); tree2.add(5);
		
		DiscreteActionOnOffDependent discreteOnOffTree = new DiscreteActionOnOffDependent(object, "toUpperCase", tree , "toLowerCase", tree2);
		DiscreteActionOnOffDependent discreteOnOffTree2 = new DiscreteActionOnOffDependent(object, "toUpperCase", tree , "toLowerCase", tree2);
		
		discreteOnOffTree.next();
		discreteOnOffTree2.next();
		assertEquals(0,discreteOnOffTree.compareTo(discreteOnOffTree2));
		discreteOnOffTree.spendTime(1);
		assertEquals(-1,discreteOnOffTree.compareTo(discreteOnOffTree2));
		
	}

}
