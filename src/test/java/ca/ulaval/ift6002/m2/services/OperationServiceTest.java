package ca.ulaval.ift6002.m2.services;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.application.assemblers.InstrumentResponseAssembler;
import ca.ulaval.ift6002.m2.application.responses.InstrumentResponse;
import ca.ulaval.ift6002.m2.application.validator.response.InvalidResponseException;
import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.operation.Operation;
import ca.ulaval.ift6002.m2.domain.operation.OperationRepository;

@RunWith(MockitoJUnitRunner.class)
public class OperationServiceTest {

    private static final String OPERATION_ID = "1232";

    @InjectMocks
    private OperationService operationService;

    @Mock
    private InstrumentResponse instrumentDto;

    @Mock
    private InstrumentResponseAssembler instrumentAssembler;

    @Mock
    private OperationRepository operationRepository;

    @Mock
    private Instrument instrument;

    @Mock
    private Operation operation;

    @Test
    public void savingInstrumentDtoShouldSaveRightInstrumentIntoInstrumentRepository() throws InvalidResponseException {
        willReturn(instrument).given(instrumentAssembler).fromResponse(instrumentDto);
        willReturn(operation).given(operationRepository).get(Integer.valueOf(OPERATION_ID));

        operationService.saveInstrument(OPERATION_ID, instrumentDto);

        verify(operationRepository).storeInstrument(operation, instrument);
    }
}
