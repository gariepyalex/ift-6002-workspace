package projectH;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class DinVerificator {
	public List<String> dinList = new ArrayList<String>();
	private final Pattern DIN_FILE_DELIMITER_PATTERN = Pattern.compile("\",\"");
	private final int DIN_COLUMN_NUMBER = 3; // first column being column #0

	public DinVerificator() throws FileNotFoundException {
		File dinFile = new File("src/main/resources/drug.txt");
		Scanner scanner = new Scanner(dinFile);
		scanner.useDelimiter(DIN_FILE_DELIMITER_PATTERN);
		fillDinList(scanner);
		scanner.close();

	}

	private void fillDinList(Scanner scannerOnDinFile) {
		String din;
		do {
			din = extractDinOfTheLine(scannerOnDinFile);
			dinList.add(din);
			scannerOnDinFile.nextLine();
		} while (scannerOnDinFile.hasNext());
	}

	private String extractDinOfTheLine(Scanner scannerOnDinFile) {
		moveScannerToTheDinColumn(scannerOnDinFile);
		String din = null;
		din = scannerOnDinFile.next();
		return din;
	}

	private void moveScannerToTheDinColumn(Scanner scannerOnDinFile) {
		for (int column = 0; column < DIN_COLUMN_NUMBER; column++) {
			scannerOnDinFile.next();
		}
	}

	public boolean isDinValid(String dinToValidate) {
		return dinList.contains(dinToValidate);

	}

	public static void main(String[] args) {
		try {
			DinVerificator v = new DinVerificator();
			System.out.println(v.isDinValid("00020885"));
			System.out.println(v.dinList.get(15532));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
