package ca.ulaval.ift6002.m2.application.services;

import ca.ulaval.ift6002.m2.application.assemblers.InstrumentDTOAssembler;
import ca.ulaval.ift6002.m2.application.responses.InstrumentDTO;
import ca.ulaval.ift6002.m2.application.validator.dto.InvalidDTOException;
import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentRepository;
import ca.ulaval.ift6002.m2.domain.operation.Operation;
import ca.ulaval.ift6002.m2.domain.operation.OperationRepository;

public class OperationService {

    private InstrumentRepository instrumentRepository;
    private InstrumentDTOAssembler instrumentAssembler;
    private OperationRepository operationRepository;

    public OperationService(OperationRepository operationRepository, InstrumentRepository instrumentRepository,
            InstrumentDTOAssembler instrumentAssembler) {
        this.operationRepository = operationRepository;
        this.instrumentRepository = instrumentRepository;
        this.instrumentAssembler = instrumentAssembler;
    }

    public void saveInstrument(String operationId, InstrumentDTO instrumentDto) throws InvalidDTOException {
        Instrument instrument = instrumentAssembler.fromDTO(instrumentDto);
        Operation operation = operationRepository.get(Integer.valueOf(operationId));
        instrumentRepository.store(operation, instrument);
    }
}
