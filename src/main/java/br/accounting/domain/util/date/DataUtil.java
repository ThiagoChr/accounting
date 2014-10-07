/*
 * Copyright (c) Banco Central do Brasil.
 *
 * Este software é confidencial e propriedade do Banco Central do Brasil.
 * Não é permitida sua distribuição ou divulgação do seu conteúdo sem
 * expressa autorização do Banco Central.
 * Este arquivo contém informações proprietárias.
 */
package br.accounting.domain.util.date;

import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.YearMonth;
import org.joda.time.base.AbstractPartial;
import org.joda.time.base.BaseLocal;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

public class DataUtil {

	private static final DateTimeFormatter YEAR_MONTH_FORMATTER = new DateTimeFormatterBuilder()
			.appendPattern("yyyyMM").toFormatter();
	public static final String DATE_PATTERN_EXTENSO = "dd 'de' MMMM 'de' YYYY";
	public static final Locale LOCALE = new Locale("pt", "BR");
	public static final DateTimeZone ZONE = DateTimeZone
			.forID("America/Sao_Paulo");
	public static final int DIAS_MES_COMERCIAL = 30;
	public static final String DATE_PATTERN = "dd/MM/yyyy";
	public static final String DATE_PATTERN_MES_ANO = "MM/yyyy";
	public static final String DATE_PATTERN_MES_TEXTO_ANO = "MMM/yy";
	public static final String DATE_PATTERN_MES_TEXTO_COMPLETO_ANO = "MMMM/yyyy";
	public static final String DATE_TIME_PATTERN = "dd/MM/yyyy HH:mm";
	public static final String DATE_TIME_PATTERN_NUMERICO = "yyyyMMddHHmmss";

	private static final LocalTime ULTIMO_INSTANTE_DIA = new LocalTime(23, 59,
			59, 999);

	public static Date toDateNoInicio(LocalDate data) {
		return data.toDateTimeAtStartOfDay().toDate();
	}

	public static Date toDateNoFim(LocalDate data) {
		return data.toDateTime(ULTIMO_INSTANTE_DIA).toDate();
	}

	public static Date toDate(LocalDateTime data) {
		return data.toDateTime().toDate();
	}

	public static LocalDate toLocalDate(Date data) {
		return LocalDate.fromDateFields(data);
	}

	public static LocalDate toLocalDate(String dataString) {
		return toLocalDate(DATE_PATTERN, dataString);
	}

	public static LocalDate toLocalDate(String pattern, String dataString) {
		return LocalDate
				.parse(dataString.trim(), getDateTimeFormatter(pattern));
	}

	public static LocalDateTime toLocalDateTime(Date data) {
		return LocalDateTime.fromDateFields(data);
	}

	public static LocalDateTime toLocalDateTimeNoInicio(LocalDate data) {
		return data.toDateTimeAtStartOfDay().toLocalDateTime();
	}

	public static LocalDateTime toLocalDateTimeNoFim(LocalDate data) {
		return data.toDateTime(ULTIMO_INSTANTE_DIA).toLocalDateTime();
	}

	public static LocalDateTime toLocalDateTime(String dataHoraString) {
		return getDateTimeFormatter(DATE_TIME_PATTERN).parseDateTime(
				dataHoraString).toLocalDateTime();
	}

	public static java.sql.Date toSqlDateNoInicio(LocalDate data) {
		return toSqlDate(toDateNoInicio(data));
	}

	public static java.sql.Date toSqlDateNoFim(LocalDate data) {
		return toSqlDate(toDateNoFim(data));
	}

	public static java.sql.Date toSqlDate(Date data) {
		return new java.sql.Date(data.getTime());
	}

	public static String toString(Date data) {
		return toString(toLocalDate(data));
	}

	public static String toString(LocalDate data) {
		return toString(data, getDateTimeFormatter(DATE_PATTERN));
	}

	public static String toString(YearMonth referenceDate) {
		return referenceDate.toString(YEAR_MONTH_FORMATTER);
	}

	public static String toString(LocalDateTime dataHora) {
		if (dataHora == null) {
			return "";
		}
		return toString(dataHora, getDateTimeFormatter(DATE_TIME_PATTERN));
	}

	public static String toStringNumerica(LocalDateTime dataHora) {
		return toString(dataHora,
				getDateTimeFormatter(DATE_TIME_PATTERN_NUMERICO));
	}

	public static String toMesAno(LocalDate dataHora) {
		return toString(dataHora, getDateTimeFormatter(DATE_PATTERN_MES_ANO));
	}

	public static String toStringFull(LocalDateTime dataHora) {
		return toString(dataHora, DateTimeFormat.fullDateTime().withZone(ZONE)
				.withLocale(LOCALE));
	}

	private static String toString(BaseLocal baseLocal,
			DateTimeFormatter formatter) {
		return baseLocal.toString(formatter);
	}

