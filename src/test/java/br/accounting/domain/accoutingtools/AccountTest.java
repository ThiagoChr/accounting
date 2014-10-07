package br.accounting.domain.accoutingtools;

import junit.framework.Assert;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import br.accounting.domain.accoutingtools.AccountName;
import br.accounting.domain.accoutingtools.AccountingEntry;
import br.accounting.domain.accoutingtools.SimpleAccount;
import br.accounting.domain.util.money.Money;

public class AccountTest {

	private SimpleAccount account;

	@Before
	public void setUp() {
		account = new SimpleAccount(AccountName.NO_NAME);
	}

	@Test(expected = NullPointerException.class)
	public void cannotCreateNullNamedAccounts() {

		AccountName nullNamed = null;

		// EXERCISE
		new SimpleAccount(nullNamed);
	}

	@Test
	public void newAccountsHaveZeroBalance() {
		Assert.assertEquals(Money.ZERO, account.getBalance());
	}

	@Test
	public void balanceIsGivenByTheSumOfAllAccountingEntries() {

		AccountingEntry entryA = Mockito.mock(AccountingEntry.class);
		AccountingEntry entryB = Mockito.mock(AccountingEntry.class);

		Mockito.when(entryA.getValue()).thenReturn(Money.ONE);
		Mockito.when(entryB.getValue()).thenReturn(Money.TEN);

		account.getAccountingEntries().add(entryA);
		account.getAccountingEntries().add(entryB);

		Assert.assertEquals(Money.newMoney("11,00"), account.getBalance());

	}

	@Test
	public void balanceUntilDateOnlySumsUpAccountingEntriesNotAfterTheGivenDate() {

		AccountingEntry entryA = Mockito.mock(AccountingEntry.class);
		AccountingEntry entryB = Mockito.mock(AccountingEntry.class);

		LocalDate threeNovember = LocalDate.parse("2000-11-03");

		Mockito.when(entryA.getValue()).thenReturn(Money.ONE);
		Mockito.when(entryA.isNotAfter(threeNovember)).thenReturn(true);

		Mockito.when(entryB.getValue()).thenReturn(Money.TEN);
		Mockito.when(entryB.isNotAfter(threeNovember)).thenReturn(false);

		account.getAccountingEntries().add(entryA);
		account.getAccountingEntries().add(entryB);

		Assert.assertEquals(Money.ONE,
				account.getBalanceUntilDay(threeNovember));

	}
}
