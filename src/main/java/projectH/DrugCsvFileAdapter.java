package projectH;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DrugCsvFileAdapter implements DrugFileAdapter {
	Scanner scanner = null;

	public DrugCsvFileAdapter() throws FileNotFoundException {
		File dinFile = new File("src/main/resources/drug.txt");
		scanner = new Scanner(dinFile);
	}

	public String getNextDrugLine() {
		return scanner.nextLine();
	}

}
