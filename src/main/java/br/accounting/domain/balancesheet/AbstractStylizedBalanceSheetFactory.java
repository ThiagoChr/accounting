package br.accounting.domain.balancesheet;


public abstract class AbstractStylizedBalanceSheetFactory {

	protected abstract AssetsAccountBuilder getAssetsAccountBuilder();

	protected abstract LiabilitiesAccountBuilder getLiabilitiesAccountBuilder();

	protected abstract OwnerEquityAccountBuilder getOwnerEquityAccountBuilder();

	public final StylizedBalanceSheet construct() {
		return new StylizedBalanceSheetBuilder(getAssetsAccountBuilder(),
				getLiabilitiesAccountBuilder(), getOwnerEquityAccountBuilder())
				.build();
	}

}
