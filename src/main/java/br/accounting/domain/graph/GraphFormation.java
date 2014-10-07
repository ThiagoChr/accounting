package br.accounting.domain.graph;

import java.util.List;

import com.google.common.base.Preconditions;

import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;

public class GraphFormation<V extends IVertex, E extends IEdge> {

	private List<E> edges;

	public GraphFormation(List<E> edges) {

		this.edges = Preconditions.checkNotNull(edges,
				"The edges cannot be null!");
	}

	@SuppressWarnings("unchecked")
	public DirectedGraph<V, E> createNetwork() {

		DirectedGraph<V, E> graph = new DirectedSparseGraph<V, E>();

		for (E edge : edges) {

			V sourceVertex = (V) edge.getSourceVertex();
			V destinyVertex = (V) edge.getDestinyVertex();

			addVertexIfNecessary(graph, sourceVertex);
			addVertexIfNecessary(graph, destinyVertex);

			graph.addEdge(edge, sourceVertex, destinyVertex);

		}

		return graph;

	}

	private void addVertexIfNecessary(Graph<V, E> graph, V bank) {

		Preconditions.checkNotNull(bank, "The bank cannot be null!");

		if (!graph.containsVertex(bank)) {
			graph.addVertex(bank);
		}

	}
}
