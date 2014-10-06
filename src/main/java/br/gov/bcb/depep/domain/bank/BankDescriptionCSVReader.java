package br.gov.bcb.depep.domain.bank;

import java.util.Map;

import br.gov.bcb.depep.domain.util.reader.CSVFileReader;

public class BankDescriptionCSVReader extends CSVFileReader<Bank> {

	private static final String SIZE = "Porte";
	private static final String MACRO_SEGMENT = "Macrosseg";
	private static final String CONTROL_TYPE = "Controle";
	private static final String CNPJ = "IF";
	private static final String NAME = "Nome_IF";

	public BankDescriptionCSVReader(String filePath) {
		super(filePath);
	}

	@Override
	protected Bank buildUpObject(String[] record, Map<String, Integer> header) {

		Bank bank = new Bank();

		bank.setCnpj(record[header.get(CNPJ)]);
		bank.setName(record[header.get(NAME)]);
		bank.setSize(record[header.get(SIZE)]);
		bank.setMacroSegment(record[header.get(MACRO_SEGMENT)]);
		bank.setControlType(record[header.get(CONTROL_TYPE)]);

		return bank;
	}

}
