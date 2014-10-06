package br.gov.bcb.depep.domain.bank;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Preconditions;

public enum BankControlType {

	NO_INFO("0", "No info"), //

	CONTROL_0("0", "Control 0"), //
	CONTROL_1("1", "Control 1"), //
	CONTROL_2("2", "Control 2"), //
	CONTROL_3("3", "Control 3"), //
	CONTROL_4("4", "Control 4"), //
	CONTROL_5("5", "Control 5");

	private String code;
	private String description;

	private BankControlType(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static BankControlType transform(String controlType) {

		Preconditions
				.checkArgument(StringUtils.isNotBlank(controlType),
						"The bank's control type must be informed in the transformation!");

		for (BankControlType enumType : BankControlType.values()) {

			if (controlType.equals(enumType.getCode())) {
				return enumType;
			}

		}

		throw new IllegalStateException("The bank's control type "
				+ controlType
				+ " has not been given a transformation! Please map it!");
	}

}
