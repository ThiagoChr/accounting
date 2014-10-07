package br.accounting.domain.balancesheet;

import br.accounting.domain.operators.AccountName;
import br.accounting.domain.operators.CompositeAccount;

public abstract class OwnerEquityAccount extends CompositeAccount {

	protected OwnerEquityAccount() {
		super(AccountName.OWNER_EQUITY);
	}

}
