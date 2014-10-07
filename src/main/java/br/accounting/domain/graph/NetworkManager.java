package br.accounting.domain.graph;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

import org.joda.time.YearMonth;

import br.accounting.domain.bank.Bank;
import br.accounting.domain.bank.BanksCreator;
import br.accounting.domain.interbankexpositions.InterbankExposition;
import br.accounting.domain.interbankexpositions.InterbankExpositionOperations;
import br.accounting.domain.interbankexpositions.InterbankExpositionsCreator;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedSet;

public class NetworkManager {

	private Map<String, Bank> indexedBanks;

	private List<InterbankExposition> expositionList;

	private SortedSet<YearMonth> dates;

	public NetworkManager() throws IOException {

		// VERTICES ...
		indexedBanks = BanksCreator.createBanks();

		// LINKS...
		expositionList = InterbankExpositionsCreator.createLinks(indexedBanks);

		dates = InterbankExpositionOperations.extractAllDates(expositionList);
	}

	public NetworkInterbankExpositionDecorator createExpositionNetworkForDate(
			YearMonth referenceDate) {

		verifyValidityOfDate(referenceDate);

		List<InterbankExposition> filteredExpositions = InterbankExpositionOperations
				.filterByDate(expositionList, referenceDate);

		GraphFormation<Bank, InterbankExposition> graphCreator = new GraphFormation<Bank, InterbankExposition>(
				filteredExpositions);

		return new NetworkInterbankExpositionDecorator(
				graphCreator.createNetwork());
	}

	private void verifyValidityOfDate(YearMonth referenceDate) {

		Preconditions.checkArgument(dates.contains(referenceDate),
				"The reference date " + referenceDate
						+ " has no interbank expositions registered!");

	}

	public Map<String, Bank> getIndexedBanks() {
		return ImmutableMap.copyOf(indexedBanks);
	}

	public SortedSet<YearMonth> getDates() {
		return ImmutableSortedSet.copyOf(dates);
	}
}
