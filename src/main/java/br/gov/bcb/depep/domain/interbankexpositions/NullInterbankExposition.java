package br.gov.bcb.depep.domain.interbankexpositions;

import br.gov.bcb.depep.domain.graph.IEdge;
import br.gov.bcb.depep.domain.graph.IVertex;
import br.gov.bcb.depep.domain.util.money.Money;

public class NullInterbankExposition implements IEdge {

	public static NullInterbankExposition NULL = new NullInterbankExposition();

	private NullInterbankExposition() {
		// constrained use
	}

	public IVertex getSourceVertex() {
		return null;
	}

	public IVertex getDestinyVertex() {
		return null;
	}

	public Money getWeight() {
		return Money.ZERO;
	}

}
