package fr.usmb.discrete_behavior_simulator_test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import discreteBehaviorSimulator.LogFormatter;

class LogFormatterTest {
	
	/*
	 * On initialise des instances d'objet
	 */
	private LogFormatter logForm;
	private Pattern date;
	
	/*
	 * SetUp de la classe permettant l'intanciation des objets avant chaque tests
	 */
	@BeforeEach
	void setUp() throws Exception {
			
		this.logForm = new LogFormatter();
		String dateRegex = "^[0-9]{4}(\\.[0-9]{2}){2}\\s([0-9]{2}:){2}[0-9]{2}\\.[0-9]{3}";
		this.date = Pattern.compile(dateRegex);
	}

	/*
	 * Test de la fonction format
	 */
    @Test
    public void formatTest() {
    	
        LogRecord log = new LogRecord(Level.WARNING, "Ceci est un test, vive les idu");
        String format = this.logForm.format(log);

        String dateFormatted = format.split(": ")[0];
        Matcher m = this.date.matcher(dateFormatted);

        assertTrue(dateFormatted instanceof String);
        assertTrue(m.matches());
    }

}
