package br.gov.bcb.depep.domain.util.visualization;

import java.util.SortedSet;

/**
 * Interface employed to construct the toString of accounts.
 * 
 * @author depep.thiagocs
 */
public interface IAggregatableStructure {

	/**
	 * Children of the structure.
	 */
	SortedSet<? extends IAggregatableStructure> getStructuralChildren();

	String getStructureDescription();

}
