package ca.ulaval.ift6002.m2.infrastructure.persistence.filler;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.willReturn;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.file.FileParser;

@RunWith(MockitoJUnitRunner.class)
public class DrugRepositoryFillerTest {

    private static final Drug DRUG_1 = new Drug(new Din("din 1"), "brand name 1", "descriptor 1");
    private static final Drug DRUG_2 = new Drug(new Din("din 2"), "brand name 2", "descriptor 2");
    private static final Drug DRUG_3 = new Drug(new Din("din 3"), "brand name 3", "descriptor 3");

    private static final List<Drug> ALL_DRUGS = Arrays.asList(DRUG_1, DRUG_2, DRUG_3);

    @Mock
    private FileParser<Drug> drugParser;

    @InjectMocks
    private DrugRepositoryFiller drugFiller;

    @Before
    public void setup() {
        willReturn(ALL_DRUGS).given(drugParser).parse();
    }

    @Test
    public void givenFillerWhenParsingShouldContainsThreeDrugs() {
        List<Drug> drugsFilled = drugFiller.fill();

        boolean hasFirstDrug = drugsFilled.contains(DRUG_1);
        boolean hasSecondDrug = drugsFilled.contains(DRUG_2);
        boolean hasThirdDrug = drugsFilled.contains(DRUG_3);

        assertTrue(hasFirstDrug);
        assertTrue(hasSecondDrug);
        assertTrue(hasThirdDrug);
    }
}
