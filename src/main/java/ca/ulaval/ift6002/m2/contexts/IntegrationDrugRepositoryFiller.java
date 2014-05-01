package ca.ulaval.ift6002.m2.contexts;

import java.util.List;

import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.file.parser.CSVDrugParser;
import ca.ulaval.ift6002.m2.file.parser.FileParser;

public class IntegrationDrugRepositoryFiller {

    private static final String DRUG_FILE_PATH = "/IntegrationDrug.txt";
    private static final String DRUG_INTERACTIONS_FILE_PATH = "/IntegrationInteractions.txt";

    private final DrugRepository drugRepository;
    private final FileParser<Drug> drugParser;

    public IntegrationDrugRepositoryFiller(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
        this.drugParser = new CSVDrugParser(DRUG_FILE_PATH, DRUG_INTERACTIONS_FILE_PATH);
    }

    public void fill() {
        List<Drug> drugs = drugParser.parse();

        drugRepository.store(drugs);
    }
}
