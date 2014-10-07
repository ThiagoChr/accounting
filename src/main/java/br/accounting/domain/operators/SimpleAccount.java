package br.accounting.domain.operators;

import java.util.List;
import java.util.SortedSet;

import org.joda.time.LocalDate;

import br.accounting.domain.util.money.Money;
import br.accounting.domain.util.money.MoneyFunction;
import br.accounting.domain.util.money.MoneyOperations;
import br.accounting.domain.util.visualization.IAggregatableStructure;
import ch.lambdaj.Lambda;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

public class SimpleAccount extends Account {

	private SortedSet<AccountingEntry> accountingEntries = Sets.newTreeSet();

	public SimpleAccount(AccountName name) {
		super(name);
	}

	public void addAccountingEntry(Money value, LocalDate valueDate) {
		Preconditions.checkNotNull(value,
				"Cannot add null-valued accounting entries!");
		Preconditions.checkNotNull(valueDate,
				"Cannot add accounting entries with no dates!");

		AccountingEntry newEntry = new AccountingEntry(value, valueDate);

		Preconditions.checkState(getAccountingEntries().add(newEntry),
				"The entry " + newEntry + " is already present "
						+ "in the set of accounting entries!");
	}

	@Override
	public Money getBalance() {

		return MoneyOperations.sum(getAccountingEntries(),
				new MoneyFunction<AccountingEntry>() {

					@Override
					public Money getOperand(AccountingEntry input) {
						return input.getValue();
					}
				});
	}

	@Override
	public Money getBalanceUntilDay(LocalDate referenceDate) {

		List<AccountingEntry> notAfterAccountingEntries = Lambda.select(
				getAccountingEntries(),
				Lambda.having(Lambda.on(AccountingEntry.class).isNotAfter(
						referenceDate)));

		return MoneyOperations.sum(notAfterAccountingEntries,
				new MoneyFunction<AccountingEntry>() {

					@Override
					public Money getOperand(AccountingEntry input) {
						return input.getValue();
					}
				});

	}

	protected SortedSet<AccountingEntry> getAccountingEntries() {
		return accountingEntries;
	}

	@Override
	public SortedSet<? extends IAggregatableStructure> getStructuralChildren() {
		return Sets.newTreeSet();
	}

}
