package fr.emse.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class MoneyBagTest {
    private Money f12CHF;
    private Money f14CHF;
    private Money f7USD;
    private Money f21USD;
    private MoneyBag fMB1;
    private MoneyBag fMB2;

	@Before 
	public void setUp() { 
	f12CHF= new Money(12, "CHF"); 
	f14CHF= new Money(14, "CHF"); 
	f7USD= new Money( 7, "USD"); 
	f21USD= new Money(21, "USD"); 
	fMB1= new MoneyBag(f12CHF, f7USD); 
	fMB2= new MoneyBag(f14CHF, f21USD); 
	} 
	
	@Test 
	public void testBagEquals() { 
	assertTrue(!fMB1.equals(null)); 
	assertEquals(fMB1, fMB1); 
	assertTrue(!fMB1.equals(f12CHF)); 
	assertTrue(!f12CHF.equals(fMB1)); 
	assertTrue(!fMB1.equals(fMB2)); 
	} 
	
	@Test 
	public void testMixedSimpleAdd() { 
		// [12 CHF] + [7 USD] == {[12 CHF][7 USD]} 
		Money bag[] = { f12CHF, f7USD }; 
		MoneyBag expected = new MoneyBag(bag); 
		assertEquals(expected, f12CHF.add(f7USD)); 
	} 
	
	@Test
    public void testBagSimpleAdd() {
        Money bag[] = { f12CHF, f7USD, f14CHF };
        MoneyBag expected = new MoneyBag(bag);
        assertEquals(expected, fMB1.add(f14CHF));
    }

    @Test
    public void testSimpleBagAdd() {
        Money bag[] = { f12CHF, f7USD, f14CHF };
        MoneyBag expected = new MoneyBag(bag);
        assertEquals(expected, f14CHF.add(fMB1));
    }

    @Test
    public void testBagBagAdd() {
        Money bag[] = { new Money(26, "CHF"), new Money(28, "USD") };
        MoneyBag expected = new MoneyBag(bag);
        assertEquals(expected, fMB1.add(fMB2));
    }
    
    @Test
    public void testSimplifyToMoney() {
        MoneyBag bag = new MoneyBag(f12CHF, f7USD);
        IMoney result = bag.add(new Money(-12, "CHF")); // on annule les CHF
        assertEquals(f7USD, result); // doit simplifier en Money(7, "USD")
    }

    

}
