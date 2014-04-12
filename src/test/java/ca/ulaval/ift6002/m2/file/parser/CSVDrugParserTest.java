package ca.ulaval.ift6002.m2.file.parser;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.DrugFactory;
import ca.ulaval.ift6002.m2.file.reader.FileReader;

@RunWith(MockitoJUnitRunner.class)
public class CSVDrugParserTest {

    private static final String[] FIRST_DRUG_DATA = { "", "", "", "DIN_1", "BRAND_NAME_1", "DESCRIPTOR_1" };
    private static final String[] SECOND_DRUG_DATA = { "", "", "", "DIN_2", "BRAND_NAME_2", "DESCRIPTOR_2" };
    private static final List<String[]> ALL_DRUGS_DATA = Arrays.asList(FIRST_DRUG_DATA, SECOND_DRUG_DATA);

    @Mock
    private DrugFactory drugFactory;

    @Mock
    private InteractionParser interactionParser;

    @Mock
    private FileReader<String[]> fileReader;

    @InjectMocks
    private CSVDrugParser drugParser;

    @Before
    public void setUp() {
        willReturn(ALL_DRUGS_DATA).given(fileReader).readAll(anyString());
    }

    @Test
    public void givenParserWhenParsingShouldCallCreateFactoryTwoTime() {
        drugParser.parse();

        verify(drugFactory, times(2)).create(any(Din.class), anyString(), anyString());
    }
}
