package fr.usmb.timer_test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;
import java.util.TreeSet;
import java.util.Vector;

import org.junit.jupiter.api.Test;

import timer.DateTimer;

class DateTimerTest {

	/*
	 * Test de la fonction hasNext
	 */
    @Test
    void hasNextTest() {

        DateTimer emptyDates = new DateTimer(new TreeSet<>());
        assertFalse(emptyDates.hasNext());

        DateTimer emptyLapsTime = new DateTimer(new Vector<>());
        assertFalse(emptyLapsTime.hasNext());

        assertThrows(NullPointerException.class, () -> new DateTimer((TreeSet<Integer>) null));

        @SuppressWarnings({ "unchecked", "rawtypes" })
        TreeSet<Integer> treeSet = new TreeSet();
        treeSet.add(1);
        treeSet.add(2);
        treeSet.add(3);
        DateTimer dateTimer= new DateTimer(treeSet);

        for (int i = 0; i < treeSet.size(); i++) {
            assertTrue(dateTimer.hasNext());
            dateTimer.next();
        }

        assertFalse(dateTimer.hasNext());

    }

    /*
	 * Test de la fonction next
	 */
    @Test
    void nextTest() {

        DateTimer emptyDates = new DateTimer(new TreeSet<>());
        assertThrows(NoSuchElementException.class, () -> emptyDates.next());

        @SuppressWarnings({ "rawtypes", "unchecked" })
        TreeSet<Integer> treeSet = new TreeSet();
        treeSet.add(1);
        treeSet.add(2);
        treeSet.add(3);
        DateTimer dateTimer = new DateTimer(treeSet);

        for (int i = 0; i < treeSet.size(); i++) {
            assertEquals(1, dateTimer.next());
        }

        assertThrows(NoSuchElementException.class, () -> dateTimer.next());
    }
}
