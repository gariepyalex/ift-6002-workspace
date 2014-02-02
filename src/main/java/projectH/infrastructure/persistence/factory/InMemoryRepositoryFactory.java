package projectH.infrastructure.persistence.factory;

import projectH.domain.drug.DrugRepository;
import projectH.infrastructure.persistence.inmemory.repository.DrugInMemoryRepository;

public class InMemoryRepositoryFactory extends RepositoryFactory {

	@Override
	public DrugRepository createDrugRepository() {
		return new DrugInMemoryRepository();
	}

}
