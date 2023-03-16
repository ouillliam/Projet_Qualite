package fr.usmb.timer_test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import timer.OneShotTimer;

class OneShotTimerTest {
	
	/*
	 * On initialise un objet OneShotTimer
	 */
	private OneShotTimer oneShotTimer1;
	
	/*
	 * On initialise la valeur du timer
	 */
	private int value;

	/*
	 * SetUp de la classe permettant l'intanciation des objets avant chaque tests
	 */
	@BeforeEach
	void setUp() throws Exception {
		
		this.value = 1;
		
		this.oneShotTimer1 = new OneShotTimer(value);
	}

	

	/*
	 * Test des fonctions next et hasNext
	 */
    @Test
    void hasNext() {

        // la première fois que l'on execute la fonction hasNext, cela va retourner true
        assertTrue(oneShotTimer1.hasNext());

        // la première fois que l'on execute la fonction next, cela va retourne la valeur du timer
        assertEquals(this.value, oneShotTimer1.next());

        // Puisque ce timer est un oneshottimer et que nous avons déjà appelé la fonction hasNext, cela devrait retourner false
        assertFalse(oneShotTimer1.hasNext());

        // Et donc la valeur suivante est désormais nulle
        assertNull(oneShotTimer1.next());

    }
}