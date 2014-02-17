package ca.ulaval.ift6002.m2.domain.drug;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import ca.ulaval.ift6002.m2.infrastructure.persistence.inmemory.DataAdapter;

public class CSVDrugDataAdapter implements DataAdapter<Drug> {

    private static final int BRAND_NAME_COLUMN = 5;
    private static final int DESCRIPTOR_COLUMN = 6;
    private static final int DIN_COLUMN = 4;

    private static final String DATA_FILE = "/drug.txt";

    private CSVReader reader;
    private List<Drug> drugs;

    public CSVDrugDataAdapter() {
        openCSVFile();
        retrieveDrugsFromFile();
    }

    @Override
    public List<Drug> retrieveData() {
        return drugs;
    }

    private void openCSVFile() {
        InputStream drugResource = getClass().getResourceAsStream(DATA_FILE);

        reader = new CSVReader(new InputStreamReader(drugResource));
    }

    private void retrieveDrugsFromFile() {
        List<String[]> allLinesFromFile = extractLinesFromFile();
        drugs = new ArrayList<Drug>();

        for (String[] line : allLinesFromFile) {
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
        String brandName = line[BRAND_NAME_COLUMN];
        String descriptor = line[DESCRIPTOR_COLUMN];

        return new Drug(new Din(din), brandName, descriptor);
    }
}
