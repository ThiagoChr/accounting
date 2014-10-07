package br.accounting.domain.operators;

import java.util.SortedSet;

import org.joda.time.LocalDate;

import br.accounting.domain.util.money.Money;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

public class CompositeAccount extends Account {

    private final SortedSet<AccountWithNatureDecorator> descendentAccounts = Sets
            .newTreeSet();

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

        AccountWithNatureDecorator accountWithNature = new AccountWithNatureDecorator(
                newDescendent, nature);

        Preconditions.checkState(descendentAccounts.add(accountWithNature),
                "Could not add the descendent " + newDescendent + "!");
    }

    @Override
    public Money getBalance() {

        Money total = Money.ZERO;

        for (Account account : descendentAccounts) {
            total = total.add(account.getBalance());
        }

        return total;
    }

    @Override
    public Money getBalanceUntilDay(LocalDate referenceDate) {

        Money total = Money.ZERO;

        for (Account account : descendentAccounts) {
            total = total.add(account.getBalanceUntilDay(referenceDate));
        }

        return total;
    }

    @Override
    public SortedSet<AccountWithNatureDecorator> getStructuralChildren() {
        return descendentAccounts;
    }

}
