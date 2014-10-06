package br.gov.bcb.depep.domain.bank;

import br.gov.bcb.depep.domain.util.builder.IBuilder;

public class OwnerEquityBuilder implements IBuilder<OwnerEquity> {

	@Override
	public OwnerEquity build() {

		OwnerEquity ownerEquity = new OwnerEquity();

		ownerEquity.addDescendentAccount(ownerEquity.getEquities());

		return ownerEquity;
	}

}
