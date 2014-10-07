package br.accounting.domain.bank;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.accounting.domain.bank.capitalbuffer.CapitalBufferCSVReader;
import br.accounting.domain.bank.totalassets.TotalAssetsCSVReader;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class BanksCreator {

	private static final String BANK_DESCRIPTION_IN_2008_PATH = "D:/legendIn2008.csv";
	private static final String BANK_DESCRIPTION_AFTER_2008_PATH = "D:/legendAfter2008.csv";

	private static final String TOTAL_ASSETS_IN_2008_PATH = "D:/totalAssetsAfter2008.csv";
	private static final String TOTAL_ASSETS_AFTER_2008_PATH = "D:/totalAssetsIn2008.csv";

	private static final String CAPITAL_BUFFER_IN_2008_PATH = "D:/capitalBufferAfter2008.csv";
	private static final String CAPITAL_BUFFER_AFTER_2008_PATH = "D:/capitalBufferIn2008.csv";

	private static final List<String> BANK_DESCRIPTION_PATHS = Lists
			.newArrayList(BANK_DESCRIPTION_IN_2008_PATH,
					BANK_DESCRIPTION_AFTER_2008_PATH);

	private static final List<String> TOTAL_ASSETS_PATHS = Lists.newArrayList(
			TOTAL_ASSETS_IN_2008_PATH, TOTAL_ASSETS_AFTER_2008_PATH);

	private static final List<String> CAPITAL_BUFFER_PATHS = Lists
			.newArrayList(CAPITAL_BUFFER_IN_2008_PATH,
					CAPITAL_BUFFER_AFTER_2008_PATH);

	private BanksCreator() {
		// service class
	}

	public static Map<String, Bank> createBanks() throws IOException {

		Set<Bank> banksSet = generateBanks();

		Map<String, Bank> indexedBanks = BankOperations.indexByCNPJ(banksSet);

		updateTotalAssets(indexedBanks);

		updateCapitalBuffer(indexedBanks);

		return indexedBanks;

	}

	private static Set<Bank> generateBanks() throws IOException {

		Set<Bank> banksSet = Sets.newHashSet();

		for (String bankDescriptionPath : BANK_DESCRIPTION_PATHS) {

			BankDescriptionCSVReader reader = new BankDescriptionCSVReader(
					bankDescriptionPath);

			List<Bank> banksList = reader.parseCSV();

			banksSet.addAll(banksList);

		}
		return banksSet;
	}

	private static void updateCapitalBuffer(Map<String, Bank> indexedBanks)
			throws IOException {

		for (String capitalBufferPath : CAPITAL_BUFFER_PATHS) {

			CapitalBufferCSVReader capitalBufferReader = new CapitalBufferCSVReader(
					capitalBufferPath, indexedBanks);

			capitalBufferReader.parseCSV();
		}

	}

	private static void updateTotalAssets(Map<String, Bank> indexedBanks)
			throws IOException {

		for (String totalAssetsPath : TOTAL_ASSETS_PATHS) {

			TotalAssetsCSVReader assetsReader = new TotalAssetsCSVReader(
					totalAssetsPath, indexedBanks);

			assetsReader.parseCSV();
		}
	}
}
