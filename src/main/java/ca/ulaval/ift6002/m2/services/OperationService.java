package ca.ulaval.ift6002.m2.services;

import ca.ulaval.ift6002.m2.application.assemblers.InstrumentResponseAssembler;
import ca.ulaval.ift6002.m2.application.responses.InstrumentResponse;
import ca.ulaval.ift6002.m2.application.validator.response.InvalidResponseException;
import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.operation.Operation;
import ca.ulaval.ift6002.m2.domain.operation.OperationRepository;

public class OperationService {

    private InstrumentResponseAssembler instrumentAssembler;
    private OperationRepository operationRepository;

    public OperationService(OperationRepository operationRepository, InstrumentResponseAssembler instrumentAssembler) {
        this.operationRepository = operationRepository;
        this.instrumentAssembler = instrumentAssembler;
    }

    public void saveInstrument(String operationId, InstrumentResponse instrumentDto) throws InvalidResponseException {
        Instrument instrument = instrumentAssembler.fromResponse(instrumentDto);
        Operation operation = operationRepository.getOperation(Integer.valueOf(operationId));
        operationRepository.storeInstrument(operation, instrument);
    }

    public void changeInstrumentStatus(String instrumentId, InstrumentResponse response) {
        Instrument instrument = operationRepository.getInstrument(Integer.valueOf(instrumentId));
        operationRepository.modifyInstrumentStatus(instrument, response.status);
    }
}
