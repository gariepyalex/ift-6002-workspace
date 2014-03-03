package ca.ulaval.ift6002.m2.services;

import ca.ulaval.ift6002.m2.application.assemblers.InstrumentResponseAssembler;
import ca.ulaval.ift6002.m2.application.assemblers.OperationResponseAssembler;
import ca.ulaval.ift6002.m2.application.responses.InstrumentResponse;
import ca.ulaval.ift6002.m2.application.responses.OperationResponse;
import ca.ulaval.ift6002.m2.application.validator.response.InvalidResponseException;
import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentStatus;
import ca.ulaval.ift6002.m2.domain.instrument.Serial;
import ca.ulaval.ift6002.m2.domain.operation.Operation;
import ca.ulaval.ift6002.m2.domain.operation.OperationRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.locator.RepositoryLocator;

public class OperationService {

    private final InstrumentResponseAssembler instrumentAssembler;
    private final OperationResponseAssembler operationAssembler;
    private final OperationRepository operationRepository;

    protected OperationService(OperationRepository operationRepository, OperationResponseAssembler operationAssembler,
            InstrumentResponseAssembler instrumentAssembler) {
        this.operationRepository = operationRepository;
        this.operationAssembler = operationAssembler;
        this.instrumentAssembler = instrumentAssembler;
    }

    public OperationService() {
        this.operationRepository = RepositoryLocator.getOperationRepository();
        this.operationAssembler = new OperationResponseAssembler();
        this.instrumentAssembler = new InstrumentResponseAssembler();
    }

    public Integer saveOperation(OperationResponse operationResponse) throws InvalidResponseException {
        Operation operation = operationAssembler.fromResponse(operationResponse);

        operationRepository.store(operation);

        return operation.getNumber();
    }

    public void saveInstrument(String operationNumber, InstrumentResponse instrumentResponse) {
        Instrument instrument = instrumentAssembler.fromResponse(instrumentResponse);
        Operation operation = operationRepository.get(Integer.valueOf(operationNumber));

        operation.add(instrument);

        operationRepository.store(operation);
    }

    public void modifyInstrumentStatus(String operationId, InstrumentResponse instrumentResponse) {
        Operation operation = operationRepository.get(Integer.valueOf(operationId));
        InstrumentStatus instrumentStatus = InstrumentStatus.determineFrom(instrumentResponse.status);
        Serial serial = new Serial(instrumentResponse.serial);

        operation.bookmarkInstrumentToStatus(serial, instrumentStatus);

        operationRepository.store(operation);
    }
}
