package ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.Collection;
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

    private static final PrescriptionDTO PRESCRIPTION_DTO = new PrescriptionDTO(PRACTITIONER_NAME, FORMATTED_DATE,
            RENEWALS, DRUG_DTO);
    private static final Prescription PRESCRIPTION = new Prescription(PRACTITIONER, DATE, RENEWALS, DRUG);

    private static final Collection<PrescriptionDTO> PRESCRIPTION_DTOS = Arrays.asList(PRESCRIPTION_DTO);
    private static final Collection<Prescription> PRESCRIPTIONS = Arrays.asList(PRESCRIPTION);

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
    public void givenPrescriptionWhenAssemblingToDTOShouldReturnCorrespondingDTO() {
        PrescriptionDTO dtoBuilt = prescriptionAssembler.toDTO(PRESCRIPTION);
        assertPrescriptionDTOEquals(PRESCRIPTION_DTO, dtoBuilt);
    }

    @Test
    public void givenPrescriptionWhenAssemblingToDTOShouldCallDrugDTOAssembler() {
        prescriptionAssembler.toDTO(PRESCRIPTION);
        verify(drugDTOAssembler).toDTO(DRUG);
    }

    @Test
    public void givenPrescriptionsWhenAssemblingToDTOsShouldReturnCorrespondingDTOs() {
        Collection<PrescriptionDTO> dtoBuilt = prescriptionAssembler.toDTOs(PRESCRIPTIONS);
        assertPrescriptionDTOEquals(PRESCRIPTION_DTOS, dtoBuilt);
    }

    @Test
    public void givenPrescriptionsWhenAssemblingToDTOsShouldCallDrugDTOAssemblerOneTime() {
        prescriptionAssembler.toDTOs(PRESCRIPTIONS);
        verify(drugDTOAssembler, times(1)).toDTO(DRUG);
    }

    @Test
    public void givenPrescriptionDTOWhenFromDTOShouldReturnCorrespondingPrescription() {
        Prescription prescriptionBuilt = prescriptionAssembler.fromDTO(PRESCRIPTION_DTO);
        assertEquals(PRESCRIPTION, prescriptionBuilt);
    }

    @Test
    public void givenPrescriptionDTOWhenFromDTOShouldCallDrugDTOAssembler() {
        prescriptionAssembler.fromDTO(PRESCRIPTION_DTO);
        verify(drugDTOAssembler).fromDTO(DRUG_DTO);
    }

    @Test
    public void givenPrescriptionDTOsWhenFromDTOsShouldReturnCorrespondingPrescriptions() {
        Collection<Prescription> prescriptionsBuilt = prescriptionAssembler.fromDTOs(PRESCRIPTION_DTOS);
        assertPrescriptionsEquals(PRESCRIPTIONS, prescriptionsBuilt);
    }

    @Test
    public void givenPrescriptionDTOsWhenFromDTOsShouldCallDrugDTOAssemblerOneTime() {
        prescriptionAssembler.fromDTOs(PRESCRIPTION_DTOS);
        verify(drugDTOAssembler, times(1)).fromDTO(DRUG_DTO);
    }

    private void assertPrescriptionDTOEquals(PrescriptionDTO expected, PrescriptionDTO actual) {
        assertEquals(expected.practitioner, actual.practitioner);
        assertEquals(expected.date, actual.date);
        assertEquals(expected.renewals, actual.renewals);
    }

    private void assertPrescriptionDTOEquals(Collection<PrescriptionDTO> expected, Collection<PrescriptionDTO> actual) {
        PrescriptionDTO[] expectedArray = expected.toArray(new PrescriptionDTO[expected.size()]);
        PrescriptionDTO[] actualArray = actual.toArray(new PrescriptionDTO[actual.size()]);

        for (int i = 0; i < expectedArray.length; ++i) {
            assertPrescriptionDTOEquals(expectedArray[i], actualArray[i]);
        }
    }

    private void assertPrescriptionsEquals(Collection<Prescription> expected, Collection<Prescription> actual) {
        Prescription[] expectedArray = expected.toArray(new Prescription[expected.size()]);
        Prescription[] actualArray = actual.toArray(new Prescription[actual.size()]);

        for (int i = 0; i < expectedArray.length; ++i) {
            assertEquals(expectedArray[i], actualArray[i]);
        }
    }
}
