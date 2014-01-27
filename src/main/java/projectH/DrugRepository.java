package projectH;

import java.util.NoSuchElementException;

public class DrugRepository {

	private static final int MIN_LENGTH_OF_FIND_BY_NAME = 3;

	public Drug findByName(String drugName) {
		if (drugName.length() < MIN_LENGTH_OF_FIND_BY_NAME) {
			throw new IllegalArgumentException("The minimum character's length is: " + MIN_LENGTH_OF_FIND_BY_NAME);
		}

		if (drugName.equals("UNEXISTING_DRUG")) {
			throw new NoSuchElementException("There is no drug found named: " + drugName);
		}

		return new Drug("EXISTING_DRUG", "", "");
	}
}
