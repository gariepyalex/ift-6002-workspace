package ca.ulaval.ift6002.m2.domain.file;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

public class CSVFileReader implements FileReader<String[]> {

    @Override
    public List<String[]> readAll(String filename) {
        try {
            CSVReader fileReader = openFile(filename);

            return fileReader.readAll();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    private CSVReader openFile(String filename) {
        InputStream resource = getClass().getResourceAsStream(filename);

        return new CSVReader(new InputStreamReader(resource));
    }

}
