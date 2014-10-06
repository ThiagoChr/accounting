/*
 * Copyright (c) Banco Central do Brasil.
 *
 * Este software � confidencial e propriedade do Banco Central do Brasil.
 * N�o � permitida sua distribui��o ou divulga��o do seu conte�do sem
 * expressa autoriza��o do Banco Central.
 * Este arquivo cont�m informa��es propriet�rias.
 */
package br.gov.bcb.depep.domain.util.money;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

import br.gov.bcb.depep.domain.util.date.DataUtil;

public class BigDecimalBR {

	private static final DecimalFormat DECIMAL_FORMAT;

	static {
		DECIMAL_FORMAT = new DecimalFormat("#,##0.00",
				new DecimalFormatSymbols(DataUtil.LOCALE));
		DECIMAL_FORMAT.setParseBigDecimal(true);
	}

	/**
	 * Converte uma string para BigDecimal, usando o formato de n�meros usado no
	 * Brasil (isto �, usando v�rgula para separar as casas decimais).
	 */
	public static BigDecimal parse(String valor) {
		try {
			return (BigDecimal) DECIMAL_FORMAT.parse(valor);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Formato/tamanho inv�lido!", e);
		}
	}

	/**
	 * Converte o BigDecimal para String, usando o formato de n�meros usado no
	 * Brasil (isto �, usando v�rgula para separar as casas decimais).
	 */
	public static String format(BigDecimal valor) {
		return DECIMAL_FORMAT.format(valor);
	}
}
