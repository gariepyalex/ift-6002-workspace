package ca.ulaval.ift6002.m2.file.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.drug.DrugFactory;
import ca.ulaval.ift6002.m2.file.CSVFileReader;
import ca.ulaval.ift6002.m2.file.FileReader;
import ca.ulaval.ift6002.m2.locator.FactoryLocator;

public class CSVDrugParser implements FileParser<Drug> {

    private static final int DIN_COLUMN = 3;
    private static final int BRAND_NAME_COLUMN = 4;
    private static final int DESCRIPTOR_COLUMN = 5;

    private final FileReader<String[]> fileReader;
    private final DrugFactory drugFactory;
    private final InteractionParser interactionParser;
    private final String dataFilePath; // = "/drug.txt";

    public CSVDrugParser(String dataFilePath) {
        this.fileReader = new CSVFileReader();
        this.drugFactory = FactoryLocator.getDrugFactory();
        this.interactionParser = new InteractionParser();
        this.dataFilePath = dataFilePath;
    }

    @Override
    public List<Drug> parse() {
        List<String[]> allLinesFromFile = fileReader.readAll(dataFilePath);

        Map<Din, List<Din>> interactingDins = interactionParser.parse();
        Map<Din, Drug> filledDrugs = fillDrugsFromLines(allLinesFromFile);
        List<Drug> drugs = linkInteractingDrugs(filledDrugs, interactingDins);

        return drugs;
    }

    private Map<Din, Drug> fillDrugsFromLines(List<String[]> lines) {
        Map<Din, Drug> drugs = new HashMap<>();

        for (String[] line : lines) {
            Din din = new Din(line[DIN_COLUMN]);
            String brandName = line[BRAND_NAME_COLUMN];
            String descriptor = line[DESCRIPTOR_COLUMN];

            Drug drugBuilt = drugFactory.create(din, brandName, descriptor);

            drugs.put(din, drugBuilt);
        }

        return drugs;
    }

    private List<Drug> linkInteractingDrugs(Map<Din, Drug> drugs, Map<Din, List<Din>> interactingDins) {
        List<Drug> drugsLinked = new ArrayList<>();

        for (Entry<Din, Drug> drugEntry : drugs.entrySet()) {
            Din currentDin = drugEntry.getKey();
            Drug currentDrug = drugEntry.getValue();

            linkDrugWithItInteractions(currentDin, currentDrug, drugs, interactingDins);

            drugsLinked.add(currentDrug);
        }

        return drugsLinked;
    }

    private void linkDrugWithItInteractions(Din din, Drug drug, Map<Din, Drug> allDrugs,
            Map<Din, List<Din>> interactingDins) {
        if (interactingDins.containsKey(din)) {
            List<Din> dins = interactingDins.get(din);
            Collection<Drug> interactingDrugs = convertDinsIntoDrugs(allDrugs, dins);

            drug.interactWith(interactingDrugs);
        }
    }

    private Collection<Drug> convertDinsIntoDrugs(Map<Din, Drug> drugs, List<Din> dins) {
        Collection<Drug> drugsConverted = new ArrayList<>();

        for (Din din : dins) {
            Drug drugFound = drugs.get(din);

            drugsConverted.add(drugFound);
        }

        return drugsConverted;
    }

    protected CSVDrugParser(FileReader<String[]> fileReader, DrugFactory drugFactory,
            InteractionParser interactionParser, String dataFilePath) {
        this.fileReader = fileReader;
        this.drugFactory = drugFactory;
        this.interactionParser = interactionParser;
        this.dataFilePath = dataFilePath;
    }

}
