package fr.usmb.timer_test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import timer.OneShotTimer;
import timer.PeriodicTimer;
import timer.TimeBoundedTimer;

class TimeBoundedTimerTest {

	/**
	 * Test de la fonction hasNext() lorsque le timer a un comportement classique
	 */
	@Test
	void hasNextTest1() {
		PeriodicTimer periodicTimer = new PeriodicTimer(1);
		TimeBoundedTimer tbTimer = new TimeBoundedTimer(periodicTimer, 3);
		assertTrue(tbTimer.hasNext());
	}
	
	/**
	 * Test de la fonction hasNext() lorsque la dernière valeur du timer est à la limite de fin
	 */
	@Test
	void hasNextTest2() {
		PeriodicTimer periodic = new PeriodicTimer(1);
		TimeBoundedTimer tbTimer = new TimeBoundedTimer(periodic, 2, 3);
		tbTimer.next();
		assertFalse(tbTimer.hasNext());
	}
	
	/**
	 * Test de la fonction hasNext() lorsque la dernière valeur du timer est au-delà de la limite de fin
	 */
	@Test
	void hasNextTest3() {
		PeriodicTimer periodic = new PeriodicTimer(10);
		TimeBoundedTimer tbTimer = new TimeBoundedTimer(periodic, 5, 15);
		tbTimer.next();
		assertFalse(tbTimer.hasNext());
	}
	
	/**
	 * Test de la fonction hasNext() lorsque le timer s'arrête avant la valeur de départ
	 */
	@Test
	void hasNextTest4() {
		OneShotTimer oneshot = new OneShotTimer(1);
		TimeBoundedTimer tbTimer = new TimeBoundedTimer(oneshot, 2);
		assertFalse(tbTimer.hasNext());
	}
	
	/**
	 * Test de la fonction hasNext() lorsque la première valeur du timer est au-delà de la limite de fin
	 */
	@Test
	void hasNextTest5() {
		OneShotTimer oneshot = new OneShotTimer(4);
		TimeBoundedTimer tbTimer = new TimeBoundedTimer(oneshot, 2, 3);
		assertFalse(tbTimer.hasNext());
	}
	
	/**
	 * Test de la fonction next() lorsque le timer à un comportement classique
	 */
	@Test
	void nextTest1() {
		PeriodicTimer periodic = new PeriodicTimer(1);
		TimeBoundedTimer tbTimer = new TimeBoundedTimer(periodic, 3,6);
		assertEquals(tbTimer.next(), 3);
		assertEquals(tbTimer.next(), 1);
		assertEquals(tbTimer.next(), 1);
		assertNull(tbTimer.next());
	}
	
	/**
	 * Test de la fonction next() lorsque le time s'arrête avant la valeur de début
	 */
	@Test
	void nextTest2() {
		OneShotTimer oneshot = new OneShotTimer(1);
		TimeBoundedTimer tbTimer = new TimeBoundedTimer(oneshot, 2);
		assertNull(tbTimer.next());
	}
	
	/**
	 * Test de la fonction next() lorsque la première valeur du timer est au-delà de la valeur limite
	 */
	@Test
	void nextTest3() {
		OneShotTimer oneshot = new OneShotTimer(4);
		TimeBoundedTimer tbTimer = new TimeBoundedTimer(oneshot, 2, 3);
		assertNull(tbTimer.next());
	}
	
	/**
	 * Test si le constructeur retourne une erreur si le temps de départ est plus important que le temps de fin
	 */
	@Test
	void tbt9() {
		OneShotTimer oneshot = new OneShotTimer(4);
		assertThrows(Exception.class, () -> new TimeBoundedTimer(oneshot, 4, 3));		
	}


}
