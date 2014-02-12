package projectH.application.assemblers;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import projectH.application.responses.DrugDTO;
import projectH.domain.drug.Din;
import projectH.domain.drug.Drug;

public class DrugDTOAssemblerTest {

    private final static Din DIN = new Din("A random din");
    private final static String BRAND_NAME = "A random brand name";
    private final static String DESCRIPTOR = "A random descriptor";

    private final static Drug DRUG = new Drug(DIN, BRAND_NAME, DESCRIPTOR);
    private final static DrugDTO DRUG_DTO = new DrugDTO(DIN.toString(), BRAND_NAME, DESCRIPTOR);

    private final static Collection<Drug> DRUGS = Arrays.asList(DRUG);
    private final static DrugDTO[] DRUG_DTOS = { DRUG_DTO };

    private DrugDTOAssembler drugAssembler;

    @Before
    public void setup() {
        drugAssembler = new DrugDTOAssembler();
    }

    @Test
    public void givenDrugWhenConvertToDTOShouldReturnGivenDrugDTO() {
        DrugDTO dtoBuilt = drugAssembler.toDTO(DRUG);

        assertDrugDTOEquals(DRUG_DTO, dtoBuilt);
    }

    @Test
    public void givenDrugsWhenConvertToDTOsShouldReturnGivenDrugDTOs() {
        DrugDTO[] dtosBuilt = drugAssembler.toDTOs(DRUGS);

        assertDrugDTOsEquals(dtosBuilt, DRUG_DTOS);
    }

    private void assertDrugDTOsEquals(DrugDTO[] expected, DrugDTO[] actual) {
        for (int i = 0; i < expected.length; ++i) {
            assertDrugDTOEquals(expected[i], actual[i]);
        }
    }

    private void assertDrugDTOEquals(DrugDTO expected, DrugDTO actual) {
        assertEquals(expected.din, actual.din);
        assertEquals(expected.brandName, actual.brandName);
        assertEquals(expected.description, actual.description);
    }

}
