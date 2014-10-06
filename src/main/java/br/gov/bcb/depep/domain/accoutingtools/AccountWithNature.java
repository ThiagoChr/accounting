package br.gov.bcb.depep.domain.accoutingtools;

import java.util.SortedSet;

import org.joda.time.LocalDate;

import br.gov.bcb.depep.domain.util.money.Money;
import br.gov.bcb.depep.domain.util.visualization.IAggregatableStructure;

import com.google.common.base.Preconditions;

public class AccountWithNature extends Account {

	private Account account;
	private AccountingNature nature;

	public AccountWithNature(Account account) {
		this(account, AccountingNature.POSITIVE);
	}

	public AccountWithNature(Account account, AccountingNature nature) {
		super(account.getName());
		this.account = Preconditions.checkNotNull(account,
				"The account cannot be null!");
		this.nature = Preconditions.checkNotNull(nature,
				"The accouting nature cannot be null!");
	}

	public Account getAccount() {
		return account;
	}

	public AccountingNature getNature() {
		return nature;
	}

	@Override
	public Money getBalance() {
		return applyAccoutingNature(account.getBalance());
	}

	@Override
	public Money getBalanceUntilDay(LocalDate referenceDate) {
		return applyAccoutingNature(account.getBalanceUntilDay(referenceDate));
	}

	private Money applyAccoutingNature(Money value) {
		if (nature.isNegative()) {
			return value.negate();
		}
		return value;
	}

	public SortedSet<? extends IAggregatableStructure> getStructuralChildren() {
		return account.getStructuralChildren();
	}

}
