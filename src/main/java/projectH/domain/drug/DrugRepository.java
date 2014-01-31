package projectH.domain.drug;

import java.util.List;

public interface DrugRepository {

	List<Drug> findByBrandNameOrDescriptor(String drugName);

}
