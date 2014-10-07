package br.accounting.domain.balancesheet;

import br.accounting.domain.util.builder.IBuilder;

public class LiabilitiesBuilder implements IBuilder<Liabilities> {

	@Override
	public Liabilities build() {

		Liabilities liabilities = new Liabilities();

		liabilities.addDescendentAccount(liabilities.getDeposits());
		liabilities.addDescendentAccount(liabilities.getLongTermBorrowing());
		liabilities.addDescendentAccount(liabilities.getShortTermBorrowing());
		liabilities.addDescendentAccount(liabilities.getOtherLiabilities());

		return liabilities;
	}

}
