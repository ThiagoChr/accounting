package br.accounting.domain.operators;

import java.util.SortedSet;

import org.joda.time.LocalDate;

import br.accounting.domain.util.money.Money;
import br.accounting.domain.util.visualization.IAggregatableStructure;

import com.google.common.collect.Sets;

/**
 * Null-object for the account class.
 * 
 */
public class NullAccount extends Account {

	public NullAccount() {
		super(AccountName.NULL_ACCOUNT);
	}

	@Override
	public Money getBalance() {
		return Money.ZERO;
	}

	@Override
	public Money getBalanceUntilDay(LocalDate referenceDate) {
		return Money.ZERO;
	}

	@Override
	public SortedSet<? extends IAggregatableStructure> getStructuralChildren() {
		return Sets.newTreeSet();
	}

}
