package br.accounting.domain.graph.recorder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.YearMonth;

import br.accounting.domain.bank.Bank;
import br.accounting.domain.bank.BankOperations;
import br.accounting.domain.graph.IEdge;
import br.accounting.domain.graph.NetworkInterbankExpositionDecorator;
import br.accounting.domain.interbankexpositions.InterbankExposition;
import br.accounting.domain.interbankexpositions.NullInterbankExposition;
import br.accounting.domain.util.date.DataUtil;
import br.accounting.domain.util.money.Money;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

import edu.uci.ics.jung.graph.Graph;

public class NetworkXLSXRecorder implements INetworkRecorder {

    private final Graph<Bank, InterbankExposition> graph;
    private final Map<String, Bank> indexedBanks;

    public NetworkXLSXRecorder(NetworkInterbankExpositionDecorator network) {
        this.graph = Preconditions.checkNotNull(network.getGraph());

        this.indexedBanks = BankOperations.indexByCNPJ(graph.getVertices());
    }

    @Override
    public void saveNetworkAtDate(YearMonth referenceDate) throws IOException {

        String fileName = INetworkRecorder.ABSOLUTE_PATH
                + "InterbankExpositions_" + DataUtil.toString(referenceDate)
                + ".xlsx";

        XSSFWorkbook workbook = new XSSFWorkbook();

        saveNetworkToSheet(workbook);
        saveCapitalBuffer(workbook, referenceDate);
        saveTotalAssets(workbook, referenceDate);
        saveBankCNPJMapping(workbook);

        FileOutputStream fos = new FileOutputStream(new File(fileName));

        workbook.write(fos);
        fos.close();

    }

    private void saveNetworkToSheet(XSSFWorkbook workbook) {

        XSSFSheet matrixSheet = workbook.createSheet("matrix");

        Map<Integer, Bank> sequencialIndexing = convertIndexedByCNPJToIndexedBySequencialNumbers();

        int numberVertices = graph.getVertexCount();

        for (int i = 1; i <= numberVertices; i++) {

            Bank sourceBank = sequencialIndexing.get(i);

            XSSFRow row = matrixSheet.createRow(i - 1);

            for (int j = 1; j <= numberVertices; j++) {

                Bank destinyBank = sequencialIndexing.get(j);

                IEdge edge = graph.findEdge(sourceBank, destinyBank);

                Money edgeWeight = Objects.firstNonNull(edge,
                        NullInterbankExposition.NULL).getWeight();

                XSSFCell cellAtRow = row.createCell(j - 1);

                cellAtRow.setCellValue(edgeWeight.getValor().doubleValue());

            }

        }

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

    private void saveTotalAssets(XSSFWorkbook workbook, YearMonth referenceDate) {

        XSSFSheet totalAssetsSheet = workbook.createSheet("total_assets");

        Map<Integer, Bank> sequencialIndexing = convertIndexedByCNPJToIndexedBySequencialNumbers();

        int numberVertices = graph.getVertexCount();

        for (int i = 1; i <= numberVertices; i++) {

            Bank bank = sequencialIndexing.get(i);

            XSSFRow row = totalAssetsSheet.createRow(i - 1);
            XSSFCell cellAtRow = row.createCell(0);

            cellAtRow.setCellValue(bank.getTotalAssets(referenceDate)
                    .getValor().doubleValue());

        }

    }

    private void saveCapitalBuffer(XSSFWorkbook workbook,
            YearMonth referenceDate) {

        XSSFSheet capitalBufferSheet = workbook.createSheet("capital");

        Map<Integer, Bank> sequencialIndexing = convertIndexedByCNPJToIndexedBySequencialNumbers();

        int numberVertices = graph.getVertexCount();

        for (int i = 1; i <= numberVertices; i++) {

            Bank bank = sequencialIndexing.get(i);

            XSSFRow row = capitalBufferSheet.createRow(i - 1);
            XSSFCell cellAtRow = row.createCell(0);

            cellAtRow.setCellValue(bank.getCapitalBuffer(referenceDate)
                    .getValor().doubleValue());

        }

    }

    private void saveBankCNPJMapping(XSSFWorkbook workbook) {

        XSSFSheet capitalBufferSheet = workbook.createSheet("CNPJ mapping");

        Map<Integer, Bank> sequencialIndexing = convertIndexedByCNPJToIndexedBySequencialNumbers();

        int numberVertices = graph.getVertexCount();

        for (int i = 1; i <= numberVertices; i++) {

            Bank bank = sequencialIndexing.get(i);

            XSSFRow row = capitalBufferSheet.createRow(i - 1);

            XSSFCell cellAtRow = row.createCell(0);
            cellAtRow.setCellValue(i);

            cellAtRow = row.createCell(1);
            cellAtRow.setCellValue(bank.getCnpj());

            cellAtRow = row.createCell(2);
            cellAtRow.setCellValue(bank.getName());

            cellAtRow = row.createCell(3);
            cellAtRow.setCellValue(bank.getSize().getDescription());

        }
    }
}
