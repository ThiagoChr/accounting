package br.accounting.domain.accoutingtools;

import org.joda.time.LocalDate;

import br.accounting.domain.util.money.Money;
import br.accounting.domain.util.visualization.AggregatableStructureProcessor;
import br.accounting.domain.util.visualization.IAggregatableStructure;

import com.google.common.base.Preconditions;

public abstract class Account implements IAggregatableStructure,
		Comparable<Account> {

	private AccountName name;

	public Account(AccountName name) {
		this.name = Preconditions.checkNotNull(name,
				"Accounts must have a name!");
	}

	public abstract Money getBalance();

	public abstract Money getBalanceUntilDay(LocalDate referenceDate);

	public final AccountName getName() {
		return name;
	}

	@Override
	public String toString() {
		return new AggregatableStructureProcessor(this).visualize();
	}

	@Override
	public int compareTo(Account o) {
		return this.getName().compareTo(o.getName());
	}

	@Override
	public final String getStructureDescription() {
		return "|____ " + getName().getDescription() + ": " + getBalance();
	}

}
