package projectH.domain.drug;

public interface DrugRepository {

	Drug findByBrandNameOrDescriptor(String drugName);

}
