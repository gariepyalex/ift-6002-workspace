package ca.ulaval.ift6002.m2.infrastructure.persistence.factory;

import ca.ulaval.ift6002.m2.domain.drug.CSVDrugDataAdapter;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.inmemory.repository.DrugInMemoryRepository;

public class InMemoryRepositoryFactory extends RepositoryFactory {

	@Override
	public DrugRepository createDrugRepository() {
		return new DrugInMemoryRepository(new CSVDrugDataAdapter());
	}

}
