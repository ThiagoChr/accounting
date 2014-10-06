package br.gov.bcb.depep.domain.bank;

import br.gov.bcb.depep.domain.accoutingtools.AccountingNature;
import br.gov.bcb.depep.domain.util.builder.IBuilder;

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
