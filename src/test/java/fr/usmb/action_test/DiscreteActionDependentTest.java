package fr.usmb.action_test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import action.DiscreteActionDependent;
import timer.OneShotTimer;
import timer.PeriodicTimer;
import timer.Timer;

/*
 * Cette classe va réaliser les tests sur la classe DiscreteActionDependent
 * 
 * @author Benjamin
 * @version 1.0
 */

class DiscreteActionDependentTest {

	/*
     * Première action discrete dependente
     */
	private DiscreteActionDependent discreteDependent;
	
	/*
     * Seconde action discrete dependente
     */
	private DiscreteActionDependent discreteDependent2;
	
	/*
     * Objet de l'action dicrete
     */
	private Object object;
	
	/*
     * Temps d'attente avant la première action
     */
	private Timer timer;
	
	/*
     * Temps d'attente avant la première action de la seconde action discrete
     */
	private Timer timer2;
	
	/*
     * Temps d'attente de la première dependence
     */
	private Timer timer3;
	
	/*
     * Temps d'attente de la seconde dependence
     */
	private Timer timer4;
	
	/*
	* SetUp permettant l'initialisation des objets avant chaque tests
	*/
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

	/*
	* Test si l'action à une suite
	*/
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
	
	/*
	* Compare deux actions
	*/
	@Test
	void compareto() {
		
		discreteDependent.next();
		discreteDependent2.next();
		
		discreteDependent.updateTimeLaps();
		discreteDependent2.updateTimeLaps();
		
		discreteDependent.next();
		discreteDependent2.next();
		
		discreteDependent.spendTime(1);
		discreteDependent2.spendTime(1);
		
		assertEquals(-1,discreteDependent.compareTo(discreteDependent2));
		discreteDependent.spendTime(1);
		assertEquals(0,discreteDependent.compareTo(discreteDependent2));
	}
	
	/*
	* Test les getter
	*/
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
		discreteDependent2.next();
		
		assertEquals(4,discreteDependent2.getCurrentLapsTime());
		
		try {
			assertEquals(object.getClass().getDeclaredMethod("toLowerCase", new Class<?>[0]),discreteDependent2.getMethod());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		discreteDependent2.next();
		
		assertEquals(discreteDependent.getObject(),object);
		
	}
	
	
	/*
	* Test la fonction updateTimeLaps()
	*/
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
