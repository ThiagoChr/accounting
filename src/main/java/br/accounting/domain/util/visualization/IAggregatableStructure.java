package br.accounting.domain.util.visualization;

import java.util.SortedSet;

/**
 * Interface employed to construct the toString of accounts.
 */
public interface IAggregatableStructure {

    /**
     * Children of the structure.
     */
    SortedSet<? extends IAggregatableStructure> getStructuralChildren();

    String getStructureDescription();

}
