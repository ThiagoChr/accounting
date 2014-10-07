package br.accounting.domain.interbankexpositions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import br.accounting.domain.bank.Bank;

public class InterbankExpositionsCreator {

	private static final String INTERBANK_EXPOSITIONS_PATH = "D:/dataInsolvency.csv";

	private InterbankExpositionsCreator() {
		// service class
	}

	public static List<InterbankExposition> createLinks(
			Map<String, Bank> indexedBanks) throws IOException {

		InterbankExpositionCSVReader interbankExpositionCSV = new InterbankExpositionCSVReader(
				indexedBanks, INTERBANK_EXPOSITIONS_PATH);

		return interbankExpositionCSV.parseCSV();
	}

}
