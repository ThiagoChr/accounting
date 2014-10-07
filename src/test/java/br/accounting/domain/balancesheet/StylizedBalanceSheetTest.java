package br.accounting.domain.balancesheet;

import junit.framework.Assert;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import br.accounting.domain.util.money.Money;

public class StylizedBalanceSheetTest {

    private static final LocalDate DATE = LocalDate.parse("2000-01-01");
    private StylizedBalanceSheet balanceSheet;

    @Before
    public void setUp() {
        balanceSheet = new StylizedBalanceSheetBuilder().build();
    }

    @Test
    public void balanceSheetMustAlwaysPossessZeroValuedBalance() {

        balanceSheet.getAssets().getCash()
                .addAccountingEntry(Money.newMoney("100,00"), DATE);

        balanceSheet.getLiabilities().getDeposits()
                .addAccountingEntry(Money.newMoney("100,00"), DATE);

        Assert.assertEquals(Money.ZERO, balanceSheet.getBalance());

    }

}
