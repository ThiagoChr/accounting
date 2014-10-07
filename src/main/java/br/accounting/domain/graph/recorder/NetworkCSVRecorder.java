package br.accounting.domain.graph.recorder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Map.Entry;

import org.joda.time.YearMonth;

import br.accounting.domain.bank.Bank;
import br.accounting.domain.bank.BankOperations;
import br.accounting.domain.graph.IEdge;
import br.accounting.domain.graph.NetworkInterbankExpositionDecorator;
import br.accounting.domain.interbankexpositions.InterbankExposition;
import br.accounting.domain.interbankexpositions.NullInterbankExposition;
import br.accounting.domain.util.date.DataUtil;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

import edu.uci.ics.jung.graph.Graph;

public class NetworkCSVRecorder implements INetworkRecorder {

    private static final String FILE_ENCODING = "UTF-8";
    private static final Character SEPARATOR = ',';

    private final Graph<Bank, InterbankExposition> graph;
    private final Map<String, Bank> indexedBanks;

    public NetworkCSVRecorder(
            NetworkInterbankExpositionDecorator networkDecorator) {
        this.graph = Preconditions.checkNotNull(networkDecorator.getGraph());

        this.indexedBanks = BankOperations.indexByCNPJ(graph.getVertices());
    }

    @Override
    public void saveNetworkAtDate(YearMonth referenceDate) throws IOException {

        saveBankMapping(referenceDate);

        saveTotalAssets(referenceDate);

        saveNetwork(referenceDate);

    }

    private void saveBankMapping(YearMonth referenceDate) throws IOException {

        String fileName = INetworkRecorder.ABSOLUTE_PATH + "bankMapping_"
                + DataUtil.toString(referenceDate) + ".txt";

        saveBankMapping(fileName);

    }

    private void saveTotalAssets(YearMonth referenceDate) throws IOException {

        String fileName = ABSOLUTE_PATH + "totalAssets_"
                + DataUtil.toString(referenceDate) + ".txt";

        saveTotalAssetsToFile(fileName, referenceDate);

    }

    private void saveNetwork(YearMonth referenceDate)
            throws FileNotFoundException, UnsupportedEncodingException {

        String fileName = ABSOLUTE_PATH + "network_"
                + DataUtil.toString(referenceDate) + ".txt";

        saveNetworkToFile(fileName);
    }

    private void saveNetworkToFile(String fileName)
            throws FileNotFoundException, UnsupportedEncodingException {

        Map<Integer, Bank> sequencialIndexing = convertIndexedByCNPJToIndexedBySequencialNumbers();

        PrintWriter writer = new PrintWriter(fileName);

        int numberVertices = graph.getVertexCount();

        for (int i = 1; i <= numberVertices; i++) {

            Bank sourceBank = sequencialIndexing.get(i);

            StringBuilder lineBuilder = new StringBuilder();

            for (int j = 1; j <= numberVertices; j++) {

                Bank destinyBank = sequencialIndexing.get(j);

                IEdge edge = graph.findEdge(sourceBank, destinyBank);

                BigDecimal edgeWeight = Objects
                        .firstNonNull(edge, NullInterbankExposition.NULL)
                        .getWeight().getValor();

                lineBuilder.append(edgeWeight);

                if (j != numberVertices) {
                    lineBuilder.append(SEPARATOR);
                }

            }

            writer.println(lineBuilder.toString());

        }

        writer.close();

    }

    private Map<Integer, Bank> convertIndexedByCNPJToIndexedBySequencialNumbers() {

        int sequencial = 1;

        Map<Integer, Bank> sequencialIndexing = Maps.newHashMap();

        for (Entry<String, Bank> entry : indexedBanks.entrySet()) {

            Bank bank = entry.getValue();

            sequencialIndexing.put(sequencial++, bank);

        }

        return sequencialIndexing;

    }

    private void saveTotalAssetsToFile(String fileName, YearMonth referenceDate)
            throws FileNotFoundException, UnsupportedEncodingException {

        Map<Integer, Bank> sequencialIndexing = convertIndexedByCNPJToIndexedBySequencialNumbers();

        PrintWriter writer = new PrintWriter(fileName);

        int numberVertices = graph.getVertexCount();

        for (int i = 1; i <= numberVertices; i++) {

            Bank bank = sequencialIndexing.get(i);

            StringBuilder sb = new StringBuilder();

            sb.append(bank.getCnpj());
            sb.append(SEPARATOR);
            sb.append(bank.getTotalAssets(referenceDate).getValor().toString());

            writer.println(sb.toString());
        }

        writer.close();

    }

    private void saveBankMapping(String fileName) throws FileNotFoundException,
            UnsupportedEncodingException {

        Map<Integer, Bank> sequencialIndexing = convertIndexedByCNPJToIndexedBySequencialNumbers();

        PrintWriter writer = new PrintWriter(fileName, FILE_ENCODING);

        int numberVertices = graph.getVertexCount();

        for (int i = 1; i <= numberVertices; i++) {

            Bank bank = sequencialIndexing.get(i);

            StringBuilder sb = new StringBuilder();

            sb.append(i);
            sb.append(SEPARATOR);
            sb.append(bank.getCnpj());
            sb.append(SEPARATOR);
            sb.append(bank.getName().trim().replace(SEPARATOR, '-'));

            writer.println(sb.toString());
        }

        writer.close();

    }

    // public void saveBankMapping(String fileName) throws IOException {
    //
    // Map<Integer, Bank> sequencialIndexing =
    // convertIndexedByCNPJToIndexedBySequencialNumbers();
    //
    // PrintWriter writer = new PrintWriter(fileName);
    //
    // CSVWriter csvWriter = new CSVWriter(writer);
    //
    // int numberVertices = graph.getVertexCount();
    // int numberTokens = 3;
    //
    // for (int i = 1; i <= numberVertices; i++) {
    //
    // Bank bank = sequencialIndexing.get(i);
    //
    // String[] line = new String[numberTokens];
    //
    // line[0] = String.valueOf(i);
    // line[1] = bank.getCnpj();
    // line[2] = bank.getName();
    //
    // csvWriter.writeNext(line);
    // }
    //
    // csvWriter.close();
    // writer.close();
    //
    // }

}
