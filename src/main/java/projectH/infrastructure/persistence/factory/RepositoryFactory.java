package projectH.infrastructure.persistence.factory;

import projectH.domain.drug.DrugRepository;

public interface RepositoryFactory {

	DrugRepository createDrugRepository();
}
