package ca.ulaval.ift6002.m2.domain.drug;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.ift6002.m2.domain.file.FileReader;
import ca.ulaval.ift6002.m2.domain.file.Parser;

public class CSVDrugParser implements Parser<Drug> {

    private static final String DATA_FILE = "/drug.txt";

    private static final int DIN_COLUMN = 4;
    private static final int BRAND_NAME_COLUMN = 5;
    private static final int DESCRIPTOR_COLUMN = 6;

    private final FileReader<String[]> fileReader;

    public CSVDrugParser(FileReader<String[]> fileReader) {
        this.fileReader = fileReader;
    }

    @Override
    public List<Drug> parse() {
        List<String[]> allLinesFromFile = fileReader.readAll(DATA_FILE);
        List<Drug> drugs = new ArrayList<Drug>();

        for (String[] line : allLinesFromFile) {
            Drug drugBuilt = hydrate(line);

            drugs.add(drugBuilt);
        }

        return drugs;
    }

    private Drug hydrate(String[] line) {
        String din = line[DIN_COLUMN];
        String brandName = line[BRAND_NAME_COLUMN];
        String descriptor = line[DESCRIPTOR_COLUMN];

        return new Drug(new Din(din), brandName, descriptor);
    }

}
