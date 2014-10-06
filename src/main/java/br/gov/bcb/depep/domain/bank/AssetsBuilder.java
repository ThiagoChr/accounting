package br.gov.bcb.depep.domain.bank;

import br.gov.bcb.depep.domain.util.builder.IBuilder;

public class AssetsBuilder implements IBuilder<Assets> {

	@Override
	public Assets build() {

		Assets assets = new Assets();

		assets.addDescendentAccount(assets.getCash());
		assets.addDescendentAccount(assets.getLongTermLoans());
		assets.addDescendentAccount(assets.getShortTermLoans());
		assets.addDescendentAccount(assets.getSecurities());
		assets.addDescendentAccount(assets.getOtherAssets());

		return assets;
	}

}
