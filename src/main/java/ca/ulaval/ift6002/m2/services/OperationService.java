package ca.ulaval.ift6002.m2.services;

import ca.ulaval.ift6002.m2.application.assemblers.InstrumentResponseAssembler;
import ca.ulaval.ift6002.m2.application.responses.InstrumentResponse;
import ca.ulaval.ift6002.m2.application.validator.response.InvalidResponseException;
import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentRepository;
import ca.ulaval.ift6002.m2.domain.operation.Operation;
import ca.ulaval.ift6002.m2.domain.operation.OperationRepository;

public class OperationService {

    private InstrumentRepository instrumentRepository;
    private InstrumentResponseAssembler instrumentAssembler;
    private OperationRepository operationRepository;

    public OperationService(OperationRepository operationRepository, InstrumentRepository instrumentRepository,
            InstrumentResponseAssembler instrumentAssembler) {
        this.operationRepository = operationRepository;
        this.instrumentRepository = instrumentRepository;
        this.instrumentAssembler = instrumentAssembler;
    }

    public void saveInstrument(String operationId, InstrumentResponse instrumentDto) throws InvalidResponseException {
        Instrument instrument = instrumentAssembler.fromResponse(instrumentDto);
        Operation operation = operationRepository.get(Integer.valueOf(operationId));
        instrumentRepository.store(operation, instrument);
    }
}
