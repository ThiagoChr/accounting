package br.accounting.domain.interbankexpositions;

import br.accounting.domain.graph.IEdge;
import br.accounting.domain.graph.IVertex;
import br.accounting.domain.util.money.Money;

public class NullInterbankExposition implements IEdge {

    public static NullInterbankExposition NULL = new NullInterbankExposition();

    private NullInterbankExposition() {
        // constrained use
    }

    @Override
    public IVertex getSourceVertex() {
        return null;
    }

    @Override
    public IVertex getDestinyVertex() {
        return null;
    }

    @Override
    public Money getWeight() {
        return Money.ZERO;
    }

}
