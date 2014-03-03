package ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.Date;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;

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
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.RoomDTO;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.SurgeonDTO;

public class OperationDTOAssemblerTest {

    private static final Integer ID = 1;

    private static final String STRING_OPERATION_STATUS = "PLANNED";

    private static final String DATE_STRING = "2014-01-01T12:00:00";
    private static final String DESCRIPTION = "description";
    private static final OperationType OPERATION_TYPE = OperationType.EYE;
    private static final OperationStatus OPERATION_STATUS = OperationStatus.PLANNED;
    private final OperationFactory operationFactory = new OperationFactory();

    private Operation operation;

    private OperationDTOAssember operationAssembler;

    @Mock
    private Surgeon surgeon;

    @Mock
    private InstrumentDTOAssembler instrumentsDTOAssembler;
    @Mock
    private Collection<InstrumentDTO> instrumentsDTO;

    @Mock
    private Date date;
    @Mock
    private Room room;
    @Mock
    SurgeonDTO surgeonDTO;
    @Mock
    private PatientDTO patientDTO;
    @Mock
    private Patient patient;

    RoomDTO roomDTO;

    private final OperationDTO OPERATION_DTO = new OperationDTO(ID, DATE_STRING, STRING_OPERATION_STATUS, DESCRIPTION,
            patientDTO, instrumentsDTO, surgeonDTO, roomDTO);

    @Before
    public void setUp() {

        operationAssembler = new OperationDTOAssember(instrumentsDTOAssembler);
        operation = operationFactory
                .create(OPERATION_TYPE, DESCRIPTION, surgeon, date, room, OPERATION_STATUS, patient);

    }

    @Test
    @Ignore
    public void givenOperationWhenConvertToDTOShouldReturnGivenOperationDTO() {

        OperationDTO dtoBuilt = operationAssembler.toDTO(operation);
        assertOperationDTOEquals(OPERATION_DTO, dtoBuilt);
    }

    private void assertOperationDTOEquals(OperationDTO expected, OperationDTO actual) {
        assertEquals(expected.date, actual.date);

    }
}
