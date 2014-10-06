/*
 * Copyright (c) Banco Central do Brasil.
 *
 * Este software é confidencial e propriedade do Banco Central do Brasil.
 * Não é permitida sua distribuição ou divulgação do seu conteúdo sem
 * expressa autorização do Banco Central.
 * Este arquivo contém informações proprietárias.
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
	 * Converte uma string para BigDecimal, usando o formato de números usado no
	 * Brasil (isto é, usando vírgula para separar as casas decimais).
	 */
	public static BigDecimal parse(String valor) {
		try {
			return (BigDecimal) DECIMAL_FORMAT.parse(valor);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Formato/tamanho inválido!", e);
		}
	}

	/**
	 * Converte o BigDecimal para String, usando o formato de números usado no
	 * Brasil (isto é, usando vírgula para separar as casas decimais).
	 */
	public static String format(BigDecimal valor) {
		return DECIMAL_FORMAT.format(valor);
	}
}
