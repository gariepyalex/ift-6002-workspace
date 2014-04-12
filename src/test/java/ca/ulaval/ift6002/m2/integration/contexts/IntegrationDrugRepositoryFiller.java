package ca.ulaval.ift6002.m2.integration.contexts;

import java.util.List;

import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.file.parser.CSVDrugParser;
import ca.ulaval.ift6002.m2.file.parser.FileParser;
import ca.ulaval.ift6002.m2.locator.RepositoryLocator;

public class IntegrationDrugRepositoryFiller {
    private final DrugRepository drugRepository;
    private final FileParser<Drug> drugParser;
    private final String dataFilePath = "/IntegrationDrug.txt";

    public IntegrationDrugRepositoryFiller() {
        this.drugRepository = RepositoryLocator.getDrugRepository();
        this.drugParser = new CSVDrugParser(dataFilePath);
    }

    public void fill() {
        List<Drug> drugs = drugParser.parse();

        drugRepository.store(drugs);
    }
}
