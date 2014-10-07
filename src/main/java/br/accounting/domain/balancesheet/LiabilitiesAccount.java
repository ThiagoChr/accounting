package br.accounting.domain.balancesheet;

import br.accounting.domain.operators.AccountName;
import br.accounting.domain.operators.CompositeAccount;

public class LiabilitiesAccount extends CompositeAccount {

	protected LiabilitiesAccount() {
		super(AccountName.LIABILITIES);
	}

}
