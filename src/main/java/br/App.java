package br;

import java.io.IOException;
import java.util.SortedSet;

import org.joda.time.YearMonth;

import br.accounting.domain.graph.NetworkInterbankExpositionDecorator;
import br.accounting.domain.graph.NetworkManager;
import br.accounting.domain.graph.recorder.NetworkCSVRecorder;
import br.accounting.domain.graph.recorder.NetworkXLSXRecorder;

public class App {

	public static void main(String[] args) throws IOException {

		NetworkManager graphManager = new NetworkManager();

		recordInfo(graphManager);

	}

	private static void recordInfo(NetworkManager graphManager)
			throws IOException {

		SortedSet<YearMonth> dates = graphManager.getDates();

		for (YearMonth referenceDate : dates) {

			NetworkInterbankExpositionDecorator network = graphManager
					.createExpositionNetworkForDate(referenceDate);

			NetworkXLSXRecorder recorderXSLX = new NetworkXLSXRecorder(network);
			NetworkCSVRecorder recorderCSV = new NetworkCSVRecorder(network);

			recorderXSLX.saveNetworkAtDate(referenceDate);
			recorderCSV.saveNetworkAtDate(referenceDate);

		}

	}
}
