package br.gov.bcb.depep.domain.accoutingtools;

public enum AccountName {

	NO_NAME("Nameless account"), //
	CASH("Cash"), //
	SHORT_TERM_LOANS("Short-term loans"), //
	LONG_TERM_LOANS("Long-term loans"), //
	SECURITIES("Securities"), //
	OTHER_ASSETS("Other non-modeled assets"), //
	ASSETS("Assets"), //

	DEPOSITS("Deposits"), //
	SHORT_TERM_BORROWING("Short-term borrowing"), //
	LONG_TERM_BORROWING("Long-term borrowing"), //
	OTHER_LIABILITIES("Other liabilities"), //
	LIABILITIES("Liabilities"), //

	OWNER_EQUITY("Owner's equity"), //
	EQUITIES("Equities"), //
	
	BALANCE_SHEET("Bank's balance sheet");

	private String description;

	private AccountName(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
