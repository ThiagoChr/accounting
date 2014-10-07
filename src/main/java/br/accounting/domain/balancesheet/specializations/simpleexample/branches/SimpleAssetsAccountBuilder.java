package br.accounting.domain.balancesheet.specializations.simpleexample.branches;

import br.accounting.domain.balancesheet.AssetsAccountBuilder;

public class SimpleAssetsAccountBuilder implements AssetsAccountBuilder {

	@Override
	public SimpleAssetsAccount build() {

		SimpleAssetsAccount assets = new SimpleAssetsAccount();

		assets.addDescendentAccount(assets.getCash());
		assets.addDescendentAccount(assets.getLongTermLoans());
		assets.addDescendentAccount(assets.getShortTermLoans());
		assets.addDescendentAccount(assets.getSecurities());
		assets.addDescendentAccount(assets.getOtherAssets());

		return assets;
	}

}
