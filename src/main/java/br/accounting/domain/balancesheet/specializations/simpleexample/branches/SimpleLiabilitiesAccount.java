package br.accounting.domain.balancesheet.specializations.simpleexample.branches;

import br.accounting.domain.balancesheet.LiabilitiesAccount;
import br.accounting.domain.operators.AccountName;
import br.accounting.domain.operators.SimpleAccount;

public class SimpleLiabilitiesAccount extends LiabilitiesAccount {

	private SimpleAccount deposits;
	private SimpleAccount shortTermBorrowing;
	private SimpleAccount longTermBorrowing;
	private SimpleAccount otherLiabilities;

	public SimpleAccount getDeposits() {
		if (deposits == null) {
			deposits = new SimpleAccount(AccountName.DEPOSITS);
		}
		return deposits;
	}

	public SimpleAccount getShortTermBorrowing() {
		if (shortTermBorrowing == null) {
			shortTermBorrowing = new SimpleAccount(
					AccountName.SHORT_TERM_BORROWING);
		}
		return shortTermBorrowing;
	}

	public SimpleAccount getLongTermBorrowing() {
		if (longTermBorrowing == null) {
			longTermBorrowing = new SimpleAccount(
					AccountName.LONG_TERM_BORROWING);
		}
		return longTermBorrowing;
	}

	public SimpleAccount getOtherLiabilities() {
		if (otherLiabilities == null) {
			otherLiabilities = new SimpleAccount(AccountName.OTHER_LIABILITIES);
		}
		return otherLiabilities;
	}

}
