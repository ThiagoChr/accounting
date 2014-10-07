package br.accounting.domain.bank;

import br.accounting.domain.accoutingtools.AccountName;
import br.accounting.domain.accoutingtools.CompositeAccount;
import br.accounting.domain.accoutingtools.SimpleAccount;

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
