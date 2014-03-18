package ca.ulaval.ift6002.m2.services;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.application.assemblers.InstrumentAssembler;
import ca.ulaval.ift6002.m2.application.assemblers.OperationAssembler;
import ca.ulaval.ift6002.m2.application.requests.InstrumentRequest;
import ca.ulaval.ift6002.m2.application.requests.OperationRequest;
import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentStatus;
import ca.ulaval.ift6002.m2.domain.instrument.Serial;
import ca.ulaval.ift6002.m2.domain.operation.Operation;
import ca.ulaval.ift6002.m2.domain.operation.OperationRepository;

@RunWith(MockitoJUnitRunner.class)
public class OperationServiceTest {

    private static final String OPERATION_ID = "1232";
    private static final String UNUSED = InstrumentStatus.UNUSED.toString();

    private static final InstrumentRequest INSTRUMENT_RESPONSE = new InstrumentRequest("123", UNUSED, "1312422");

    private static final OperationRequest OPERATION_RESPONSE = new OperationRequest("Cataracte à l'oeil gauche",
            101224, "0000-00-00T24:01:00", "blocB", "oeil", "en cours", 1);

    @Mock
    private OperationAssembler operationAssembler;

    @Mock
    private InstrumentAssembler instrumentAssembler;

    @Mock
    private OperationRepository operationRepository;

    @Mock
    private Instrument instrument;

    @Mock
    private Operation operation;

    @InjectMocks
    private OperationService operationService;

    @Before
    public void setUp() {
        willReturn(operation).given(operationRepository).get(Integer.valueOf(OPERATION_ID));
    }

    @Test
    public void whenSavingOperationShouldStoreUsingRepository() {
        willReturn(operation).given(operationAssembler).fromRequest(OPERATION_RESPONSE);
        operationService.saveOperation(OPERATION_RESPONSE);
        verify(operationRepository).store(operation);
    }

    @Test
    public void whenSavingInstrumentShouldAddInstrument() {
        willReturn(instrument).given(instrumentAssembler).fromRequest(INSTRUMENT_RESPONSE);
        operationService.saveInstrument(OPERATION_ID, INSTRUMENT_RESPONSE);
        verify(operation).add(instrument);
    }

    @Test
    public void whenSavingInstrumentShouldStoreOperationUsingRepository() {
        operationService.saveInstrument(OPERATION_ID, INSTRUMENT_RESPONSE);

        verify(operationRepository).store(operation);
    }

    @Test
    public void whenBookmarkingInstrumentShouldUpdateInstrumentStatus() {
        operationService.bookmarkInstrumentToStatus(OPERATION_ID, INSTRUMENT_RESPONSE);

        verify(operation).bookmarkInstrumentToStatus(any(Serial.class), any(InstrumentStatus.class));
    }

    @Test
    public void whenBookmarkingInstrumentShouldStoreOperationUsingRepository() {
        operationService.bookmarkInstrumentToStatus(OPERATION_ID, INSTRUMENT_RESPONSE);

        verify(operationRepository).store(operation);
    }
}
