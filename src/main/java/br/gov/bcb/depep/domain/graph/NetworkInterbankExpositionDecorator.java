package br.gov.bcb.depep.domain.graph;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import br.gov.bcb.depep.domain.bank.Bank;
import br.gov.bcb.depep.domain.bank.BankOperations;
import br.gov.bcb.depep.domain.interbankexpositions.InterbankExposition;
import br.gov.bcb.depep.domain.interbankexpositions.InterbankExpositionOperations;
import br.gov.bcb.depep.domain.util.money.Money;

import com.google.common.base.Preconditions;

import edu.uci.ics.jung.graph.Graph;

public class NetworkInterbankExpositionDecorator {

	private Graph<Bank, InterbankExposition> graph;

	private Map<String, Bank> indexedBanks; // for efficiency

	public NetworkInterbankExpositionDecorator(
			Graph<Bank, InterbankExposition> graph) {

		this.graph = Preconditions.checkNotNull(graph,
				"The graph to be decorated cannot be null!");

		this.indexedBanks = BankOperations.indexByCNPJ(graph.getVertices());

	}

	/**
	 * In the rows there are the debtors and, in the columns, the creditors.
	 * 
	 * Sum of a i-th row = Liabilities of the i-th bank
	 * 
	 * @param bank
	 * @return
	 */
	public Money getTotalLiabilities(Bank bank) {

		Preconditions.checkNotNull(bank,
				"Null banks do not posses assets! Please specify one!");

		Collection<InterbankExposition> outEdges = graph.getOutEdges(bank);

		return InterbankExpositionOperations.sum(outEdges);

	}

	public Money getTotalLiabilitiesByCNPJ(String CNPJ) {

		Preconditions.checkArgument(StringUtils.isNotBlank(CNPJ),
				"The CNPJ cannot be null nor blank!");

		return getTotalLiabilities(indexedBanks.get(CNPJ));
	}

	/**
	 * In the rows there are the debtors and, in the columns, the creditors.
	 * 
	 * Sum of a i-th row = Liabilities of the i-th bank
	 * 
	 * @param bank
	 * @return
	 */
	public Money getTotalAssets(Bank bank) {

		Preconditions.checkNotNull(bank,
				"Null banks do not posses assets! Please specify one!");

		Collection<InterbankExposition> outEdges = graph.getInEdges(bank);

		return InterbankExpositionOperations.sum(outEdges);

	}

	public Money getTotalAssetsByCNPJ(String CNPJ) {

		Preconditions.checkArgument(StringUtils.isNotBlank(CNPJ),
				"The CNPJ cannot be null nor blank!");

		return getTotalAssets(indexedBanks.get(CNPJ));
	}

	public int getDegree(String CNPJ) {
		return graph.degree(indexedBanks.get(CNPJ));
	}

	public int getInDegree(String CNPJ) {
		return graph.inDegree(indexedBanks.get(CNPJ));
	}

	public int getOutDegree(String CNPJ) {
		return graph.outDegree(indexedBanks.get(CNPJ));
	}

	public Collection<Bank> getNeighbors(String CNPJ) {
		return graph.getNeighbors(indexedBanks.get(CNPJ));
	}

	public Graph<Bank, InterbankExposition> getGraph() {
		return graph;
	}

}
