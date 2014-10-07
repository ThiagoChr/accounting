package br.accounting.domain.balancesheet.specializations.simpleexample;

import br.accounting.domain.balancesheet.AbstractStylizedBalanceSheetFactory;
import br.accounting.domain.balancesheet.AssetsAccountBuilder;
import br.accounting.domain.balancesheet.LiabilitiesAccountBuilder;
import br.accounting.domain.balancesheet.OwnerEquityAccountBuilder;
import br.accounting.domain.balancesheet.specializations.simpleexample.branches.SimpleAssetsAccountBuilder;
import br.accounting.domain.balancesheet.specializations.simpleexample.branches.SimpleLiabilitiesAccountBuilder;
import br.accounting.domain.balancesheet.specializations.simpleexample.branches.SimpleOwnerEquityAccountBuilder;

public class SimpleStylizedBalanceSheetFactory extends
		AbstractStylizedBalanceSheetFactory {

	@Override
	protected AssetsAccountBuilder getAssetsAccountBuilder() {
		return new SimpleAssetsAccountBuilder();
	}

	@Override
	protected LiabilitiesAccountBuilder getLiabilitiesAccountBuilder() {
		return new SimpleLiabilitiesAccountBuilder();
	}

	@Override
	protected OwnerEquityAccountBuilder getOwnerEquityAccountBuilder() {
		return new SimpleOwnerEquityAccountBuilder();
	}

}
