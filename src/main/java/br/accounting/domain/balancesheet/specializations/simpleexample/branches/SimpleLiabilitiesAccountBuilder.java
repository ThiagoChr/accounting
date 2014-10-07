package br.accounting.domain.balancesheet.specializations.simpleexample.branches;

import br.accounting.domain.balancesheet.LiabilitiesAccountBuilder;

public class SimpleLiabilitiesAccountBuilder implements LiabilitiesAccountBuilder {

	@Override
	public SimpleLiabilitiesAccount build() {

		SimpleLiabilitiesAccount liabilities = new SimpleLiabilitiesAccount();

		liabilities.addDescendentAccount(liabilities.getDeposits());
		liabilities.addDescendentAccount(liabilities.getLongTermBorrowing());
		liabilities.addDescendentAccount(liabilities.getShortTermBorrowing());
		liabilities.addDescendentAccount(liabilities.getOtherLiabilities());

		return liabilities;
	}

}
