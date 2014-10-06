package br.gov.bcb.depep.domain.util.money;

import java.util.Iterator;

public class MoneyOperations {

	private MoneyOperations() {
		// service class
	}

	public static Money max(Money a, Money b) {
		if (a.isGreaterThan(b)) {
			return a;
		}
		return b;
	}

	public static Money min(Money a, Money b) {
		if (a.isLowerThan(b)) {
			return a;
		}
		return b;
	}

	public static <T> Money sum(Iterable<T> collection,
			MoneyFunction<T> function) {
		Money total = Money.ZERO;
		Iterator<T> i = collection.iterator();
		while (i.hasNext()) {
			T item = i.next();
			total = total.add(function.getOperand(item));
		}
		return total;
	}

}
