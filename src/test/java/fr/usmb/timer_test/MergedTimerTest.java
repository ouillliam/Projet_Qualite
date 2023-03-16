package fr.usmb.timer_test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import timer.MergedTimer;
import timer.OneShotTimer;

class MergedTimerTest {

    
	/*
	 * Test des fonctions next et hasNext
	 */
    @Test
    void hasNextTest() {

    	// On initialise les valeurs des différents oneShotTimer que l'on va créer 
    	int timerValue1 = 1;
    	int timerValue2 = 2;
    	int timerValue3 = 3;
    	
        // On initialise un premier timer (one shot)
        OneShotTimer oneShotTimer1 = new OneShotTimer(timerValue1);

        // On initialise un second timer (one shot)
        OneShotTimer oneShotTimer2 = new OneShotTimer(timerValue2);

        // D'abord nous vérifions bien qu'un objet MergedTimer ne peut pas contenir de paramètres nuls (timers nuls)
        assertThrows(NullPointerException.class, () -> new MergedTimer(null, null));
        assertThrows(NullPointerException.class, () -> new MergedTimer(oneShotTimer1, null));
        assertThrows(NullPointerException.class, () -> new MergedTimer(null, oneShotTimer2));

        // Nous vérifions alors qu'il n'y a pas de problèmes avec de bons paramètres
        assertDoesNotThrow(() -> new MergedTimer(oneShotTimer1, oneShotTimer2));

        // On initialise un timer combiné
        MergedTimer mergedTimer1 = new MergedTimer(oneShotTimer1, oneShotTimer2);

        // Comme c'est la première fois que l'on appele la fonction hasNext, cela renvoit True
        assertTrue(mergedTimer1.hasNext());

        // Nous vérifions si la prochaine valeur de notre timer combiné est bien égal à 3 car 1 + 2 = 3
        assertEquals(timerValue1 + timerValue2, mergedTimer1.next());

        // Puisque ce timer est composé de deux one shit timer, la fonction hasNext doit retourner false
        assertFalse(mergedTimer1.hasNext());

        // Si nous executons la fonction next, celle-ci devrait renvoyer null
        assertNull(mergedTimer1.next());
        
    }
}