package ca.ulaval.ift6002.m2.contexts;

import java.util.List;

import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.domain.file.FileParser;

public class DemoDrugRepositoryFiller {

    private final DrugRepository drugRepository;
    private final FileParser<Drug> drugParser;

    public DemoDrugRepositoryFiller(DrugRepository repository, FileParser<Drug> drugParser) {
        this.drugRepository = repository;
        this.drugParser = drugParser;
    }

    public void fill() {

        List<Drug> drugs = drugParser.parse();

        for (Drug drug : drugs) {
            drugRepository.store(drug);
        }
    }

}
