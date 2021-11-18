package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		NOK = new Currency("NOK", 0.35);
		
	}

	@Test
	public void testGetName() {
		
		assertEquals("SEK", SEK.getName());
		assertEquals("DKK", DKK.getName());
		assertEquals("EUR", EUR.getName());
		
	}
	
	@Test
	public void testGetRate() {
	
		assertEquals(0.15, SEK.getRate(), 0.00001);
		assertEquals(0.20, DKK.getRate(), 0.00001);
		assertEquals(1.5, EUR.getRate(), 0.00001);
	}
	
	@Test
	public void testSetRate() {
		
		NOK.setRate(0.67);
		assertEquals(0.67, NOK.getRate(), 0.00001);
	}
	
	@Test
	public void testGlobalValue() {
		
		assertEquals("The universal value of 400 is 60 ",6000, SEK.universalValue(40000), 0.00001);
		assertEquals("The universal value of 237.90 is 47.58 ", 4758, DKK.universalValue(23790), 0.00001);
		assertEquals("The universal value of 89.92 is 134.88 ", 13488, EUR.universalValue(8992), 0.00001);
	}
	
	@Test
	public void testValueInThisCurrency() {
	
		assertEquals("The 400 DKK will be 533.33 SEK ", 53333  ,SEK.valueInThisCurrency(40000, DKK), 0.0001 );
		assertEquals("The 23.48 EUR will be 176.10 DKK ", 17610  ,DKK.valueInThisCurrency(2348, EUR), 0.0001 );
		assertEquals( "The 359.20 SEK will be 35.92 EUR ",3592  ,EUR.valueInThisCurrency(35920, SEK), 0.0001 );
		
		
	}
	

}
