package br.accounting.domain.balancesheet;

import br.accounting.domain.operators.AccountName;
import br.accounting.domain.operators.CompositeAccount;
import br.accounting.domain.operators.SimpleAccount;

public class OwnerEquity extends CompositeAccount {

	private SimpleAccount equities;

	OwnerEquity() {
		super(AccountName.OWNER_EQUITY);
	}

	public SimpleAccount getEquities() {
		if (equities == null) {
			equities = new SimpleAccount(AccountName.EQUITIES);
		}
		return equities;
	}

}
