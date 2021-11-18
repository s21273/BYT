package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("AB789854","Ulrika");
		SweBank.openAccount("AS984532","Bob");
		Nordea.openAccount("AD674391","Bob");
		DanskeBank.openAccount("WK886655","Gertrud");
	}

	@Test
	public void testGetName() {
		assertEquals("SweBank", SweBank.getName());
		assertEquals("Nordea", Nordea.getName());
		assertEquals("DanskeBank", DanskeBank.getName());
	}

	@Test
	public void testGetCurrency() {
		assertEquals("The currency of SweBank is SEK ", SEK.getName(), SweBank.getCurrency().getName());
		assertEquals("The currency of Nordea is SEK ", SEK.getName(), Nordea.getCurrency().getName());
		assertEquals("The currency of DanskeBank is DKK ", DKK.getName(), DanskeBank.getCurrency().getName());
		
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		SweBank.openAccount("AB764589","Marylin");
		SweBank.deposit("AB764589", new Money(10000,SEK));
		assertEquals("The balance of the new created account ",10000, SweBank.getBalance("AB764589"),0.0001);
		
		//here it throws AccountExistsException because this account already exists
		SweBank.openAccount("AB764589","Marylin");
		
			
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		Nordea.deposit("AD674391", new Money(50000, SEK));
		assertEquals("The balance of AD674391 after deposit operation ", 50000, Nordea.getBalance("AD674391"),0.0001);
		Nordea.deposit("AD674391", new Money(20000, SEK));
		assertEquals("The balance of AD674391 after deposit operation ", 70000, Nordea.getBalance("AD674391"),0.0001);
		Nordea.deposit("AD674391", new Money(6750, SEK));
		assertEquals("The balance of AD674391 after deposit operation ", 76747, Nordea.getBalance("AD674391"),0.0001);
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		Nordea.deposit("AD674391", new Money(50000, SEK));
		assertEquals("The balance of AD674391 before withdraw operation ", 50000, Nordea.getBalance("AD674391"),0.0001);
		Nordea.withdraw("AD674391", new Money(20000, SEK));
		assertEquals("The balance of AD674391 after withdraw operation ", 30000, Nordea.getBalance("AD674391"),0.0001);
		Nordea.withdraw("AD674391", new Money(6750, SEK));
		assertEquals("The balance of AD674391 after withdraw operation ", 23253, Nordea.getBalance("AD674391"),0.0001);
	
		
		
	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		
		assertEquals("The balance of WK886655 is zero now ",0,DanskeBank.getBalance("WK886655"),0.0001);
	    DanskeBank.deposit("WK886655", new Money(50000, DKK));
		assertEquals("The balance of WK886655 after deposit operation is 50000 ", 50000, DanskeBank.getBalance("WK886655"),0.0001);
		
		
	}
	
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		//Transfer between accounts which are from different banks
		Nordea.deposit("AD674391", new Money(50000, SEK));
		 SweBank.deposit("AS984532", new Money(50000, SEK));
		 assertEquals("The balance of AD674391 before transfer ", 50000, Nordea.getBalance("AD674391"),0.0001);
		 assertEquals("The balance of AS984532 before transfer ", 50000, SweBank.getBalance("AS984532"),0.0001); 
		Nordea.transfer("AD674391", SweBank, "AS984532", new Money(30000, SEK));
		 assertEquals("The balance of AD674391 after transfer ", 20000, Nordea.getBalance("AD674391"),0.0001);
		 assertEquals("The balance of AS984532 after transfer ", 80000, SweBank.getBalance("AS984532"),0.0001); 
		//Transfer between accounts in the same bank
		 SweBank.deposit("AB789854", new Money(50000,SEK));
		 assertEquals("The balance of AS984532 before transfer ", 80000, SweBank.getBalance("AS984532"),0.0001); 
		 assertEquals("The balance of AB789854 before transfer ", 50000, SweBank.getBalance("AB789854"),0.0001); 
		SweBank.transfer("AS984532", "AB789854", new Money(45000,SEK));
		 assertEquals("The balance of AS984532 after transfer ", 35000, SweBank.getBalance("AS984532"),0.0001); 
		 assertEquals("The balance of AB789854 after transfer ", 95000, SweBank.getBalance("AB789854"),0.0001); 
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		 SweBank.deposit("AS984532", new Money(80000, SEK));
		SweBank.addTimedPayment("AS984532", "1", 5, 0, new Money(75000,SEK), Nordea,"AD674391" );
		 assertEquals("The balance of AD674391 before timed payment is instatiated ", 0, Nordea.getBalance("AD674391"),0.0001);
		 assertEquals("The balance of AS984532 before timed payment instatiated ", 80000, SweBank.getBalance("AS984532"),0.0001);
		SweBank.tick();
		assertEquals("The balance of AD674391 after timed payment is instatiated ", 75000, Nordea.getBalance("AD674391"),0.0001);
		 assertEquals("The balance of AS984532 after timed payment is instatiated  ",5000 , SweBank.getBalance("AS984532"),0.0001); 
		
		
		
		
	}
}
