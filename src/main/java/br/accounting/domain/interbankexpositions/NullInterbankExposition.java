package br.accounting.domain.interbankexpositions;

import br.accounting.domain.graph.IEdge;
import br.accounting.domain.graph.IVertex;
import br.accounting.domain.util.money.Money;

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
