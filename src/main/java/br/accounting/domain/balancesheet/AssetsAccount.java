package br.accounting.domain.balancesheet;

import br.accounting.domain.operators.AccountName;
import br.accounting.domain.operators.CompositeAccount;

public abstract class AssetsAccount extends CompositeAccount {

	public AssetsAccount() {
		super(AccountName.ASSETS);
	}

}
