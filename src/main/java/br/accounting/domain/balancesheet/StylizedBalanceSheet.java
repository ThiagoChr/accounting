package br.accounting.domain.balancesheet;

import br.accounting.domain.operators.AccountName;
import br.accounting.domain.operators.CompositeAccount;
import br.accounting.domain.util.money.Money;

import com.google.common.base.Preconditions;

/**
 * General stylized balance sheet. Inject the children accordingly to your
 * needs.
 */
public class StylizedBalanceSheet extends CompositeAccount {

	private AssetsAccount assets; // applications

	private LiabilitiesAccount liabilities; // money from others
	private OwnerEquityAccount ownerEquity; // money from inside

	StylizedBalanceSheet(AssetsAccount assets, LiabilitiesAccount liabilities,
			OwnerEquityAccount ownerEquity) {
		super(AccountName.BALANCE_SHEET);

		this.assets = Preconditions.checkNotNull(assets,
				"The assets composite account cannot be null!");
		this.liabilities = Preconditions.checkNotNull(liabilities,
				"The liabilities composite account cannot be null!");
		this.ownerEquity = Preconditions.checkNotNull(ownerEquity,
				"The owner's equity composite account cannot be null!");
	}

	public AssetsAccount getAssets() {
		return assets;
	}

	public LiabilitiesAccount getLiabilities() {
		return liabilities;
	}

	public OwnerEquityAccount getOwnerEquity() {
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
