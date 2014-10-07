package br.accounting.domain.accoutingtools;

import java.util.SortedSet;

import org.joda.time.LocalDate;

import br.accounting.domain.util.money.Money;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

public class CompositeAccount extends Account {

	private SortedSet<AccountWithNature> descendentAccounts = Sets.newTreeSet();

	protected CompositeAccount(AccountName accountName) {
		super(accountName);
	}

	public void addDescendentAccount(Account newDescendent) {
		addDescendentAccount(newDescendent, AccountingNature.POSITIVE);
	}

	public void addDescendentAccount(Account newDescendent,
			AccountingNature nature) {

		Preconditions.checkNotNull(newDescendent,
				"The new descendent accounts must not be null!");

		AccountWithNature accountWithNature = new AccountWithNature(
				newDescendent, nature);

		Preconditions.checkState(descendentAccounts.add(accountWithNature),
				"Could not add the descendent " + newDescendent + "!");
	}

	public Money getBalance() {

		Money total = Money.ZERO;

		for (Account account : descendentAccounts) {
			total = total.add(account.getBalance());
		}

		return total;
	}

	public Money getBalanceUntilDay(LocalDate referenceDate) {

		Money total = Money.ZERO;

		for (Account account : descendentAccounts) {
			total = total.add(account.getBalanceUntilDay(referenceDate));
		}

		return total;
	}

	public SortedSet<AccountWithNature> getStructuralChildren() {
		return descendentAccounts;
	}

}
