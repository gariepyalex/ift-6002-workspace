package ca.ulaval.ift6002.m2.domain.file;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class CSVFileReaderTest {

    private static final String UNEXISTING_FILENAME = "abc";

    private static final List<String[]> EMPTY_LIST = Collections.emptyList();

    // TODO test with existing file... mock file? how?

    @Test
    public void givenFileReaderWhenReadAllWithUnexistingFilenameShouldReturnEmptyList() {
        CSVFileReader fileReader = new CSVFileReader();
        List<String[]> result = fileReader.readAll(UNEXISTING_FILENAME);
        assertEquals(EMPTY_LIST, result);
    }
}
