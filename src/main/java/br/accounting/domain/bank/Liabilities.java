package br.accounting.domain.bank;

import br.accounting.domain.accoutingtools.AccountName;
import br.accounting.domain.accoutingtools.CompositeAccount;
import br.accounting.domain.accoutingtools.SimpleAccount;

public class Liabilities extends CompositeAccount {

	private SimpleAccount deposits;
	private SimpleAccount shortTermBorrowing;
	private SimpleAccount longTermBorrowing;
	private SimpleAccount otherLiabilities;

	Liabilities() {
		super(AccountName.LIABILITIES);
	}

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
