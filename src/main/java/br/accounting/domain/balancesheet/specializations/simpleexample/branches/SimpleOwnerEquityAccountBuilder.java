package br.accounting.domain.balancesheet.specializations.simpleexample.branches;

import br.accounting.domain.balancesheet.OwnerEquityAccountBuilder;

public class SimpleOwnerEquityAccountBuilder implements OwnerEquityAccountBuilder {

	@Override
	public SimpleOwnerEquityAccount build() {

		SimpleOwnerEquityAccount ownerEquity = new SimpleOwnerEquityAccount();

		ownerEquity.addDescendentAccount(ownerEquity.getEquities());

		return ownerEquity;
	}

}
