package ca.ulaval.ift6002.m2.file;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.drug.DrugFactory;
import ca.ulaval.ift6002.m2.factory.hibernate.DrugHibernateFactory;

public class CSVDrugParser implements FileParser<Drug> {

    private static final String DATA_FILE_PATH = "/drug.txt";

    private static final int DIN_COLUMN = 3;
    private static final int BRAND_NAME_COLUMN = 4;
    private static final int DESCRIPTOR_COLUMN = 5;

    private final FileReader<String[]> fileReader;
    private final DrugFactory drugFactory;

    public CSVDrugParser() {
        this.fileReader = new CSVFileReader();
        this.drugFactory = new DrugHibernateFactory();
    }

    public CSVDrugParser(FileReader<String[]> fileReader) {
        this.fileReader = fileReader;
        // TODO call factoryLocator
        this.drugFactory = new DrugHibernateFactory();
    }

    public CSVDrugParser(FileReader<String[]> fileReader, DrugFactory drugFactory) {
        this.fileReader = fileReader;
        this.drugFactory = drugFactory;
    }

    @Override
    public List<Drug> parse() {
        List<String[]> allLinesFromFile = fileReader.readAll(DATA_FILE_PATH);
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

        return drugFactory.create(new Din(din), brandName, descriptor);
    }

}
