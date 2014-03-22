package ca.ulaval.ift6002.m2.file.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.drug.DrugFactory;
import ca.ulaval.ift6002.m2.factory.FactoryLocator;
import ca.ulaval.ift6002.m2.file.CSVFileReader;
import ca.ulaval.ift6002.m2.file.FileReader;

public class CSVDrugParser implements FileParser<Drug> {

    private static final String DATA_FILE_PATH = "/drug.txt";

    private static final int DIN_COLUMN = 3;
    private static final int BRAND_NAME_COLUMN = 4;
    private static final int DESCRIPTOR_COLUMN = 5;

    private final FileReader<String[]> fileReader;
    private final DrugFactory drugFactory;
    private final InteractionParser interactionParser;

    public CSVDrugParser() {
        this.fileReader = new CSVFileReader();
        this.drugFactory = FactoryLocator.getDrugFactory();
        this.interactionParser = new InteractionParser();
    }

    @Override
    public List<Drug> parse() {
        Map<Din, List<Din>> interactingDins = interactionParser.parse();
        List<String[]> allLinesFromFile = fileReader.readAll(DATA_FILE_PATH);

        return fillDrugs(allLinesFromFile, interactingDins);
    }

    private List<Drug> fillDrugs(List<String[]> lines, Map<Din, List<Din>> interactingDins) {
        List<Drug> drugs = new ArrayList<Drug>();

        for (String[] line : lines) {
            Drug drugBuilt = hydrate(line, interactingDins);

            drugs.add(drugBuilt);
        }

        return drugs;
    }

    private Drug hydrate(String[] line, Map<Din, List<Din>> interactingDins) {
        Din din = new Din(line[DIN_COLUMN]);
        String brandName = line[BRAND_NAME_COLUMN];
        String descriptor = line[DESCRIPTOR_COLUMN];

        if (interactingDins.containsKey(din)) {
            return drugFactory.create(din, brandName, descriptor, interactingDins.get(din));
        } else {
            return drugFactory.create(din, brandName, descriptor);
        }
    }

    protected CSVDrugParser(FileReader<String[]> fileReader, DrugFactory drugFactory,
            InteractionParser interactionParser) {
        this.fileReader = fileReader;
        this.drugFactory = drugFactory;
        this.interactionParser = interactionParser;
    }

}
