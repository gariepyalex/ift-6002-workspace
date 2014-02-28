package ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.prescription.Practitioner;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.DrugDTO;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.PrescriptionDTO;

@RunWith(MockitoJUnitRunner.class)
public class PrescriptionDTOAssemblerTest {

    private static final String PRACTITIONER_NAME = "practitioner";
    private static final String FORMATTED_DATE = "date";
    private static final Integer RENEWALS = 15;

    private static final Din DIN = new Din("A random din");
    private static final String BRAND_NAME = "A random brand name";
    private static final String DESCRIPTOR = "A random descriptor";

    private static final DrugDTO DRUG_DTO = new DrugDTO(DIN.toString(), BRAND_NAME, DESCRIPTOR);

    private static final PrescriptionDTO PRESCRIPTION_DTO = new PrescriptionDTO(PRACTITIONER_NAME, FORMATTED_DATE,
            RENEWALS, DRUG_DTO);

    private static final Practitioner PRACTITIONER = new Practitioner(PRACTITIONER_NAME);
    private static final Date DATE = new Date();
    private static final Drug DRUG = new Drug(DIN, BRAND_NAME, DESCRIPTOR);
    private static final Prescription PRESCRIPTION = new Prescription(PRACTITIONER, DATE, RENEWALS, DRUG);

    @Mock
    private DateFormatter dateFormatter;

    @Mock
    private DrugDTOAssembler drugDTOAssembler;

    @InjectMocks
    private PrescriptionDTOAssembler prescriptionAssembler;

    @Test
    public void givenPrescriptionWhenAssemblingToDTOShouldReturnCorrespondingDTO() {
        willReturn(FORMATTED_DATE).given(dateFormatter).dateToString(any(Date.class));
        willReturn(DRUG_DTO).given(drugDTOAssembler).toDTO(DRUG);
        PrescriptionDTO dtoBuilt = prescriptionAssembler.toDTO(PRESCRIPTION);
        assertPrescriptionDTOEquals(PRESCRIPTION_DTO, dtoBuilt);
    }

    private void assertPrescriptionDTOEquals(PrescriptionDTO expected, PrescriptionDTO actual) {
        assertEquals(expected.practitioner, actual.practitioner);
        assertEquals(expected.date, actual.date);
        assertEquals(expected.renewals, actual.renewals);
        assertDrugDTOEquals(expected.drugDTO, actual.drugDTO);
    }

    private void assertDrugDTOEquals(DrugDTO expected, DrugDTO actual) {
        assertEquals(expected.din, actual.din);
        assertEquals(expected.brandName, actual.brandName);
        assertEquals(expected.descriptor, actual.descriptor);
    }
}
