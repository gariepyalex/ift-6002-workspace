package projectH.domain.drug;

public interface DrugRepository {

	Drug findByName(String drugName);

}
