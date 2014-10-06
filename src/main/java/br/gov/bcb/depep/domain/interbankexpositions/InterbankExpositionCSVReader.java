package br.gov.bcb.depep.domain.interbankexpositions;

import java.util.Map;

import br.gov.bcb.depep.domain.bank.Bank;
import br.gov.bcb.depep.domain.util.reader.CSVFileReader;

public class InterbankExpositionCSVReader extends
		CSVFileReader<InterbankExposition> {

	private static final String ANO_MES = "AnoMes";
	private static final String VALOR = "Valor";
	private static final String CREDITOR = "Credor";
	private static final String DEBTOR = "Devedor";

	private Map<String, Bank> indexedBanks;

	public InterbankExpositionCSVReader(Map<String, Bank> indexedBanks,
			String filePath) {
		super(filePath);
		this.indexedBanks = indexedBanks;
	}

	@Override
	protected InterbankExposition buildUpObject(String[] record,
			Map<String, Integer> header) {

		InterbankExposition interbankExposition = new InterbankExposition();

		interbankExposition.setYearMonth(record[header.get(ANO_MES)]);
		interbankExposition.setCreditor(indexedBanks.get(record[header
				.get(CREDITOR)]));
		interbankExposition.setDebtor(indexedBanks.get(record[header
				.get(DEBTOR)]));
		interbankExposition.setValue(record[header.get(VALOR)]);

		return interbankExposition;
	}

}
