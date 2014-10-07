package br.accounting.domain.interbankexpositions;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.joda.time.YearMonth;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

import br.accounting.domain.bank.Bank;
import br.accounting.domain.graph.IEdge;
import br.accounting.domain.graph.IVertex;
import br.accounting.domain.util.money.Money;

public class InterbankExposition implements IEdge {

	private static final DateTimeFormatter YEAR_MONTH_FORMATTER = new DateTimeFormatterBuilder()
			.appendPattern("YYYYMM").toFormatter();

	private YearMonth yearMonth;
	private Bank creditor;
	private Bank debtor;
	private Money value;

	public YearMonth getYearMonth() {
		return yearMonth;
	}

	protected void setYearMonth(String yearMonth) {
		this.yearMonth = YearMonth.parse(yearMonth, YEAR_MONTH_FORMATTER);
	}

	public IVertex getCreditor() {
		return creditor;
	}

	protected void setCreditor(Bank creditor) {
		this.creditor = creditor;
	}

	public IVertex getDebtor() {
		return debtor;
	}

	protected void setDebtor(Bank debtor) {
		this.debtor = debtor;
	}

	public Money getValue() {
		return value;
	}

	protected void setValue(String value) {
		this.value = Money.newRawMoney(value);
	}

	@Override
	public String toString() {
		return "(" + creditor + ", " + debtor + "): value=" + value;
	}

	@Override
	public boolean equals(Object obj) {

		if (!InterbankExposition.class.isInstance(obj)) {
			return false;
		}

		InterbankExposition other = InterbankExposition.class.cast(obj);

		EqualsBuilder eb = new EqualsBuilder();

		eb.append(this.creditor, other.getCreditor());
		eb.append(this.debtor, other.getDebtor());
		eb.append(this.value, other.getValue());
		eb.append(this.yearMonth, other.getYearMonth());

		return eb.isEquals();
	}

	@Override
	public int hashCode() {

		HashCodeBuilder hcb = new HashCodeBuilder();

		hcb.append(this.creditor);
		hcb.append(this.debtor);
		hcb.append(this.value);
		hcb.append(this.yearMonth);

		return hcb.toHashCode();
	}

	public IVertex getSourceVertex() {
		return debtor;
	}

	public IVertex getDestinyVertex() {
		return creditor;
	}

	public Money getWeight() {
		return getValue();
	}

}
