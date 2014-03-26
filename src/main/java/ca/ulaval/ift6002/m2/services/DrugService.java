package ca.ulaval.ift6002.m2.services;

import java.util.Collection;

import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.locator.RepositoryLocator;

public class DrugService {

    private static final int MIN_LENGTH_OF_SEARCH_KEYWORDS = 3;

    private final DrugRepository drugRepository;

    public DrugService() {
        drugRepository = RepositoryLocator.getDrugRepository();
    }

    public Collection<Drug> findBy(String keyword) {
        if (keyword.length() < MIN_LENGTH_OF_SEARCH_KEYWORDS) {
            throw new IllegalArgumentException("The minimum character's length is: " + MIN_LENGTH_OF_SEARCH_KEYWORDS);
        }

        return drugRepository.findBy(keyword);
    }

    protected DrugService(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }
}
