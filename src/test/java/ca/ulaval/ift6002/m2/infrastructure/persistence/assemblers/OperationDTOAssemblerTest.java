package ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.operation.Operation;
import ca.ulaval.ift6002.m2.domain.operation.OperationFactory;
import ca.ulaval.ift6002.m2.domain.operation.OperationStatus;
import ca.ulaval.ift6002.m2.domain.operation.OperationType;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.room.Room;
import ca.ulaval.ift6002.m2.domain.surgeon.Surgeon;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.InstrumentDTO;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.OperationDTO;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.PatientDTO;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.PrescriptionDTO;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.RoomDTO;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.SurgeonDTO;

@RunWith(MockitoJUnitRunner.class)
public class OperationDTOAssemblerTest {

    private static final int PATIENT_NUMBER = 11;
    private static final String ROOM_VALUE = "room";
    private static final int SURGEON_LICENSE = 123;
    private static final String STRING_OPERATION_STATUS = "PLANIFIEE";
    private static final String STRING_OPERATION_TYPE = "OEIL";

    private static final String DATE_STRING = "2014-01-01T12:00:00";
    private static final String DESCRIPTION = "description";
    private static final OperationType OPERATION_TYPE = OperationType.EYE;
    private static final OperationStatus OPERATION_STATUS = OperationStatus.PLANNED;
    private static final OperationFactory OPERATION_FACTORY = new OperationFactory();

    private static final Collection<InstrumentDTO> INSTRUMENTS_DTO = new ArrayList<InstrumentDTO>();
    private static final Collection<Instrument> INSTRUMENTS = new ArrayList<Instrument>();
    private static final Surgeon SURGEON = new Surgeon(SURGEON_LICENSE);
    private static final Room ROOM = new Room(ROOM_VALUE);
    private static final SurgeonDTO SURGEON_DTO = new SurgeonDTO(SURGEON_LICENSE);
    private static final RoomDTO ROOM_DTO = new RoomDTO(ROOM_VALUE);
    private static final Date DATE = new Date();
    private static final Patient PATIENT = new Patient(PATIENT_NUMBER);
    private static final PatientDTO PATIENT_DTO = new PatientDTO(PATIENT_NUMBER, new ArrayList<PrescriptionDTO>());

    private static final Operation operation = OPERATION_FACTORY.create(OPERATION_TYPE, DESCRIPTION, SURGEON, DATE,
            ROOM, OPERATION_STATUS, PATIENT);

    private static final OperationDTO OPERATION_DTO = new OperationDTO(DATE_STRING, STRING_OPERATION_STATUS,
            DESCRIPTION, PATIENT_DTO, INSTRUMENTS_DTO, SURGEON_DTO, ROOM_DTO, STRING_OPERATION_TYPE);

    @Mock
    private InstrumentDTOAssembler instrumentsDTOAssembler;

    @Mock
    private SurgeonDTOAssembler surgeonDTOAssembler;

    @Mock
    private RoomDTOAssembler roomDTOAssembler;

    @Mock
    private PatientDTOAssembler patientDTOAssembler;

    @Mock
    private DateFormatter dateFormatter;

    @Mock
    private OperationFactory operationFactory;

    @InjectMocks
    private OperationDTOAssembler operationAssembler;

    @Before
    public void setUp() {
        setToDTOAssembler();

        setFromDTOAssembler();
    }

    private void setFromDTOAssembler() {
        willReturn(operation).given(operationFactory).create(any(OperationType.class), anyString(), any(Surgeon.class),
                any(Date.class), any(Room.class), any(OperationStatus.class), any(Patient.class));
        willReturn(PATIENT).given(patientDTOAssembler).fromDTO(PATIENT_DTO);
        willReturn(SURGEON).given(surgeonDTOAssembler).fromDTO(SURGEON_DTO);
        willReturn(ROOM).given(roomDTOAssembler).fromDTO(ROOM_DTO);
        willReturn(DATE).given(dateFormatter).parse(DATE_STRING);
    }

    private void setToDTOAssembler() {
        willReturn(INSTRUMENTS_DTO).given(instrumentsDTOAssembler).toDTOs(operation.getInstruments());
        willReturn(SURGEON_DTO).given(surgeonDTOAssembler).toDTO(SURGEON);
        willReturn(ROOM_DTO).given(roomDTOAssembler).toDTO(ROOM);
        willReturn(PATIENT_DTO).given(patientDTOAssembler).toDTO(PATIENT);
        willReturn(DATE_STRING).given(dateFormatter).dateToString(DATE);
    }

    @Test
    public void givenOperationWhenConvertToDTOShouldReturnCorrespondingOperationDTO() {
        OperationDTO dtoBuilt = operationAssembler.toDTO(operation);

        assertOperationDTOEquals(OPERATION_DTO, dtoBuilt);
    }

    private void assertOperationDTOEquals(OperationDTO expected, OperationDTO actual) {
        assertEquals(expected.date, actual.date);
        assertEquals(expected.description, actual.description);
        assertEquals(expected.status, actual.status);
        assertEquals(expected.type, actual.type);
    }

    @Test
    public void givenOperationWhenConvertToDTORoomToDTOShouldBeCalled() {
        operationAssembler.toDTO(operation);

        verify(roomDTOAssembler).toDTO(ROOM);
    }

    @Test
    public void givenOperationWhenConvertToDTOSurgeonToDTOShouldBeCalled() {
        operationAssembler.toDTO(operation);

        verify(surgeonDTOAssembler).toDTO(SURGEON);
    }

    @Test
    public void givenOperationWhenConvertToDTOInstrumentToDTOShouldBeCalled() {
        operationAssembler.toDTO(operation);

        verify(instrumentsDTOAssembler).toDTOs(INSTRUMENTS);
    }

    @Test
    public void givenOperationWhenConvertToDTOPatientToDTOShouldBeCalled() {
        operationAssembler.toDTO(operation);

        verify(patientDTOAssembler).toDTO(PATIENT);
    }

    @Test
    public void givenOperationWhenConvertToDTODateFormatterDateToStringShouldBeCalled() {
        operationAssembler.toDTO(operation);

        verify(dateFormatter).dateToString(DATE);
    }

    @Test
    public void givenOperationDTOWhenConvertToOperationOperationFactoryCreateShouldBeCall() {
        operationAssembler.fromDTO(OPERATION_DTO);
        verify(operationFactory).create(OPERATION_TYPE, DESCRIPTION, SURGEON, DATE, ROOM, OPERATION_STATUS, PATIENT);
    }

    @Test
    public void givenOperationDTOWhenConvertToOperationPatientDtoAssemblerFromDtoShouldBeCall() {
        operationAssembler.fromDTO(OPERATION_DTO);
        verify(patientDTOAssembler).fromDTO(PATIENT_DTO);
    }

    @Test
    public void givenOperationDTOWhenConvertToOperationdateFormatterParseShouldBeCall() {
        operationAssembler.fromDTO(OPERATION_DTO);
        verify(dateFormatter).parse(DATE_STRING);
    }

    @Test
    public void givenOperationDTOWhenConvertToOperationSurgeonDTOAssemblerFromDtoShouldBeCall() {
        operationAssembler.fromDTO(OPERATION_DTO);
        verify(surgeonDTOAssembler).fromDTO(SURGEON_DTO);
    }

    @Test
    public void givenOperationDTOWhenConvertToOperationRoomDTOAssemblerFromDTOShouldBeCall() {
        operationAssembler.fromDTO(OPERATION_DTO);
        verify(roomDTOAssembler).fromDTO(ROOM_DTO);
    }
}
