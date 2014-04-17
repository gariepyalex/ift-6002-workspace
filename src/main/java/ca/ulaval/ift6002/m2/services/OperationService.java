package ca.ulaval.ift6002.m2.services;

import ca.ulaval.ift6002.m2.application.assemblers.InstrumentAssembler;
import ca.ulaval.ift6002.m2.application.assemblers.OperationAssembler;
import ca.ulaval.ift6002.m2.application.requests.InstrumentRequest;
import ca.ulaval.ift6002.m2.application.requests.OperationRequest;
import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentStatus;
import ca.ulaval.ift6002.m2.domain.instrument.Serial;
import ca.ulaval.ift6002.m2.domain.operation.Operation;
import ca.ulaval.ift6002.m2.domain.operation.OperationRepository;
import ca.ulaval.ift6002.m2.locator.RepositoryLocator;

public class OperationService {

    private final InstrumentAssembler instrumentAssembler;
    private final OperationAssembler operationAssembler;
    private final OperationRepository operationRepository;

    public OperationService() {
        this.operationRepository = RepositoryLocator.getOperationRepository();
        this.operationAssembler = new OperationAssembler();
        this.instrumentAssembler = new InstrumentAssembler();
    }

    public Integer saveOperation(OperationRequest operationRequest) {
        Operation operation = operationAssembler.fromRequest(operationRequest);

        operationRepository.store(operation);

        return operation.getNumber();
    }

    public void saveInstrument(String operationNumber, InstrumentRequest instrumentResponse) {
        Instrument instrument = instrumentAssembler.fromRequest(instrumentResponse);
        Operation operation = operationRepository.get(Integer.valueOf(operationNumber));

        operation.add(instrument);

        operationRepository.store(operation);
    }

    public void bookmarkInstrumentToStatus(String operationId, InstrumentRequest instrumentResponse) {
        Operation operation = operationRepository.get(Integer.valueOf(operationId));
        InstrumentStatus instrumentStatus = InstrumentStatus.determineFrom(instrumentResponse.status);
        Serial serial = new Serial(instrumentResponse.serial);

        operation.bookmarkInstrumentToStatus(serial, instrumentStatus);

        operationRepository.store(operation);
    }

    protected OperationService(OperationRepository operationRepository, OperationAssembler operationAssembler,
            InstrumentAssembler instrumentAssembler) {
        this.operationRepository = operationRepository;
        this.operationAssembler = operationAssembler;
        this.instrumentAssembler = instrumentAssembler;
    }
}
