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

/*
 * Cette classe va réaliser les tests sur la classe DiscreteAction
 * 
 * @author Benjamin
 * @version 1.0
 */
class DiscreteActionTest {
	
	/*
     * Première action discrete dependente
     */
	private DiscreteAction discrete;
	
	/*
     * Seconde action discrete dependente
     */
	private DiscreteAction discrete2;
	
	/*
     * Objet de l'action dicrete
     */
	private Object object;

	/*
	* SetUp permettant l'initialisation des objets avant chaque tests
	*/
	@BeforeEach
	void setUp() throws Exception {
		object = new String("test");
		Timer timer = new PeriodicTimer(2);
		Timer timer2 = new PeriodicTimer(2);
		
		discrete = new DiscreteAction(object, "toUpperCase", timer);
		discrete2 = new DiscreteAction(object, "toUpperCase", timer2);
	}

	/*
	* Test si l'action à une suite / si la consomation des temps est correct / si les getter fonctionnent
	*/
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
