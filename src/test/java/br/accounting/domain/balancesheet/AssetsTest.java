package br.accounting.domain.balancesheet;

import junit.framework.Assert;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import br.accounting.domain.balancesheet.specializations.simpleexample.branches.SimpleAssetsAccount;
import br.accounting.domain.balancesheet.specializations.simpleexample.branches.SimpleAssetsAccountBuilder;
import br.accounting.domain.util.money.Money;

public class AssetsTest {

	private static final LocalDate DATE = LocalDate.parse("2000-01-01");

	private SimpleAssetsAccount assets;

	@Before
	public void setUp() {
		assets = new SimpleAssetsAccountBuilder().build();
	}

	@Test
	public void balanceOfTotalAssetsAccountIsTheSumOfChildren() {

		assets.getCash().addAccountingEntry(Money.newMoney("100,00"), DATE);
		assets.getShortTermLoans().addAccountingEntry(Money.newMoney("200,00"),
				DATE);

		Assert.assertEquals(Money.newMoney("300,00"), assets.getBalance());

	}
}
