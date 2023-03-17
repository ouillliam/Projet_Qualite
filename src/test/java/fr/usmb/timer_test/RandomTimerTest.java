package fr.usmb.timer_test;

import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import timer.RandomTimer;
import timer.RandomTimer.randomDistribution;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomTimerTest {
	
	/*
	 * Tableau de champs
	 */
	private static Field[] reflexionFields;
	
	/*
	 * Fonction pour setup (préparer) des variables/objet avant la lecture des classes
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		RandomTimer randomTimer = new RandomTimer(randomDistribution.EXP,1);
		Class<?> reflexionRandomTimer = randomTimer.getClass();
		reflexionFields = reflexionRandomTimer.getDeclaredFields();

		for (Field field: reflexionFields) {
			field.setAccessible(true);
		}
	}
	
	/*
	 * Test de la fonction string2Distribution()
	 */
	@Test
	void string2DistributionTest() {
		randomDistribution exp = RandomTimer.string2Distribution("EXP");
		assertEquals(randomDistribution.EXP, exp);
	}
	
	/*
	 * Test de la fonction distribution2String()
	 */
	@Test
	void distribution2StringTest() {
		String s = RandomTimer.distribution2String(randomDistribution.EXP);
		assertEquals("EXP", s);
	}

	/*
	 * Test des différents champs pour un timer de type Poisson
	 */
	@Test
	void champPoissonTest() throws Exception {
		RandomTimer randomTimer = new RandomTimer(randomDistribution.POISSON, 1.1);
		assertEquals(randomDistribution.POISSON, reflexionFields[1].get(randomTimer));
		assertEquals(Double.NaN, reflexionFields[2].get(randomTimer));
		assertEquals(1.1, reflexionFields[3].get(randomTimer));
		assertEquals(0.0, reflexionFields[4].get(randomTimer));
		assertEquals(Double.POSITIVE_INFINITY, reflexionFields[5].get(randomTimer));
		
		
	}
	
	/*
	 * Test des différents champs pour un timer de type Posibilist
	 */
	@Test
	void champPosibilistTest() throws Exception {
		RandomTimer randomTimer = new RandomTimer(randomDistribution.POSIBILIST, 1, 1);
		assertEquals(randomDistribution.POSIBILIST,reflexionFields[1].get(randomTimer));
		assertEquals(Double.NaN, reflexionFields[2].get(randomTimer));
		assertEquals(1.0, reflexionFields[3].get(randomTimer));
		assertEquals(1.0, reflexionFields[4].get(randomTimer));
		assertEquals(1.0 ,reflexionFields[5].get(randomTimer));
	}
	
	/*
	 * Test de la fonction getDistribution()
	 */
	@Test
	void getDistributionTest() throws Exception {
		RandomTimer randomTimer = new RandomTimer(randomDistribution.POSIBILIST, 1, 1);
		assertEquals("POSIBILIST", randomTimer.getDistribution());
	}
	
	/*
	 * Test de la fonction getDistributionParam()
	 */
	@Test
	void getDistributionParamTest() throws Exception {
		RandomTimer randomTimer1 = new RandomTimer(randomDistribution.EXP, 1.1);
		RandomTimer randomTimer2 = new RandomTimer(randomDistribution.POISSON, 1.1);
		RandomTimer randomTimer3 = new RandomTimer(randomDistribution.GAUSSIAN, 1,1);
		assertEquals("rate: 1.1", randomTimer1.getDistributionParam());
		assertEquals("mean: 1.1", randomTimer2.getDistributionParam());
		assertEquals("lolim: 1.0 hilim: 1.0", randomTimer3.getDistributionParam());
	}
	
	/*
	 * Test de la fonction getMean()
	 */
	@Test
	void getMeanTest() throws Exception {
		RandomTimer randomTimer = new RandomTimer(randomDistribution.POISSON, 1.1);
		assertEquals(1.1, randomTimer.getMean());
	}
	
	/*
	 * Test de la fonction toString()
	 */
	@Test
	void toStringTest() throws Exception {
		RandomTimer randomTimer1 = new RandomTimer(randomDistribution.EXP, 1);
		RandomTimer randomTimer2 = new RandomTimer(randomDistribution.POISSON, 1.1);
		RandomTimer randomTimer3 = new RandomTimer(randomDistribution.GAUSSIAN, 1,1);
		RandomTimer randomTimer4 = new RandomTimer(randomDistribution.POSIBILIST, 1,1);
		assertEquals("EXP rate:1.0", randomTimer1.toString()); 
		assertEquals("POISSON mean:1.1", randomTimer2.toString());
		assertEquals("GAUSSIAN LoLim:1.0 HiLim:1.0", randomTimer3.toString());
		assertEquals("POSIBILIST LoLim:1.0 HiLim:1.0", randomTimer4.toString());
		
	}
	
	/*
	 * Test de la fonction hasNext()
	 */
	@Test
	void hasNextTest() throws Exception {
		RandomTimer randomTimer = new RandomTimer(randomDistribution.POSIBILIST, 1, 1);
		assertTrue(randomTimer.hasNext());
	}

}