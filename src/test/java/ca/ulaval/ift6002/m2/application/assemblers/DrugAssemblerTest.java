package ca.ulaval.ift6002.m2.application.assemblers;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6002.m2.application.responses.DrugResponse;
import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;

public class DrugAssemblerTest {

    private static final Din DIN = new Din("A random din");
    private static final String BRAND_NAME = "A random brand name";
    private static final String DESCRIPTOR = "A random descriptor";

    private static final DrugResponse DRUG_RESPONSE = new DrugResponse(DIN.toString(), BRAND_NAME, DESCRIPTOR);

    private static final DrugResponse[] DRUG_RESPONSES = { DRUG_RESPONSE };

    private Drug drug;
    private Collection<Drug> drugs;

    private DrugAssembler drugAssembler;

    @Before
    public void setUp() {
        drugAssembler = new DrugAssembler();
    }

    @Test
    public void givenDrugWhenConvertToResponseShouldReturnGivenDrugResponse() {
        setupDrug();

        DrugResponse responseBuilt = drugAssembler.toResponse(drug);

        assertDrugResponseEquals(DRUG_RESPONSE, responseBuilt);
    }

    @Test
    public void givenDrugsWhenConvertToResponsesShouldReturnGivenDrugResponses() {
        setupDrugs();

        DrugResponse[] responsesBuilt = drugAssembler.toResponses(drugs);

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

    private void setupDrug() {
        drug = mock(Drug.class);

        willReturn(DIN).given(drug).getDin();
        willReturn(BRAND_NAME).given(drug).getBrandName();
        willReturn(DESCRIPTOR).given(drug).getDescriptor();
    }

    private void setupDrugs() {
        setupDrug();
        drugs = Arrays.asList(drug);
    }
}
