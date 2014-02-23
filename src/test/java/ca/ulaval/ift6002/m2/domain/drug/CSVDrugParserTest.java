package ca.ulaval.ift6002.m2.domain.drug;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.anyString;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.file.FileReader;

@RunWith(MockitoJUnitRunner.class)
public class CSVDrugParserTest {

    private static final String DIN_1 = "DIN_1";
    private static final String DIN_2 = "DIN_2";

    private static final String BRAND_NAME_1 = "BRAND_NAME_1";
    private static final String BRAND_NAME_2 = "BRAND_NAME_2";

    private static final String DESCRIPTOR_1 = "DESCRIPTOR_1";
    private static final String DESCRIPTOR_2 = "DESCRIPTOR_2";

    private static final String[] DRUG_DATA_1 = { "", "", "", DIN_1, BRAND_NAME_1, DESCRIPTOR_1 };
    private static final String[] DRUG_DATA_2 = { "", "", "", DIN_2, BRAND_NAME_2, DESCRIPTOR_2 };
    private static final List<String[]> ALL_DRUGS_DATA = Arrays.asList(DRUG_DATA_1, DRUG_DATA_2);

    private static final Drug DRUG_1 = new Drug(new Din(DIN_1), BRAND_NAME_1, DESCRIPTOR_1);
    private static final Drug DRUG_2 = new Drug(new Din(DIN_2), BRAND_NAME_2, DESCRIPTOR_2);
    private static final List<Drug> ALL_DRUGS = Arrays.asList(DRUG_1, DRUG_2);

    @Mock
    private FileReader<String[]> fileReader;

    @InjectMocks
    private CSVDrugParser drugParser;

    @Before
    public void setup() {
        willReturn(ALL_DRUGS_DATA).given(fileReader).readAll(anyString());
    }

    @Test
    public void givenParserWhenParsingShouldReturnListOfDrugs() {
        List<Drug> drugsParsed = drugParser.parse();

        assertEquals(ALL_DRUGS, drugsParsed);
    }

}
