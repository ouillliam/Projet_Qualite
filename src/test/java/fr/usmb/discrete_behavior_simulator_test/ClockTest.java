package fr.usmb.discrete_behavior_simulator_test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import discreteBehaviorSimulator.Clock;


class ClockTest {

	/*
	 * L'instance de clock (singleton)
	 */
	private Clock instance;
	
	/*
	 * Variables représentant des temps
	 */
	private int i;
	private int j;
	
	/*
	 * SetUp de la classe permettant l'intanciation des objets avant chaque tests
	 */
	@BeforeEach
	void setUp() throws Exception {
		this.instance = Clock.getInstance();
		this.i = 1;
	    this.j = 2;
	}

	
	/*
	 * Nous testons si la variable instance est bien une instance du singleton Clock
	 */
	@Test
    public void getInstanceTest() {
        assertTrue(this.instance instanceof Clock);
    }

	
	/*
	 * Test de la fonction increase
	 */
    @Test
    public void increaseTest() {
        
        this.instance.setNextJump(this.i);
        assertDoesNotThrow(() -> this.instance.increase(i));

    }
    
    /*
	 * Test de la fonction getTime
	 */
    @Test
    public void getTimeTest() {
       
        this.instance.setNextJump(this.i);
        assertEquals(this.i, this.instance.getTime());
        
    }
    
    /*
  	 * Test en cas de chagements innatendus
  	 */
    @Test
    public void deviantTest() {
    	
        assertThrows(Exception.class, () -> this.instance.increase(this.j), "Changement de temps innatendu");
        assertNotEquals(this.i + this.j, this.instance.getTime());
        
    }
    

}
