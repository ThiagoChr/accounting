package br.accounting.domain.operators;

public enum AccountingNature {

	POSITIVE("Positive"), //
	NEGATIVE("Negative");

	private String description;

	private AccountingNature(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public boolean isNegative() {
		return this.equals(NEGATIVE);
	}

}
