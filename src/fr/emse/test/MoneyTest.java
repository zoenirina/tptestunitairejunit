package fr.emse.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class MoneyTest {

	@Test 
	public void testSimpleAdd() { 
		Money m12CHF = new Money(12, "CHF"); // création de données 
		Money m14CHF = new Money(14, "CHF"); 
		Money expected = new Money(26, "CHF"); 
		Money result = m12CHF.add(m14CHF); // exécution de la méthode testée 
		assertTrue(expected.equals(result)); // comparaison @Test
	}
	
}
