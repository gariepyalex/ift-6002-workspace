package projectH.domain.drug;

import java.util.Collection;

public interface DrugRepository {

	Drug get(Din din);

	Collection<Drug> findByBrandNameOrDescriptor(String keyword);

}
