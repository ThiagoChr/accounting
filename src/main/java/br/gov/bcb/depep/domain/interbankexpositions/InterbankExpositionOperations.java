package br.gov.bcb.depep.domain.interbankexpositions;

import java.util.Collection;
import java.util.List;
import java.util.SortedSet;

import org.joda.time.YearMonth;

import br.gov.bcb.depep.domain.util.money.Money;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class InterbankExpositionOperations {

	private InterbankExpositionOperations() {
		// service class
	}

	public static List<InterbankExposition> filterByDate(
			List<InterbankExposition> unfilteredList,
			final YearMonth filteringDate) {

		return Lists.newArrayList(Collections2.filter(unfilteredList,
				new Predicate<InterbankExposition>() {

					public boolean apply(InterbankExposition input) {
						return input.getYearMonth().equals(filteringDate);
					}
				}));

	}

	public static SortedSet<YearMonth> extractAllDates(
			List<InterbankExposition> unfilteredList) {

		return Sets.newTreeSet(Collections2.transform(unfilteredList,
				new Function<InterbankExposition, YearMonth>() {
					public YearMonth apply(InterbankExposition input) {
						return input.getYearMonth();

					};
				}));

	}

	public static Money sum(Collection<InterbankExposition> list) {

		Money totalAssets = Money.ZERO;

		for (InterbankExposition edge : list) {

			totalAssets = totalAssets.add(edge.getValue());
		}

		return totalAssets;
	}
}
