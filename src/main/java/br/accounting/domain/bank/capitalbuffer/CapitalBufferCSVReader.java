package br.accounting.domain.bank.capitalbuffer;

import java.util.Map;
import java.util.Map.Entry;

import org.joda.time.YearMonth;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

import br.accounting.domain.bank.Bank;
import br.accounting.domain.util.money.Money;
import br.accounting.domain.util.reader.CSVFileReader;

import com.google.common.base.Preconditions;

public class CapitalBufferCSVReader extends CSVFileReader<Void> {

	private static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder()
			.appendPattern("MM-yyyy").toFormatter();

	private static final String CNPJ = "IF";

	private Map<String, Bank> indexedBanks;

	public CapitalBufferCSVReader(String filePath,
			Map<String, Bank> indexedBanks) {
		super(filePath);
		this.indexedBanks = indexedBanks;
	}

	@Override
	protected Void buildUpObject(String[] record, Map<String, Integer> header) {

		String cnpj = record[header.get(CNPJ)];

		Bank bank = indexedBanks.get(cnpj);

		Preconditions.checkNotNull(bank, "The bank " + cnpj
				+ " has not been registered yet!");

		for (Entry<String, Integer> entry : header.entrySet()) {

			String unprocessedDate = entry.getKey();
			Integer tokenPosition = entry.getValue();

			if (tokenPosition < 1) {
				continue; // the first position (0) is always the IF's CNPJ
			}

			YearMonth processedDate = YearMonth.parse(unprocessedDate,
					FORMATTER);

			String unprocessedMoney = record[tokenPosition];

			bank.addCapitalBufferByDate(processedDate, new Money(
					new java.math.BigDecimal(unprocessedMoney)));

		}

		return null;

	}
}
