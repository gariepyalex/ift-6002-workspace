package ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.DrugDTO;

//TODO Refactor
public class DrugDTOAssemblerTest {
    private static final Din DIN = new Din("A random din");
    private static final String BRAND_NAME = "A random brand name";
    private static final String DESCRIPTOR = "A random descriptor";

    private static final Drug DRUG = null;// = new Drug(DIN, BRAND_NAME,
                                          // DESCRIPTOR);
    private static final DrugDTO DRUG_DTO = new DrugDTO(DIN.toString(), BRAND_NAME, DESCRIPTOR);

    private DrugDTOAssembler drugAssembler;

    @Before
    public void setUp() {
        drugAssembler = new DrugDTOAssembler();
    }

    @Test
    public void givenDrugWhenConvertToDTOShouldReturnGivenDrugDTO() {
        DrugDTO dtoBuilt = drugAssembler.toDTO(DRUG);

        assertDrugDTOEquals(DRUG_DTO, dtoBuilt);
    }

    @Test
    public void givenDrugDTOWhenConvertToDrugShouldReturnGivenDrug() {
        Drug drug = drugAssembler.fromDTO(DRUG_DTO);

        // assertEquals(DRUG, drug);
    }

    private void assertDrugDTOEquals(DrugDTO expected, DrugDTO actual) {
        assertEquals(expected.din, actual.din);
        assertEquals(expected.brandName, actual.brandName);
        assertEquals(expected.descriptor, actual.descriptor);
    }
}
