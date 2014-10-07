package br.accounting.domain.bank;

import br.accounting.domain.accoutingtools.AccountName;
import br.accounting.domain.accoutingtools.CompositeAccount;
import br.accounting.domain.accoutingtools.SimpleAccount;

public class Assets extends CompositeAccount {

	private SimpleAccount cash;
	private SimpleAccount shortTermLoans;
	private SimpleAccount longTermLoans;
	private SimpleAccount securities;
	private SimpleAccount otherAssets;

	Assets() {
		super(AccountName.ASSETS);
	}

	public SimpleAccount getCash() {
		if (cash == null) {
			cash = new SimpleAccount(AccountName.CASH);
		}
		return cash;
	}

	public SimpleAccount getShortTermLoans() {
		if (shortTermLoans == null) {
			shortTermLoans = new SimpleAccount(AccountName.SHORT_TERM_LOANS);
		}
		return shortTermLoans;
	}

	public SimpleAccount getLongTermLoans() {
		if (longTermLoans == null) {
			longTermLoans = new SimpleAccount(AccountName.LONG_TERM_LOANS);
		}
		return longTermLoans;
	}

	public SimpleAccount getSecurities() {
		if (securities == null) {
			securities = new SimpleAccount(AccountName.SECURITIES);
		}
		return securities;
	}

	public SimpleAccount getOtherAssets() {
		if (otherAssets == null) {
			otherAssets = new SimpleAccount(AccountName.OTHER_ASSETS);
		}
		return otherAssets;
	}

}
