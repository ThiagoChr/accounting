package br.accounting.domain.balancesheet;

import br.accounting.domain.operators.AccountingNature;
import br.accounting.domain.util.builder.IBuilder;

import com.google.common.base.Preconditions;

public class StylizedBalanceSheetBuilder implements
		IBuilder<StylizedBalanceSheet> {

	private AssetsAccountBuilder assetsAccountBuilder;
	private LiabilitiesAccountBuilder liabilitiesAccountBuilder;
	private OwnerEquityAccountBuilder ownerEquityAccountBuilder;

	StylizedBalanceSheetBuilder(AssetsAccountBuilder assetsAccountBuilder,
			LiabilitiesAccountBuilder liabilitiesAccountBuilder,
			OwnerEquityAccountBuilder ownerEquityAccountBuilder) {

		this.assetsAccountBuilder = Preconditions.checkNotNull(
				assetsAccountBuilder,
				"A strategy to build the assets side must be injected!");
		this.liabilitiesAccountBuilder = Preconditions.checkNotNull(
				liabilitiesAccountBuilder,
				"A strategy to build the liabilities side must be injected!");
		this.ownerEquityAccountBuilder = Preconditions
				.checkNotNull(ownerEquityAccountBuilder,
						"A strategy to build the owner's equity side must be injected!");
	}

	@Override
	public StylizedBalanceSheet build() {

		AssetsAccount assetsAccount = assetsAccountBuilder.build();
		LiabilitiesAccount liabilitiesAccount = liabilitiesAccountBuilder
				.build();
		OwnerEquityAccount ownerEquityAccount = ownerEquityAccountBuilder
				.build();

		StylizedBalanceSheet balanceSheet = new StylizedBalanceSheet(
				assetsAccount, liabilitiesAccount, ownerEquityAccount);

		balanceSheet.addDescendentAccount(assetsAccount);

		balanceSheet.addDescendentAccount(liabilitiesAccount,
				AccountingNature.NEGATIVE);
		balanceSheet.addDescendentAccount(ownerEquityAccount,
				AccountingNature.NEGATIVE);

		return balanceSheet;
	}

}
