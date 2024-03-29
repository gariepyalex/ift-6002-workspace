package ca.ulaval.ift6002.m2.file.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.file.reader.ByLineFileReader;
import ca.ulaval.ift6002.m2.file.reader.FileReader;

public class InteractionParser {

    private static final String KEY_REGEX_SEPARATOR = "=>";
    private static final String VALUE_REGEX_SEPARATOR = ",";

    private final FileReader<String> fileReader;
    private final String filePath;

    public InteractionParser(String filePath) {
        this.fileReader = new ByLineFileReader();
        this.filePath = filePath;
    }

    public Map<Din, List<Din>> parse() {
        List<String> lines = fileReader.readAll(filePath);

        return fillInteractions(lines);
    }

    private Map<Din, List<Din>> fillInteractions(List<String> lines) {
        Map<Din, List<Din>> interactions = new HashMap<>();

        for (String line : lines) {
            String[] splittedLine = line.split(KEY_REGEX_SEPARATOR);

            Din din = new Din(splittedLine[0]);
            List<Din> interactingDins = fillInteractingDins(splittedLine[1]);

            interactions.put(din, interactingDins);
        }

        return interactions;
    }

    private List<Din> fillInteractingDins(String dinValues) {
        List<Din> interactingDins = new ArrayList<>();
        String[] values = dinValues.split(VALUE_REGEX_SEPARATOR);

        for (String value : values) {
            Din current = new Din(value);
            interactingDins.add(current);
        }

        return interactingDins;
    }

    protected InteractionParser(FileReader<String> interactionFileReader, String filePath) {
        this.fileReader = interactionFileReader;
        this.filePath = filePath;
    }
}
