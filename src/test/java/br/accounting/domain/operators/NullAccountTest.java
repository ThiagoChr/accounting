package br.accounting.domain.operators;

import junit.framework.Assert;

import org.joda.time.LocalDate;
import org.junit.Test;

import br.accounting.domain.util.money.Money;

public class NullAccountTest {

	private static final LocalDate ANY_DATE = LocalDate.parse("1986-11-03");

	private NullAccount nullAccount = new NullAccount();

	@Test
	public void nullAccountIsNamedAccordingly() {
		Assert.assertEquals(AccountName.NULL_ACCOUNT, nullAccount.getName());
	}

	@Test
	public void nullAccountsAlwaysHaveZeroBalance() {
		Assert.assertEquals(Money.ZERO, nullAccount.getBalance());
	}

	@Test
	public void nullAccountsAlwaysHaveZeroBalanceAtAnyGivenDay() {
		Assert.assertEquals(Money.ZERO,
				nullAccount.getBalanceUntilDay(ANY_DATE));
	}

}
