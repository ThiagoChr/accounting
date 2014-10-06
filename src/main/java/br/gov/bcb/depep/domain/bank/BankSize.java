package br.gov.bcb.depep.domain.bank;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Preconditions;

public enum BankSize {

	NO_INFO("0", "No info"), //

	MEGA("1", "Mega"), //
	BIG("2", "Big"), //
	MEDIUM("3", "Medium"), //
	SMALL("4", "Small");

	private String code;
	private String description;

	private BankSize(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static BankSize transform(String bankSize) {

		Preconditions.checkArgument(StringUtils.isNotBlank(bankSize),
				"The bank's size must be informed in the transformation!");

		for (BankSize enumType : BankSize.values()) {

			if (bankSize.equals(enumType.getCode())) {
				return enumType;
			}

		}

		throw new IllegalStateException("The bank's size " + bankSize
				+ " has not been given a transformation! Please map it!");

	}

}
