package ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.DrugDTO;

public class DrugDTOAssemblerTest {
    private static final Din DIN = new Din("A random din");
    private static final String BRAND_NAME = "A random brand name";
    private static final String DESCRIPTOR = "A random descriptor";

    private static final Drug DRUG = new Drug(DIN, BRAND_NAME, DESCRIPTOR);
    private static final DrugDTO DRUG_DTO = new DrugDTO(DIN.toString(), BRAND_NAME, DESCRIPTOR);

    private static final Collection<DrugDTO> DRUG_DTOS = Arrays.asList(DRUG_DTO);
    private static final Collection<Drug> DRUGS = Arrays.asList(DRUG);

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

        assertEquals(DRUG, drug);
    }

    @Test
    public void givenDrugDTOsWhenConvertToDrugsShouldReturnGivenDrugs() {
        Collection<Drug> drugsBuilt = drugAssembler.fromDTOs(DRUG_DTOS);

        assertEquals(DRUGS, drugsBuilt);
    }

    @Test
    public void givenDrugsWhenConvertToDrugDTOsShouldReturnGivenDrugDTOs() {
        Collection<DrugDTO> dtosBuilt = drugAssembler.toDTOs(DRUGS);

        assertDrugDTOEquals(DRUG_DTOS, dtosBuilt);
    }

    private void assertDrugDTOEquals(DrugDTO expected, DrugDTO actual) {
        assertEquals(expected.din, actual.din);
        assertEquals(expected.brandName, actual.brandName);
        assertEquals(expected.descriptor, actual.descriptor);
    }

    private void assertDrugDTOEquals(Collection<DrugDTO> expected, Collection<DrugDTO> actual) {
        DrugDTO[] expectedArray = expected.toArray(new DrugDTO[expected.size()]);
        DrugDTO[] actualArray = actual.toArray(new DrugDTO[actual.size()]);

        for (int i = 0; i < expectedArray.length; ++i) {
            assertDrugDTOEquals(expectedArray[i], actualArray[i]);
        }
    }
}
