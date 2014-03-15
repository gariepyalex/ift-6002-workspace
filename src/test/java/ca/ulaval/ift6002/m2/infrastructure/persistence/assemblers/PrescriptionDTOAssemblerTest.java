package ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Date;

import org.junit.Before;
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
    private static final Practitioner PRACTITIONER = new Practitioner(PRACTITIONER_NAME);
    private static final Date DATE = new Date();
    private static final String FORMATTED_DATE = "2014-01-03T12:00:00";
    private static final Integer RENEWALS = 15;

    private static final Din DIN = new Din("A random din");
    private static final String BRAND_NAME = "A random brand name";
    private static final String DESCRIPTOR = "A random descriptor";

    private static final DrugDTO DRUG_DTO = new DrugDTO(DIN.toString(), BRAND_NAME, DESCRIPTOR);
    private static final Drug DRUG = new Drug(DIN, BRAND_NAME, DESCRIPTOR);

    private static final Drug DRUG_WITH_NAME = Drug.fromName(BRAND_NAME);

    private static final PrescriptionDTO PRESCRIPTION_DTO_WITH_DRUGNAME = new PrescriptionDTO(PRACTITIONER_NAME,
            FORMATTED_DATE, RENEWALS, BRAND_NAME, null);
    private static final PrescriptionDTO PRESCRIPTION_DTO_WITH_DRUG = new PrescriptionDTO(PRACTITIONER_NAME,
            FORMATTED_DATE, RENEWALS, "", DRUG_DTO);

    private static final Prescription PRESCRIPTION_WITH_DRUG = new Prescription(PRACTITIONER, DATE, RENEWALS, DRUG);
    private static final Prescription PRESCRIPTION_WITH_DRUGNAME = new Prescription(PRACTITIONER, DATE, RENEWALS,
            DRUG_WITH_NAME);

    @Mock
    private DateFormatter dateFormatter;

    @Mock
    private DrugDTOAssembler drugDTOAssembler;

    @InjectMocks
    private PrescriptionDTOAssembler prescriptionAssembler;

    @Before
    public void setUp() {
        willReturn(FORMATTED_DATE).given(dateFormatter).dateToString(any(Date.class));
        willReturn(DATE).given(dateFormatter).parse(FORMATTED_DATE);
        willReturn(DRUG_DTO).given(drugDTOAssembler).toDTO(DRUG);
        willReturn(DRUG).given(drugDTOAssembler).fromDTO(DRUG_DTO);
    }

    @Test
    public void givenPrescriptionWithDrugWhenAssemblingToDTOShouldReturnCorrespondingDTO() {
        PrescriptionDTO dtoBuilt = prescriptionAssembler.toDTO(PRESCRIPTION_WITH_DRUG);

        assertPrescriptionDTOEquals(PRESCRIPTION_DTO_WITH_DRUG, dtoBuilt);
    }

    @Test
    public void givenPrescriptionWithDrugWhenAssemblingToDTOShouldCallDrugDTOAssembler() {
        prescriptionAssembler.toDTO(PRESCRIPTION_WITH_DRUG);

        verify(drugDTOAssembler).toDTO(DRUG);
    }

    @Test
    public void givenPrescriptionWithDrugNameWhenAssemblingToDTOShouldReturnCorrespondingDTO() {
        PrescriptionDTO dtoBuilt = prescriptionAssembler.toDTO(PRESCRIPTION_WITH_DRUGNAME);

        assertPrescriptionDTOEquals(PRESCRIPTION_DTO_WITH_DRUGNAME, dtoBuilt);
    }

    @Test
    public void givenPrescriptionWithDrugNameWhenAssemblingToDTOShouldNotCallDrugDTOAssembler() {
        prescriptionAssembler.toDTO(PRESCRIPTION_WITH_DRUGNAME);

        verify(drugDTOAssembler, times(0)).toDTO(DRUG);
    }

    @Test
    public void givenPrescriptionDTOWithDrugWhenFromDTOShouldReturnCorrespondingPrescription() {
        Prescription prescriptionBuilt = prescriptionAssembler.fromDTO(PRESCRIPTION_DTO_WITH_DRUG);

        assertEquals(PRESCRIPTION_WITH_DRUG, prescriptionBuilt);
    }

    @Test
    public void givenPrescriptionDTOWithDrugWhenFromDTOShouldCallDrugDTOAssembler() {
        prescriptionAssembler.fromDTO(PRESCRIPTION_DTO_WITH_DRUG);

        verify(drugDTOAssembler).fromDTO(DRUG_DTO);
    }

    @Test
    public void givenPrescriptionDTOWithDrugNameWhenFromDTOShouldReturnCorrespondingPrescription() {
        Prescription prescriptionBuilt = prescriptionAssembler.fromDTO(PRESCRIPTION_DTO_WITH_DRUGNAME);

        assertEquals(PRESCRIPTION_WITH_DRUGNAME, prescriptionBuilt);
    }

    @Test
    public void givenPrescriptionDTOWithDrugNameWhenFromDTOShouldCallDrugDTOAssembler() {
        prescriptionAssembler.fromDTO(PRESCRIPTION_DTO_WITH_DRUGNAME);

        verify(drugDTOAssembler, times(0)).fromDTO(DRUG_DTO);
    }

    private void assertPrescriptionDTOEquals(PrescriptionDTO expected, PrescriptionDTO actual) {
        assertEquals(expected.practitioner, actual.practitioner);
        assertEquals(expected.date, actual.date);
        assertEquals(expected.renewals, actual.renewals);
    }

}
