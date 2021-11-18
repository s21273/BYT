package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("AC456789", "Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000, SEK));

		SweBank.deposit("AC456789", new Money(1000, SEK));
	}
	
	@Test
	public void testAddRemoveTimedPayment() {
		testAccount.addTimedPayment("1", 5, 5, new Money(600, SEK), SweBank,"AC456789" );
		testAccount.addTimedPayment("2", 5, 5, new Money(400, SEK), SweBank,"AC456789" );
		assertTrue("The timed payment with id=1 exists ", testAccount.timedPaymentExists("1"));
		assertTrue("The timed payment with id=2 exists ", testAccount.timedPaymentExists("2"));
		testAccount.removeTimedPayment("2");
		assertFalse("The timed payment with id=2 does not exist ", testAccount.timedPaymentExists("2"));
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		testAccount.addTimedPayment("1", 5, 0, new Money(6000, SEK), SweBank,"AC456789" );
		assertEquals("The balance of testAccount before timedPayment is initialized ",10000,testAccount.getBalance().getAmount(), 0.0001);
		assertEquals("The balance of recipients account before timedPayment is initialized ",1000, SweBank.getBalance("AC456789"), 0.0001);
		testAccount.tick();
		assertEquals("The balance of testAccount after timedPayment is initialized ",4000,testAccount.getBalance().getAmount(), 0.0001);
		assertEquals("The balance of recipients account after timedPayment is initialized ",7000, SweBank.getBalance("AC456789"), 0.0001);
	}

	@Test
	public void testAddWithdraw() {
		assertEquals("The balance before deposit operation ",10000 ,testAccount.getBalance().getAmount(), 0.0001);
		testAccount.deposit(new Money(30000, SEK));
		assertEquals("The balance after deposit operation ",40000 ,testAccount.getBalance().getAmount(), 0.0001);
		testAccount.withdraw(new Money(20000, SEK));
		assertEquals("The balance after withdraw operation ",20000 ,testAccount.getBalance().getAmount(), 0.0001);
		
	}
	
	@Test
	public void testGetBalance() {
		Money m =new Money(10000, SEK);
		Money f = new Money(30000, SEK);
		assertTrue("The balance is 10000 SEK ", testAccount.getBalance().equals(m));
		assertFalse("The balance is not 30000 SEK ", testAccount.getBalance().equals(f));
		
	}
}
