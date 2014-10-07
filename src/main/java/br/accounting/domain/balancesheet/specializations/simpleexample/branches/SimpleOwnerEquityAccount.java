package br.accounting.domain.balancesheet.specializations.simpleexample.branches;

import br.accounting.domain.balancesheet.OwnerEquityAccount;
import br.accounting.domain.operators.AccountName;
import br.accounting.domain.operators.SimpleAccount;

public class SimpleOwnerEquityAccount extends OwnerEquityAccount {

	private SimpleAccount equities;

	public SimpleAccount getEquities() {
		if (equities == null) {
			equities = new SimpleAccount(AccountName.EQUITIES);
		}
		return equities;
	}

}
