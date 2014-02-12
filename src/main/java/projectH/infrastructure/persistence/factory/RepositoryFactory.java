package projectH.infrastructure.persistence.factory;

import projectH.domain.drug.DrugRepository;
import projectH.infrastructure.persistence.PersistenceType;

public abstract class RepositoryFactory {

    private static final PersistenceType TYPE = PersistenceType.IN_MEMORY;

    public abstract DrugRepository createDrugRepository();

    public static DrugRepository getDrugRepository() {
        return getFactory().createDrugRepository();
    }

    private static RepositoryFactory getFactory() {
        // TODO ask the teacher about how to do polymorphism on this case
        if (TYPE == PersistenceType.IN_MEMORY) {
            return new InMemoryRepositoryFactory();
        }

        throw new IllegalStateException("The persistence type set is not implemented");
    }
}
