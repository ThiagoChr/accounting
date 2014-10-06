package br.gov.bcb.depep.domain.bank;

import br.gov.bcb.depep.domain.accoutingtools.AccountName;
import br.gov.bcb.depep.domain.accoutingtools.CompositeAccount;
import br.gov.bcb.depep.domain.util.money.Money;

public class StylizedBalanceSheet extends CompositeAccount {

	private Assets assets; // applications

	private Liabilities liabilities; // money from others
	private OwnerEquity ownerEquity; // money from inside

	StylizedBalanceSheet() {
		super(AccountName.BALANCE_SHEET);
	}

	public Assets getAssets() {
		if (assets == null) {
			assets = new AssetsBuilder().build();
		}
		return assets;
	}

	public Liabilities getLiabilities() {
		if (liabilities == null) {
			liabilities = new LiabilitiesBuilder().build();
		}
		return liabilities;
	}

	public OwnerEquity getOwnerEquity() {
		if (ownerEquity == null) {
			ownerEquity = new OwnerEquityBuilder().build();
		}

		return ownerEquity;
	}

	public void verifyConsistency() {

		if (getBalance().isZero()) {
			return;
		}

		Money resources = getLiabilities().getBalance().add(
				getOwnerEquity().getBalance());

		Money applications = getAssets().getBalance();

		throw new IllegalStateException(
				String.format(
						"The total assets (%s) is not equal to the sum of the resources (%s + %s = %s)", //
						applications, //
						liabilities.getBalance(), //
						ownerEquity.getBalance(), //
						resources));

	}
}
