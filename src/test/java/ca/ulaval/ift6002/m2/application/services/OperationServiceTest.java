package ca.ulaval.ift6002.m2.application.services;

import static org.mockito.BDDMockito.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.application.assemblers.InstrumentDTOAssembler;
import ca.ulaval.ift6002.m2.application.responses.InstrumentDTO;
import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentRepository;
import ca.ulaval.ift6002.m2.domain.operation.Operation;
import ca.ulaval.ift6002.m2.domain.operation.OperationRepository;

@RunWith(MockitoJUnitRunner.class)
public class OperationServiceTest {

    private static final String OPERATION_ID = "1232";

    // @InjectMocks
    private OperationService operationService;

    @Mock
    private InstrumentDTO instrumentDto;

    @Mock
    private InstrumentDTOAssembler instrumentAssembler;

    @Mock
    private InstrumentRepository instrumentRepository;

    @Mock
    private Instrument instrument;

    @Mock
    private Operation operation;

    private OperationRepository operationRepository;

    @Before
    public void setup() {
        operationService = new OperationService(instrumentRepository, instrumentAssembler);
    }

    @Test
    public void savingInstrumentDtoShouldSaveRightInstrumentIntoInstrumentRepository() {
        operationService.saveInstrument(OPERATION_ID, instrumentDto);
        willReturn(instrument).given(instrumentAssembler).fromDTO(any(InstrumentDTO.class));
        willReturn(operation).given(operationRepository).get(Integer.valueOf(OPERATION_ID));

        verify(instrumentRepository).store(operation, instrument);
    }
}
