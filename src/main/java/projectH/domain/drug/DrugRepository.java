package projectH.domain.drug;

import java.util.Collection;

public interface DrugRepository {

	Collection<Drug> findByBrandNameOrDescriptor(String keyword);

}
