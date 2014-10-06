package br.gov.bcb.depep.domain.graph;

import br.gov.bcb.depep.domain.util.money.Money;

public interface IEdge {

	IVertex getSourceVertex();

	IVertex getDestinyVertex();

	Money getWeight();

}
