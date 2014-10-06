package br.gov.bcb.depep.domain.bank;

import br.gov.bcb.depep.domain.accoutingtools.AccountName;
import br.gov.bcb.depep.domain.accoutingtools.CompositeAccount;
import br.gov.bcb.depep.domain.accoutingtools.SimpleAccount;

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
