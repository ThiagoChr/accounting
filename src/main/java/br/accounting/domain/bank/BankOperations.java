package br.accounting.domain.bank;

import java.util.Collection;
import java.util.Map;

import com.google.common.collect.Maps;

public class BankOperations {

	private BankOperations() {
		// service class
	}

	public static Map<String, Bank> indexByCNPJ(Collection<Bank> banks) {

		Map<String, Bank> indexedMap = Maps.newHashMap();

		for (Bank bank : banks) {
			indexedMap.put(bank.getCnpj(), bank);
		}

		return indexedMap;
	}
}
