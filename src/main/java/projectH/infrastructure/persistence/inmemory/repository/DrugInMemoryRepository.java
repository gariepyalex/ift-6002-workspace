package projectH.infrastructure.persistence.inmemory.repository;

import java.util.Collection;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Objects;

import projectH.domain.drug.Drug;
import projectH.domain.drug.DrugRepository;
import projectH.infrastructure.persistence.inmemory.ListingRepository;

public class DrugInMemoryRepository extends ListingRepository<Drug> implements DrugRepository {

	private static final int MIN_LENGTH_OF_FIND_BY_NAME = 3;

	@Override
	public Drug findByName(String drugName) {
		if (drugName.length() < MIN_LENGTH_OF_FIND_BY_NAME) {
			throw new IllegalArgumentException("The minimum character's length is: " + MIN_LENGTH_OF_FIND_BY_NAME);
		}

		if (drugName.equals("UNEXISTING_DRUG")) {
			throw new NoSuchElementException("There is no drug found named: " + drugName);
		}

		return new Drug("din", drugName, "", "");
	}

	@Override
	protected Collection<Drug> loadData() {
		return Collections.emptySet(); // temporary
	}

	@Override
	protected int hashKeys(Drug element) {
		return Objects.hash(element.getDin());
	}
}
