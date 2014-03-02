package ca.ulaval.ift6002.m2.services;

import ca.ulaval.ift6002.m2.application.assemblers.InstrumentResponseAssembler;
import ca.ulaval.ift6002.m2.application.assemblers.OperationResponseAssembler;
import ca.ulaval.ift6002.m2.application.responses.InstrumentResponse;
import ca.ulaval.ift6002.m2.application.responses.OperationResponse;
import ca.ulaval.ift6002.m2.application.validator.response.InvalidResponseException;
import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.operation.Operation;
import ca.ulaval.ift6002.m2.domain.operation.OperationRepository;

public class OperationService {

    private InstrumentResponseAssembler instrumentAssembler;
    private OperationResponseAssembler operationAssembler;
    private OperationRepository operationRepository;

    public OperationService(OperationRepository operationRepository, OperationResponseAssembler operationAssembler,
            InstrumentResponseAssembler instrumentAssembler) {
        this.operationRepository = operationRepository;
        this.operationAssembler = operationAssembler;
        this.instrumentAssembler = instrumentAssembler;
    }

    public void saveOperation(OperationResponse operationResponse) throws InvalidResponseException {
        Operation operation = operationAssembler.fromResponse(operationResponse);
        operationRepository.store(operation);
    }

    public void saveInstrument(String operationId, InstrumentResponse instrumentResponse) {
        Instrument instrument = instrumentAssembler.fromResponse(instrumentResponse);
        Operation operation = operationRepository.getOperation(Integer.valueOf(operationId));
        operationRepository.storeInstrument(operation, instrument);
    }

    public void modifyInstrumentStatus(String instrumentId, InstrumentResponse response) {
        Instrument instrument = operationRepository.getInstrument(Integer.valueOf(instrumentId));
        operationRepository.modifyInstrumentStatus(instrument, response.status);
    }
}
