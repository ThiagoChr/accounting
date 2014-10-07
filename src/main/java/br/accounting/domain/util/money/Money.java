/*
 * Copyright (c) Banco Central do Brasil.
 *
 * Este software é confidencial e propriedade do Banco Central do Brasil.
 * Não é permitida sua distribuição ou divulgação do seu conteúdo sem
 * expressa autorização do Banco Central.
 * Este arquivo contém informações proprietárias.
 */

package br.accounting.domain.util.money;

import java.beans.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

import org.apache.commons.lang3.StringUtils;

import br.accounting.domain.util.date.DataUtil;

import com.google.common.base.Preconditions;

public class Money implements Comparable<Money>, Serializable {

    public static final Money ZERO = new Money(BigDecimal.ZERO);
    public static final Money ONE = new Money(BigDecimal.ONE);
    public static final Money TEN = new Money(BigDecimal.TEN);
    public static final int TAMANHO_MAXIMO = 33; // Tamanho máximo do campo no
                                                 // DB2
    public static final int SCALE = 2;
    public static final int PRECISION = TAMANHO_MAXIMO - SCALE;
    public static final String COLUMN_DEFINITION = "decimal(" + PRECISION + ","
            + SCALE + ")";
    private static final MathContext MATH_CONTEXT;
    private static final DecimalFormat DECIMAL_FORMAT;
    private static final String REGEX_TAMANHO_MAXIMO = "^[-]?[0-9]{1,3}([.]?[0-9]{3}){0,9}[,]{1}[0-9]{1,16}$";

    private BigDecimal bigDecimal;

    static {
        DECIMAL_FORMAT = new DecimalFormat("#,##0.00",
                new DecimalFormatSymbols(DataUtil.LOCALE));
        DECIMAL_FORMAT.setParseBigDecimal(true);
        MATH_CONTEXT = new MathContext(PRECISION, RoundingMode.HALF_UP);
    }

    public Money() {
        // default
    }

    public Money(BigDecimal value) {
        Preconditions.checkNotNull(value, "Valor não pode ser nulo!");
        bigDecimal = new BigDecimal(value.toString(),
                new MathContext(PRECISION));
        bigDecimal = bigDecimal.setScale(SCALE, RoundingMode.HALF_UP);
    }

    /**
     * O tamanho máximo do valor permitido no DB2 é
     * 123456789012345,1234567890123456, ou seja, 15 de magnitude e 16 de
     * precisão.
     * 
     * @param value
     *            - Valor monetário em Reais (R$). Ex.: 1.000,00
     */
    public static Money newMoney(String value) throws IllegalArgumentException {
        try {
            if (StringUtils.isBlank(value)) {
                throw new ParseException(StringUtils.EMPTY, 0);
            }
            if (!value.trim().matches(REGEX_TAMANHO_MAXIMO)) {
                throw new ParseException(StringUtils.EMPTY, 0);
            }
            return new Money((BigDecimal) DECIMAL_FORMAT.parse(value));
        } catch (ParseException e) {
            throw new IllegalArgumentException(
                    "Formato/tamanho inválido! [Money]", e);
        }
    }

    public static Money newRawMoney(String value)
            throws IllegalArgumentException {
        try {
            if (StringUtils.isBlank(value)) {
                throw new ParseException(StringUtils.EMPTY, 0);
            }
            return new Money(new BigDecimal(value));
        } catch (ParseException e) {
            throw new IllegalArgumentException(
                    "Formato/tamanho inválido! [Money]", e);
        }
    }

    public final Money add(Money augment) {
        return new Money(bigDecimal.add(augment.bigDecimal, MATH_CONTEXT));
    }

    public final Money subtract(Money subtract) {
        return new Money(bigDecimal.subtract(subtract.bigDecimal, MATH_CONTEXT));
    }

    public final Money divide(Money divisor) {
        return new Money(bigDecimal.divide(divisor.bigDecimal, MATH_CONTEXT));
    }

    public final Money multiply(Money multiplicand) {
        return new Money(bigDecimal.multiply(multiplicand.bigDecimal,
                MATH_CONTEXT));
    }

    public final Money negate() {
        return new Money(this.bigDecimal.negate());
    }

    public final Money round() {
        return new Money(this.bigDecimal.round(MATH_CONTEXT));
    }

    public final Money pow(Integer exponent) {
        return new Money(this.bigDecimal.pow(exponent, MATH_CONTEXT));
    }

    public final Money abs() {
        return new Money(this.bigDecimal.abs());
    }

    public final boolean isGreaterThan(Money otherNumber) {
        return this.compareTo(otherNumber) > 0;
    }

    public final boolean isLowerThan(Money otherNumber) {
        return this.compareTo(otherNumber) < 0;
    }

    @Override
    public int compareTo(Money other) {
        return this.bigDecimal.compareTo(other.bigDecimal);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (!(other instanceof Money)) {
            return false;
        }
        return this.compareTo((Money) other) == 0;
    }

    @Override
    public int hashCode() {
        return this.bigDecimal.hashCode();
    }

    @Override
    public String toString() {
        return DECIMAL_FORMAT.format(this.bigDecimal);
    }

    @Transient
    public boolean isNegative() {
        return this.bigDecimal.signum() == -1;
    }

    @Transient
    public boolean isPositive() {
        return this.bigDecimal.signum() == 1;
    }

    @Transient
    public boolean isZero() {
        return this.bigDecimal.signum() == 0;
    }

    public BigDecimal getValor() {
        return this.bigDecimal;
    }
}
