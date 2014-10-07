package br.accounting.domain.operators;

import java.util.SortedSet;

import org.joda.time.LocalDate;

import br.accounting.domain.util.money.Money;
import br.accounting.domain.util.visualization.IAggregatableStructure;

import com.google.common.base.Preconditions;

public class AccountWithNature extends Account {

    private final Account account;
    private final AccountingNature nature;

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

    @Override
    public SortedSet<? extends IAggregatableStructure> getStructuralChildren() {
        return account.getStructuralChildren();
    }

}
