package projectH;

import org.junit.Test;

public class DinRepositoryTest {

	@Test
	public void takeTheDinFromTheDrugLineGivenAndPutItInTheList() {
		DrugCsvFileAdapter drugFileAdapter = mock(DrugCsvFileAdapter.class);
		DinRepository drugRepo = new DinRepository(drugFileAdapter);
		drugFileAdapter.getNextDrugLine();

	}
}
