package ca.ulaval.ift6002.m2.contexts;

import java.util.List;

import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.file.parser.CSVDrugParser;
import ca.ulaval.ift6002.m2.file.parser.FileParser;
import ca.ulaval.ift6002.m2.infrastructure.persistence.locator.RepositoryLocator;

public class DemoDrugRepositoryFiller {

    private final DrugRepository drugRepository;
    private final FileParser<Drug> drugParser;

    public DemoDrugRepositoryFiller() {
        this.drugRepository = RepositoryLocator.getDrugRepository();
        this.drugParser = new CSVDrugParser();
    }

    public DemoDrugRepositoryFiller(DrugRepository repository, FileParser<Drug> drugParser) {
        this.drugRepository = repository;
        this.drugParser = drugParser;
    }

    public void fill() {
        List<Drug> drugs = drugParser.parse();

        drugRepository.store(drugs);
    }

}
