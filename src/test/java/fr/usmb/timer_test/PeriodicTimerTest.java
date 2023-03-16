package fr.usmb.timer_test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import timer.PeriodicTimer;
import timer.RandomTimer;
import timer.RandomTimer.randomDistribution;

class PeriodicTimerTest {

	
	/*
	 * Test de la fonction getPeriod()
	 */
	 @Test
	    void getPeriod() throws Exception {

		 	// On initialise un timer périodique et on essaye d'obtenir sa periode
	        PeriodicTimer periodicTimer1 = new PeriodicTimer(1);
	        assertEquals(1, periodicTimer1.getPeriod());

	        // On initialise un autre timer périodique et on essaye d'obtenir sa periode
	        PeriodicTimer periodicTimer2 = new PeriodicTimer(1, 1);
	        assertEquals(1, periodicTimer2.getPeriod());

	        // On initialise un timer aléatoire
	        RandomTimer randomTimer = new RandomTimer(randomDistribution.EXP, 1);

	        // On initialise un timer périodique avec comme paramètre un timer aléatoire et on essaye d'obtenir sa periode
	        PeriodicTimer periodicTimer3 = new PeriodicTimer(1, randomTimer);
	        assertEquals(1, periodicTimer3.getPeriod());

	        // On initialise un autre timer périodique avec comme paramètre un timer aléatoire et on essaye d'obtenir sa periode
	        PeriodicTimer periodicTimer4 = new PeriodicTimer(1, 1, randomTimer);
	        assertEquals(1, periodicTimer4.getPeriod());

	    }

	 	/*
		 * Test de la fonction next()
		 */
	    @Test
	    void next() {

	    	// Nous voulons récupérer la prochaine valeur de notre timer
	        int nextValue = 1;
	        PeriodicTimer periodicTimer1 = new PeriodicTimer(nextValue);

	        // Comme un timer périodique a toujours une prochaine valeur, nous pouvons faire le test 2 fois
	        assertEquals(nextValue, periodicTimer1.next());
	        assertEquals(nextValue, periodicTimer1.next());

	        // Nous répétons l'opération avec cette fois-ci deux valeurs en paramètres
	        int periodValue = 2;
	        PeriodicTimer periodicTimer2 = new PeriodicTimer(periodValue, nextValue);
	        assertEquals(nextValue, periodicTimer2.next());
	        assertEquals(periodValue, periodicTimer2.next());


	    }

	 

}
