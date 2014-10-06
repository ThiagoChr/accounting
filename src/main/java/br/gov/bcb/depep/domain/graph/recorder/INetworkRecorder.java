package br.gov.bcb.depep.domain.graph.recorder;

import java.io.IOException;

import org.joda.time.YearMonth;

public interface INetworkRecorder {

	String ABSOLUTE_PATH = "D:\\";

	void saveNetworkAtDate(YearMonth referenceDate) throws IOException;

}
