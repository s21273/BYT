package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
	}

	@Test
	public void testGetAmount() {
		assertEquals(10000, SEK100.getAmount(), 0.00001);
		assertEquals(2000, EUR20.getAmount(), 0.00001);
		assertEquals(20000, SEK200.getAmount(), 0.00001);
	}

	@Test
	public void testGetCurrency() {
		assertEquals(SEK.getName(), SEK100.getCurrency().getName());
		assertEquals(EUR.getName(), EUR20.getCurrency().getName());
		assertEquals(SEK.getName(), SEKn100.getCurrency().getName());
		
	}

	@Test
	public void testToString() {
		String s ="100.0 SEK";
		String d = "20.0 EUR";
		String m = "-100.0 SEK";
		assertEquals(s, SEK100.toString());
		assertEquals(d, EUR20.toString());
		assertEquals(m, SEKn100.toString());
	}

	@Test
	public void testGlobalValue() {
		assertEquals("The global value of 100 SEK is 1500 ",1500, SEK100.universalValue(), 0.00001);
		assertEquals("The global value of 10 EUR is 1500 ",1500, EUR10.universalValue(), 0.00001);
		assertEquals("The global value of 200 SEK is 3000 ",3000, SEK200.universalValue(), 0.00001);
		assertEquals("The global value of 20 EUR is 3000 ",3000, EUR20.universalValue(), 0.00001);
		assertEquals("The global value of 0 SEK is 0 ",0, SEK0.universalValue(), 0.00001);
		assertEquals("The global value of -100 SEK is -1500 ",-1500, SEKn100.universalValue(), 0.00001);
	}

	@Test
	public void testEqualsMoney() {
		assertTrue("They have equal universal values ",SEK100.equals(EUR10));
		assertTrue("They have equal universal values ",SEK200.equals(EUR20));
		assertFalse("They do not have equal universal values ",SEKn100.equals(EUR20));
		assertFalse("They do not have equal universal values ",SEK200.equals(EUR0));
		
		
	}

	@Test
	public void testAdd() {
		assertEquals("The sum of 10EUR and 100SEK wil be 200 SEK",20000, SEK100.add(EUR10).getAmount(), 0.00001);
		assertEquals("The sum of 20EUR and 200SEK wil be 400 SEK",40000, SEK200.add(EUR20).getAmount(), 0.00001);
		assertEquals("The sum of 200SEK and 100SEK wil be 300 SEK",30000, SEK100.add(SEK200).getAmount(), 0.00001);
	}

	@Test
	public void testSub() {
		assertEquals("The substraction from 100SEK  10EUR wil be 0", 0, SEK100.sub(EUR10).getAmount(), 0.00001);
		assertEquals("The substraction from 200SEK  20EUR wil be 0",0, SEK200.sub(EUR20).getAmount(), 0.00001);
		assertEquals("The substraction from 100SEK  200SEK wil be -10000",-10000, SEK100.sub(SEK200).getAmount(), 0.00001);
	}

	@Test
	public void testIsZero() {
		assertTrue("It has a zero value ",SEK0.isZero());
		assertTrue("It has a zero value ",EUR0.isZero());
		assertFalse("It does not have a zero value ", SEK100.isZero());
	}

	@Test
	public void testNegate() {
		assertEquals(-10000, SEK100.negate().getAmount(), 0.00001);
		assertEquals(-20000, SEK200.negate().getAmount(), 0.00001);
		assertEquals(-1000, EUR10.negate().getAmount(), 0.00001);
		assertEquals(-2000, EUR20.negate().getAmount(), 0.00001);
	}

	@Test
	public void testCompareTo() {
		assertEquals("SEK100 is less than SEK200 ",-1, SEK100.compareTo(SEK200), 0.00001);
		assertEquals("SEK200 is greater than SEK100 ",1, SEK200.compareTo(SEK100), 0.00001);
		assertEquals("SEK200 is equal to EUR20 ", 0, SEK200.compareTo(EUR20), 0.00001);
		
		
	}
}