	public static String diaDaSemana(LocalDate data) {
		return data.dayOfWeek().getAsText(LOCALE).toLowerCase(LOCALE);
	}

	private static DateTimeFormatter getDateTimeFormatter(String formato) {
		return DateTimeFormat.forPattern(formato).withZone(ZONE)
				.withLocale(LOCALE);
	}

	public static int diasAte(BaseLocal diaComparado, BaseLocal diaBase) {
		return Days.daysBetween(diaComparado, diaBase).getDays();
	}

	public static boolean possuiIntersecao(LocalDate dataInicioPesquisa,
			LocalDate dataFimPesquisa, LocalDate dataInicioBase,
			LocalDate dataFimBase) {
		return getIntervalo(dataInicioPesquisa, dataFimPesquisa).overlaps(
				getIntervalo(dataInicioBase, dataFimBase));
	}

	public static Interval getIntervalo(LocalDate inicio, LocalDate fim) {
		return new Interval(inicio.toDateTimeAtStartOfDay(),
				fim.toDateTime(ULTIMO_INSTANTE_DIA));
	}

	public static LocalDate getMesAnterior(LocalDate data) {
		return data.minusMonths(1);
	}

	public static LocalDate primeiroDiaDoAno(LocalDate data) {
		return data.withMonthOfYear(1).withDayOfMonth(1);
	}

	public static LocalDate primeiroDiaDoMesSeguinte(LocalDate data) {
		return data.withDayOfMonth(1).plusMonths(1);
	}

	public static LocalDate primeiroDiaDoMesSeguinte(LocalDateTime data) {
		return primeiroDiaDoMesSeguinte(data.toLocalDate());
	}

	public static LocalDate getMesSeguinteComUltimoDia(LocalDate data) {
		return ultimoDiaDoMes(data.plusMonths(1));
	}

	public static <T extends AbstractPartial> T min(T dataA, T dataB) {
		if (dataA.isBefore(dataB)) {
			return dataA;
		}
		return dataB;
	}

	public static <T extends AbstractPartial> T max(T dataA, T dataB) {
		if (dataA.isAfter(dataB)) {
			return dataA;
		}
		return dataB;
	}

	public static boolean primeiraDataPosteriorASegundaData(
			LocalDate primeiraData, LocalDate segundaData) {
		return primeiraData.isAfter(segundaData);
	}

	public static LocalDate ultimoDiaDoMes(LocalDate data) {
		return data.withDayOfMonth(data.dayOfMonth().getMaximumValue());
	}

	public static LocalDate primeiroDiaDoMes(LocalDate data) {
		return data.withDayOfMonth(1);
	}

	public static LocalDate primeiroDiaDoMes(LocalDateTime data) {
		return primeiroDiaDoMes(data.toLocalDate());
	}

	public static boolean mesmoMes(LocalDate data1, LocalDate data2) {
		if ((data1 == null) || (data2 == null)) {
			return false;
		}
		boolean mesmoAno = (data1.getYear() == data2.getYear());
		boolean mesmoMes = (data1.getMonthOfYear() == data2.getMonthOfYear());
		return mesmoAno && mesmoMes;
	}

	public static boolean mesmoMes(LocalDateTime data1, LocalDateTime data2) {
		if ((data1 == null) || (data2 == null)) {
			return false;
		}
		boolean mesmoAno = (data1.getYear() == data2.getYear());
		boolean mesmoMes = (data1.getMonthOfYear() == data2.getMonthOfYear());
		return mesmoAno && mesmoMes;
	}

	public static boolean temMesmoDiaDoMes(LocalDate data1, LocalDate data2) {
		if ((data1 == null) || (data2 == null)) {
			return false;
		}
		return data1.getDayOfMonth() == data2.getDayOfMonth();
	}

	public static boolean temMesmoDiaDoMes(LocalDate data1, LocalDate data2,
			int diaDoMes) {
		return temMesmoDiaDoMes(data1, data2)
				&& (data1.getDayOfMonth() == diaDoMes);
	}

	public static String toMesAnoComMesTextoEAnoAbreviados(LocalDate dataHora) {
		return toString(dataHora,
				getDateTimeFormatter(DATE_PATTERN_MES_TEXTO_ANO));
	}

	public static String toMesAnoComMesTextoEAno(LocalDate dataHora) {
		return toString(dataHora,
				getDateTimeFormatter(DATE_PATTERN_MES_TEXTO_COMPLETO_ANO));
	}

	public static String toDataPorExtenso(LocalDate data) {
		return toString(data, getDateTimeFormatter(DATE_PATTERN_EXTENSO));
	}

	public static LocalDateTime primeiroSegundoDoMes(LocalDateTime dhRegistro) {
		return dhRegistro.withDayOfMonth(1).withMillisOfDay(1);
	}

}
