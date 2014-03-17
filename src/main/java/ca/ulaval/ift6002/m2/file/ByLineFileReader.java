package ca.ulaval.ift6002.m2.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ByLineFileReader implements FileReader<String> {

    @Override
    public List<String> readAll(String filePath) {
        List<String> lines;
        try {
            Scanner scanner = getScannerOnFile(filePath);
            lines = getAllLinesFromScanner(scanner);
            scanner.close();
        } catch (FileNotFoundException e) {
            // TODO how to handle the exception (waiting teacher's answer)
            e.printStackTrace();
            lines = Collections.emptyList();
        }

        return lines;
    }

    private List<String> getAllLinesFromScanner(Scanner scanner) {
        List<String> lines = new ArrayList<String>();
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
        return lines;
    }

    private Scanner getScannerOnFile(String filePath) throws FileNotFoundException {
        File interactionFile = new File(filePath);
        Scanner scanner = new Scanner(interactionFile);
        return scanner;
    }

}
