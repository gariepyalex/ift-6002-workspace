package ca.ulaval.ift6002.m2.domain.drug;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

public class CSVDrugDataAdapter implements DrugDataAdapter {

	private static final int BRAND_NAME = 5;
	private static final int DESCRIPTOR_COLUMN = 6;
	private static final int DIN_COLUMN = 4;
	private static final String DATA_FILE = "/drug.txt";
	private CSVReader reader;
	private List<Drug> drugs;

	public CSVDrugDataAdapter() {
		openCSVFile();
		makeDrugsList();
	}

	private void openCSVFile() {
		InputStream drugResource = this.getClass().getResourceAsStream(DATA_FILE);
		reader = new CSVReader(new InputStreamReader(drugResource));
	}

	private void makeDrugsList() {
		List<String[]> fileLines = extractLinesFromFile();
		drugs = new LinkedList<Drug>();

		for (String[] line : fileLines) {
			Drug drugBuilt = hydrateDrugFromLine(line);
			drugs.add(drugBuilt);
		}
	}

	private List<String[]> extractLinesFromFile() {
		try {
			return reader.readAll();
		} catch (IOException e) {
			return Collections.emptyList();
		}
	}

	private Drug hydrateDrugFromLine(String[] line) {
		String din = line[DIN_COLUMN];
		String brandName = line[BRAND_NAME];
		String descriptor = line[DESCRIPTOR_COLUMN];

		return new Drug(new Din(din), brandName, descriptor);
	}

	public Collection<Drug> getDrugsListFromFile() {
		return drugs;

	}
}
