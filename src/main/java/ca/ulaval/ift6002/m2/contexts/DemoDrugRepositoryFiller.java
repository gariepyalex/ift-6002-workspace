package ca.ulaval.ift6002.m2.contexts;

import java.util.List;

import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.file.parser.CSVDrugParser;
import ca.ulaval.ift6002.m2.file.parser.FileParser;
import ca.ulaval.ift6002.m2.locator.RepositoryLocator;

public class DemoDrugRepositoryFiller {

    private final DrugRepository drugRepository;
    private final FileParser<Drug> drugParser;
    private final String dataFilePath = "/drug.txt";

    public DemoDrugRepositoryFiller() {
        this.drugRepository = RepositoryLocator.getDrugRepository();
        this.drugParser = new CSVDrugParser(dataFilePath);
    }

    public void fill() {
        List<Drug> drugs = drugParser.parse();

        drugRepository.store(drugs);
    }

    protected DemoDrugRepositoryFiller(DrugRepository repository, FileParser<Drug> drugParser) {
        this.drugRepository = repository;
        this.drugParser = drugParser;
    }

}
