package br.accounting.domain.balancesheet;

import br.accounting.domain.operators.AccountingNature;
import br.accounting.domain.util.builder.IBuilder;

public class StylizedBalanceSheetBuilder implements
		IBuilder<StylizedBalanceSheet> {

	@Override
	public StylizedBalanceSheet build() {

		StylizedBalanceSheet balanceSheet = new StylizedBalanceSheet();

		balanceSheet.addDescendentAccount(balanceSheet.getAssets());

		balanceSheet.addDescendentAccount(balanceSheet.getLiabilities(),
				AccountingNature.NEGATIVE);
		balanceSheet.addDescendentAccount(balanceSheet.getOwnerEquity(),
				AccountingNature.NEGATIVE);

		return balanceSheet;
	}

}
