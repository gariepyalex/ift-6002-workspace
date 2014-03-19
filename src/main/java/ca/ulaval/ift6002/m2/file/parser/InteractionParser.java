package ca.ulaval.ift6002.m2.file.parser;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.patient.Interaction;
import ca.ulaval.ift6002.m2.file.FileReader;

public class InteractionParser implements FileParser<Interaction> {

    private static final String DATA_FILE_PATH = "/Interaction.txt";
    private static final String REGEX_SEPARATOR = "=>|,";

    private final FileReader<String> interactionFileReader;

    public InteractionParser(FileReader<String> interactionFileReader) {
        this.interactionFileReader = interactionFileReader;
    }

    @Override
    public List<Interaction> parse() {
        List<String> lines = interactionFileReader.readAll(DATA_FILE_PATH);
        List<Interaction> interactions = parseLinesToInteractions(lines);
        return interactions;
    }

    private List<Interaction> parseLinesToInteractions(List<String> lines) {
        List<Interaction> interactions = new ArrayList<Interaction>();

        for (String line : lines) {
            List<Din> dinsFromLine = getDinsFromLine(line);
            Din dinsFromWhichInteractionsAreChecked = dinsFromLine.remove(0);
            List<Din> interactingDins = dinsFromLine;
            interactions.add(new Interaction(dinsFromWhichInteractionsAreChecked, interactingDins));
        }

        return interactions;
    }

    private List<Din> getDinsFromLine(String line) {
        String[] dinStringsFromLine = line.split(REGEX_SEPARATOR);
        List<Din> dinsFromLine = new ArrayList<Din>();

        for (String dinString : dinStringsFromLine) {
            dinsFromLine.add(new Din(dinString));
        }

        return dinsFromLine;
    }
}
