package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.operation.Operation;
import ca.ulaval.ift6002.m2.domain.operation.OperationStatus;
import ca.ulaval.ift6002.m2.domain.operation.OperationType;
import ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers.OperationDTOAssembler;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.InstrumentDTO;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.OperationDTO;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.PatientDTO;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.PrescriptionDTO;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.RoomDTO;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.SurgeonDTO;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerProvider;

@RunWith(MockitoJUnitRunner.class)
public class OperationHibernateRepositoryTest {

    private static final int PATIENT_NUMBER = 1;
    private static final Collection<PrescriptionDTO> PRESCRIPTION_DTOS = new ArrayList<PrescriptionDTO>();
    private static final PatientDTO PATIENT_DTO = new PatientDTO(PATIENT_NUMBER, PRESCRIPTION_DTOS);

    private static final String INSTRUMENT_SERIAL = "a serial";
    private static final String INSTRUMENT_TYPECODE = "A typecode";
    private static final String INSTRUMENT_STATUS = "A status";

    private static final InstrumentDTO INSTRUMENT_DTO = new InstrumentDTO(INSTRUMENT_SERIAL, INSTRUMENT_TYPECODE,
            INSTRUMENT_STATUS);
    private static final Collection<InstrumentDTO> INSTRUMENT_DTOS = Arrays.asList(INSTRUMENT_DTO);

    private static final int SURGEON_NUMBER = 1;
    private static final SurgeonDTO SURGEON_DTO = new SurgeonDTO(SURGEON_NUMBER);

    private static final String ROOM_NAME = "A room name";
    private static final RoomDTO ROOM_DTO = new RoomDTO(ROOM_NAME);

    private static final String OPERATION_TYPE = OperationType.EYE.toString();

    private static final String DESCRIPTION = "A Description";
    private static final String OPERATIONSTATUS = OperationStatus.FINISH.toString();

    private static final String FORMATTED_DATE = "2014-01-03T12:00:00";

    private static final Integer OPERATION_NUMBER = 1;
    private static final Integer UNEXISTING_OPERATION_NUMBER = 0;

    private static final OperationDTO OPERATION_DTO = new OperationDTO(FORMATTED_DATE, OPERATIONSTATUS, DESCRIPTION,
            PATIENT_DTO, INSTRUMENT_DTOS, SURGEON_DTO, ROOM_DTO, OPERATION_TYPE, OPERATION_NUMBER);

    // private static final OperationDTO OPERATION_DTO =
    // mock(OperationDTO.class);
    private Operation OPERATION;

    @Mock
    private EntityManagerProvider entityManagerProvider;

    @Mock
    private EntityManager entityManager;

    @Mock
    private OperationDTOAssembler operationDTOAssembler;

    @Mock
    private EntityTransaction transaction;

    @InjectMocks
    private OperationHibernateRepository operationRepository;

    @Before
    public void setUp() {
        willReturn(entityManager).given(entityManagerProvider).getEntityManager();
        willReturn(transaction).given(entityManager).getTransaction();
        OPERATION = mock(Operation.class);
    }

    @Test
    public void whenGettingOperationShouldCallOperationDTOAssemblerFromDTO() {
        willReturn(OPERATION_DTO).given(entityManager).find(OperationDTO.class, OPERATION_NUMBER);
        operationRepository.get(OPERATION_NUMBER);
        verify(operationDTOAssembler).fromDTO(OPERATION_DTO);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGettingUnknownPatientShouldThowException() {
        willReturn(null).given(entityManager).find(OperationDTO.class, UNEXISTING_OPERATION_NUMBER);

        operationRepository.get(UNEXISTING_OPERATION_NUMBER);
    }

    @Test
    public void whenGettingPatientShouldCallEntityManagerFind() {
        willReturn(OPERATION_DTO).given(entityManager).find(OperationDTO.class, OPERATION_NUMBER);
        operationRepository.get(OPERATION_NUMBER);
        verify(entityManager).find(OperationDTO.class, OPERATION_NUMBER);
    }

    @Test
    public void whenStoreOperationShouldCallOperationDTOAssemblerToDTO() {
        willReturn(OPERATION_DTO).given(entityManager).merge(any(OperationDTO.class));

        operationRepository.store(OPERATION);

        verify(operationDTOAssembler).toDTO(OPERATION);
    }

    @Test
    public void whenStoreOperationShouldCallEntityManagerMerge() {
        willReturn(OPERATION_DTO).given(entityManager).merge(any(OperationDTO.class));

        operationRepository.store(OPERATION);

        verify(entityManager).merge(any(OperationDTO.class));
    }

    @Test
    public void whenStoreOperationShouldCallUpdateOperationNumber() {
        willReturn(OPERATION_DTO).given(entityManager).merge(any(OperationDTO.class));

        operationRepository.store(OPERATION);

        verify(OPERATION).updateNumber(OPERATION_NUMBER);
    }
}
