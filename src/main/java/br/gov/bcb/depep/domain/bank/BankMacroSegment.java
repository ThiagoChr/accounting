package br.gov.bcb.depep.domain.bank;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Preconditions;

public enum BankMacroSegment {

	NO_INFO("0", "No info"), //

	MS_1("1", "Macro-segment 1"), //
	MS_2("2", "Macro-segment 2"), //
	MS_3("3", "Macro-segment 3"), //
	MS_4("4", "Macro-segment 4"), //
	MS_5("5", "Macro-segment 5"), //
	MS_6("6", "Macro-segment 6"), //
	MS_7("7", "Macro-segment 7");

	private String code;
	private String description;

	private BankMacroSegment(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public boolean isBankingSegment() {
		return Arrays.asList(MS_1, MS_2, MS_4).contains(this);
	}

	public boolean isNonBankingSegment() {
		return !isBankingSegment();
	}

	public static BankMacroSegment transform(String macroSegment) {

		Preconditions
				.checkArgument(StringUtils.isNotBlank(macroSegment),
						"The bank's macro-segment must be informed in the transformation!");

		for (BankMacroSegment enumType : BankMacroSegment.values()) {

			if (macroSegment.equals(enumType.getCode())) {
				return enumType;
			}

		}

		throw new IllegalStateException("The bank's macro-segment "
				+ macroSegment
				+ " has not been given a transformation! Please map it!");

	}

}
