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

    private static final String INSTRUMENT_ID = "1313131";
    private static final String OPERATION_ID = "1232";
    private static final InstrumentResponse INSTRUMENT_DTO = new InstrumentResponse("123", "UNUSED", "1312422");

    @InjectMocks
    private OperationService operationService;

    @Mock
    private InstrumentResponseAssembler instrumentAssembler;

    @Mock
    private OperationRepository operationRepository;

    @Mock
    private Instrument instrument;

    @Mock
    private Operation operation;

    @Test
    public void savingInstrumentShouldCallOperationRepositoryForStorage() throws InvalidResponseException {
        willReturn(instrument).given(instrumentAssembler).fromResponse(INSTRUMENT_DTO);
        willReturn(operation).given(operationRepository).getOperation(Integer.valueOf(OPERATION_ID));

        operationService.saveInstrument(OPERATION_ID, INSTRUMENT_DTO);

        verify(operationRepository).storeInstrument(operation, instrument);
    }

    @Test
    public void modifyingInstrumentStatusShouldCallOperationForModification() throws InvalidResponseException {
        willReturn(instrument).given(operationRepository).getInstrument(Integer.valueOf(INSTRUMENT_ID));

        operationService.changeInstrumentStatus(INSTRUMENT_ID, INSTRUMENT_DTO);

        verify(operationRepository).modifyInstrumentStatus(instrument, INSTRUMENT_DTO.status);
    }
}
