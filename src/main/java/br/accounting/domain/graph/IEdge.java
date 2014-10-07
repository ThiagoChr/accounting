package br.accounting.domain.graph;

import br.accounting.domain.util.money.Money;

public interface IEdge {

	IVertex getSourceVertex();

	IVertex getDestinyVertex();

	Money getWeight();

}
