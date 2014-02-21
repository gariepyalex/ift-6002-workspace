package ca.ulaval.ift6002.m2.services;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.application.assemblers.InstrumentDTOAssembler;
import ca.ulaval.ift6002.m2.application.responses.InstrumentDTO;
import ca.ulaval.ift6002.m2.application.validator.dto.InvalidDTOException;
import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentRepository;
import ca.ulaval.ift6002.m2.domain.operation.Operation;
import ca.ulaval.ift6002.m2.domain.operation.OperationRepository;

@RunWith(MockitoJUnitRunner.class)
public class OperationServiceTest {

    private static final String OPERATION_ID = "1232";

    @InjectMocks
    private OperationService operationService;

    @Mock
    private InstrumentDTO instrumentDto;

    @Mock
    private InstrumentDTOAssembler instrumentAssembler;

    @Mock
    private InstrumentRepository instrumentRepository;

    @Mock
    private OperationRepository operationRepository;

    @Mock
    private Instrument instrument;

    @Mock
    private Operation operation;

    @Test
    public void savingInstrumentDtoShouldSaveRightInstrumentIntoInstrumentRepository() throws InvalidDTOException {
        willReturn(instrument).given(instrumentAssembler).fromDTO(instrumentDto);
        willReturn(operation).given(operationRepository).get(Integer.valueOf(OPERATION_ID));

        operationService.saveInstrument(OPERATION_ID, instrumentDto);

        verify(instrumentRepository).store(operation, instrument);
    }
}
