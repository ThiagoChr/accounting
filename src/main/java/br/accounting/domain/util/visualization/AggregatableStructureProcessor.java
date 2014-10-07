package br.accounting.domain.util.visualization;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class AggregatableStructureProcessor {

	private static final int ONE = 1;

	private String identation;
	private String spacing;
	private String lineBreak;

	private IAggregatableStructure structure;

	public AggregatableStructureProcessor(IAggregatableStructure estrutura) {
		this.structure = estrutura;
	}

	public String visualize() {

		designarCaracteresEspeciaisConformeTipoFormatacao();

		// variável compartilhada
		StringBuilder sb = new StringBuilder();

		toStringUsingBreadthFirstSearchStrategy(ONE, structure, sb,
				StringUtils.EMPTY, false);

		return sb.toString();
	}

	private void toStringUsingBreadthFirstSearchStrategy(int depth,
			IAggregatableStructure structure, StringBuilder sb,
			String recuoAcumulado, boolean multiplo) {

		sb.append(recuoAcumulado);
		sb.append(getHeaderConta(structure));
		sb.append(lineBreak);

		final Set<? extends IAggregatableStructure> estruturasFilhas = structure
				.getStructuralChildren();

		int i = ONE;
		for (IAggregatableStructure estruturaFilha : estruturasFilhas) {
			toStringUsingBreadthFirstSearchStrategy(depth + ONE,
					estruturaFilha, sb, recuoAcumulado + getRecuo(multiplo),
					isMultiplo(estruturasFilhas, i));
			++i;
		}
	}

	private String getRecuo(boolean multiplo) {
		String retorno = StringUtils.EMPTY;
		if (multiplo) {
			retorno = identation;
		} else {
			retorno = spacing;
		}
		return retorno;
	}

	private String getHeaderConta(IAggregatableStructure estruturaAtual) {
		return estruturaAtual.getStructureDescription();
	}

	/**
	 * Se houver mais de uma conta filha e a conta atual não é a última, é
	 * múltiplo
	 */
	private boolean isMultiplo(
			final Set<? extends IAggregatableStructure> contasFilhas, int i) {
		return (contasFilhas.size() > 1) && (i != contasFilhas.size());
	}

	private void designarCaracteresEspeciaisConformeTipoFormatacao() {
		identation = "|     ";
		spacing = "      ";
		lineBreak = "\n";
	}

}
