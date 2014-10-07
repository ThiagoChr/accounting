package br.accounting.domain.operators;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.joda.time.LocalDate;

import br.accounting.domain.util.keygenerator.UUIDUtil;
import br.accounting.domain.util.money.Money;

import com.google.common.base.Preconditions;

public class AccountingEntry implements Comparable<AccountingEntry> {

    private final Money value;
    private final LocalDate dhValue;

    private final Long identifier;

    AccountingEntry(Money value, LocalDate dhValue) {
        this.value = Preconditions.checkNotNull(value);
        this.dhValue = Preconditions.checkNotNull(dhValue);

        this.identifier = UUIDUtil.gerarLongUID();
    }

    public Money getValue() {
        return value;
    }

    public LocalDate getDhValue() {
        return dhValue;
    }

    @Override
    public boolean equals(Object obj) {

        if (!(AccountingEntry.class.isInstance(obj))) {
            return false;
        }

        AccountingEntry other = AccountingEntry.class.cast(obj);

        return this.identifier.equals(other.identifier);
    }

    @Override
    public int hashCode() {
        return this.identifier.hashCode();
    }

    @Override
    public int compareTo(AccountingEntry o) {

        CompareToBuilder ctb = new CompareToBuilder();

        ctb.append(this.dhValue, o.dhValue);
        ctb.append(this.value, o.value);
        ctb.append(this.identifier, o.identifier);

        return ctb.toComparison();
    }

    @Override
    public String toString() {
        return "$ " + getValue();
    }

    public boolean isNotAfter(LocalDate referenceDate) {
        return !getDhValue().isAfter(referenceDate);
    }

}
