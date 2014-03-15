package ca.ulaval.ift6002.m2.application.assemblers;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6002.m2.application.responses.DrugResponse;
import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;

public class DrugResponseAssemblerTest {

    private static final Din DIN = new Din("A random din");
    private static final String BRAND_NAME = "A random brand name";
    private static final String DESCRIPTOR = "A random descriptor";

    private static final Drug DRUG = new Drug(DIN, BRAND_NAME, DESCRIPTOR);
    private static final DrugResponse DRUG_RESPONSE = new DrugResponse(DIN.toString(), BRAND_NAME, DESCRIPTOR);

    private static final Collection<Drug> DRUGS = Arrays.asList(DRUG);
    private static final DrugResponse[] DRUG_RESPONSES = { DRUG_RESPONSE };

    private DrugResponseAssembler drugAssembler;

    @Before
    public void setUp() {
        drugAssembler = new DrugResponseAssembler();
    }

    @Test
    public void givenDrugWhenConvertToResponseShouldReturnGivenDrugResponse() {
        DrugResponse responseBuilt = drugAssembler.toResponse(DRUG);

        assertDrugResponseEquals(DRUG_RESPONSE, responseBuilt);
    }

    @Test
    public void givenDrugsWhenConvertToResponsesShouldReturnGivenDrugResponses() {
        DrugResponse[] responsesBuilt = drugAssembler.toResponses(DRUGS);

        assertDrugResponsesEquals(responsesBuilt, DRUG_RESPONSES);
    }

    private void assertDrugResponsesEquals(DrugResponse[] expected, DrugResponse[] actual) {
        for (int i = 0; i < expected.length; ++i) {
            assertDrugResponseEquals(expected[i], actual[i]);
        }
    }

    private void assertDrugResponseEquals(DrugResponse expected, DrugResponse actual) {
        assertEquals(expected.din, actual.din);
        assertEquals(expected.brandName, actual.brandName);
        assertEquals(expected.description, actual.description);
    }

}
