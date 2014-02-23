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
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.prescription.Practitioner;
import ca.ulaval.ift6002.m2.domain.prescription.Prescription;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.PrescriptionDTO;

@RunWith(MockitoJUnitRunner.class)
public class PrescriptionDTOAssemblerTest {

    private static final String PRACTITIONER_NAME = "practitioner";
    private static final String FORMATTED_DATE = "date";
    private static final Integer RENEWALS = 15;

    private static final PrescriptionDTO PRESCRIPTION_DTO = new PrescriptionDTO(PRACTITIONER_NAME, FORMATTED_DATE,
            RENEWALS);

    private static final Practitioner PRACTITIONER = new Practitioner(PRACTITIONER_NAME);
    private static final Date DATE = new Date();
    private static final Drug DRUG = Drug.fromName("drug name");
    private static final Prescription PRESCRIPTION = new Prescription(PRACTITIONER, DATE, RENEWALS, DRUG);

    @Mock
    private DateFormatter dateFormatter;

    @InjectMocks
    private PrescriptionDTOAssembler prescriptionAssembler;

    @Test
    public void givenPrescriptionWhenAssemblingToDTOShouldReturnCorrespondingDTO() {
        willReturn(FORMATTED_DATE).given(dateFormatter).dateToString(any(Date.class));
        PrescriptionDTO dtoBuilt = prescriptionAssembler.toDTO(PRESCRIPTION);
        assertPrescriptionDTOEquals(PRESCRIPTION_DTO, dtoBuilt);
    }

    private void assertPrescriptionDTOEquals(PrescriptionDTO expected, PrescriptionDTO actual) {
        assertEquals(expected.practitioner, actual.practitioner);
        assertEquals(expected.date, actual.date);
        assertEquals(expected.renewals, actual.renewals);
    }
}
