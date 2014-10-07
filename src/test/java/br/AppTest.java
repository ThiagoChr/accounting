package br;

import java.io.IOException;

import junit.framework.Assert;

import org.joda.time.YearMonth;
import org.junit.Before;
import org.junit.Test;

import br.accounting.domain.graph.NetworkInterbankExpositionDecorator;
import br.accounting.domain.graph.NetworkManager;
import br.accounting.domain.util.money.Money;

/**
 * Testes de integração.
 */
public class AppTest {

    private NetworkManager graphManager;

    @Before
    public void setUp() throws IOException {

        graphManager = new NetworkManager();

    }

    @Test
    public void totalLiabilitiesInAnInCrisisScenario() {

        YearMonth referenceDate = YearMonth.parse("2008-09");

        NetworkInterbankExpositionDecorator graph = graphManager
                .createExpositionNetworkForDate(referenceDate);

        Money totalLiabilitiesByCNPJ = graph.getTotalLiabilitiesByCNPJ("50191");

        Assert.assertEquals(Money.newMoney("238.417.688,00"),
                totalLiabilitiesByCNPJ);
    }

    @Test
    public void totalLiabilitiesInPostCrisisJuly2011() {

        YearMonth referenceDate = YearMonth.parse("2011-07");

        NetworkInterbankExpositionDecorator graph = graphManager
                .createExpositionNetworkForDate(referenceDate);

        Money totalLiabilitiesByCNPJ = graph.getTotalLiabilitiesByCNPJ("50191");

        Assert.assertEquals(Money.newMoney("12.644.755,00"),
                totalLiabilitiesByCNPJ);
    }

    @Test
    public void totalAssetsInAnInCrisisScenario() {

        YearMonth referenceDate = YearMonth.parse("2008-09");

        NetworkInterbankExpositionDecorator graph = graphManager
                .createExpositionNetworkForDate(referenceDate);

        Money totalAssetsByCNPJ = graph.getTotalAssetsByCNPJ("10045");

        Assert.assertEquals(Money.newMoney("9.421.843.639,28"),
                totalAssetsByCNPJ);
    }

    @Test
    public void totalAssetsInPostCrisisJuly2011() {

        YearMonth referenceDate = YearMonth.parse("2011-07");

        NetworkInterbankExpositionDecorator graph = graphManager
                .createExpositionNetworkForDate(referenceDate);

        Money totalAssetsByCNPJ = graph.getTotalAssetsByCNPJ("20152");

        Assert.assertEquals(Money.newMoney("9.001.053.011,69"),
                totalAssetsByCNPJ);
    }

    @Test
    public void inDegreeInAnInCrisisScenario() {

        YearMonth referenceDate = YearMonth.parse("2008-09");

        NetworkInterbankExpositionDecorator graph = graphManager
                .createExpositionNetworkForDate(referenceDate);

        int degree = graph.getInDegree("2038232");

        Assert.assertEquals(28, degree);

    }

    @Test
    public void inDegreeInAPostCrisisJuly2011() {

        YearMonth referenceDate = YearMonth.parse("2011-07");

        NetworkInterbankExpositionDecorator graph = graphManager
                .createExpositionNetworkForDate(referenceDate);

        int degree = graph.getInDegree("20255");

        Assert.assertEquals(4, degree);

    }

    @Test
    public void outDegreeInAnInCrisisScenario() {

        YearMonth referenceDate = YearMonth.parse("2008-09");

        NetworkInterbankExpositionDecorator graph = graphManager
                .createExpositionNetworkForDate(referenceDate);

        int degree = graph.getOutDegree("2038232");

        Assert.assertEquals(48, degree);

    }

    @Test
    public void degreeInAnInCrisisScenario() {

        YearMonth referenceDate = YearMonth.parse("2008-09");

        NetworkInterbankExpositionDecorator graph = graphManager
                .createExpositionNetworkForDate(referenceDate);

        int degree = graph.getDegree("2038232");

        Assert.assertEquals(28 + 48, degree);

    }

}
