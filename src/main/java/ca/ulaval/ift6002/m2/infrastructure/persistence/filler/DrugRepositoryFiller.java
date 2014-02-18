package ca.ulaval.ift6002.m2.infrastructure.persistence.filler;

import java.util.List;

import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.file.FileParser;

public class DrugRepositoryFiller implements RepositoryFiller<Drug> {

    private final FileParser<Drug> drugParser;

    public DrugRepositoryFiller(FileParser<Drug> drugParser) {
        this.drugParser = drugParser;
    }

    @Override
    public List<Drug> fill() {
        return drugParser.parse();
    }

}
