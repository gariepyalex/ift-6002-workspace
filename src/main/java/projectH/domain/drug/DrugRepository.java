package projectH.domain.drug;

import java.util.List;

public interface DrugRepository {

	List<Drug> findByBrandNameOrDescriptor(String keyword);
	boolean isAValidDin(String din);
}
