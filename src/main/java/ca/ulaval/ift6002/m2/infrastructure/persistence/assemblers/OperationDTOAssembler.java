package ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers;

import java.util.Collection;
import java.util.Date;

import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.operation.Operation;
import ca.ulaval.ift6002.m2.domain.operation.OperationFactory;
import ca.ulaval.ift6002.m2.domain.operation.OperationStatus;
import ca.ulaval.ift6002.m2.domain.operation.OperationType;
import ca.ulaval.ift6002.m2.domain.operation.room.Room;
import ca.ulaval.ift6002.m2.domain.operation.surgeon.Surgeon;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.InstrumentDTO;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.OperationDTO;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.PatientDTO;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.RoomDTO;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.SurgeonDTO;

public class OperationDTOAssembler extends DTOAssembler<Operation, OperationDTO> {

    private final InstrumentDTOAssembler instrumentDTOAssembler;
    private final RoomDTOAssembler roomDTOAssembler;
    private final SurgeonDTOAssembler surgeonDTOAssembler;
    private final DateFormatter dateFormatter;
    private final PatientDTOAssembler patientDTOAssembler;
    private final OperationFactory operationFactory;

    protected OperationDTOAssembler(InstrumentDTOAssembler instrumentDTOAssembler,
            SurgeonDTOAssembler surgeonDTOAssembler, RoomDTOAssembler roomDTOAssembler,
            PatientDTOAssembler patientDTOAssembler, DateFormatter dateFormatter, OperationFactory operationFactory) {
        this.instrumentDTOAssembler = instrumentDTOAssembler;
        this.surgeonDTOAssembler = surgeonDTOAssembler;
        this.roomDTOAssembler = roomDTOAssembler;
        this.dateFormatter = dateFormatter;
        this.operationFactory = operationFactory;
        this.patientDTOAssembler = patientDTOAssembler;
    }

    public OperationDTOAssembler() {
        this.instrumentDTOAssembler = new InstrumentDTOAssembler();
        this.surgeonDTOAssembler = new SurgeonDTOAssembler();
        this.roomDTOAssembler = new RoomDTOAssembler();
        this.dateFormatter = new DateFormatter();
        this.operationFactory = new OperationFactory();
        this.patientDTOAssembler = new PatientDTOAssembler();
    }

    @Override
    public OperationDTO toDTO(Operation operation) {
        Collection<InstrumentDTO> instruments = instrumentDTOAssembler.toDTOs(operation.getInstruments());
        RoomDTO room = roomDTOAssembler.toDTO(operation.getRoom());
        SurgeonDTO surgeon = surgeonDTOAssembler.toDTO(operation.getSurgeon());
        PatientDTO patient = patientDTOAssembler.toDTO(operation.getPatient());
        Integer number = null;

        if (operation.hasNumber()) {
            number = operation.getNumber();
        }

        String description = operation.getDescription();
        String date = dateFormatter.dateToString(operation.getDate());

        String operationType = operation.getType().toString();
        String operationStatus = operation.getStatus().toString();

        return new OperationDTO(date, operationStatus, description, patient, instruments, surgeon, room, operationType,
                number);
    }

    @Override
    public Operation fromDTO(OperationDTO operationDTO) {
        Patient patient = patientDTOAssembler.fromDTO(operationDTO.patient);
        Date date = dateFormatter.parse(operationDTO.date);
        Surgeon surgeon = surgeonDTOAssembler.fromDTO(operationDTO.surgeon);
        Room room = roomDTOAssembler.fromDTO(operationDTO.room);
        String description = operationDTO.description;
        Integer number = operationDTO.number;
        OperationType type = OperationType.determineFrom(operationDTO.type);
        OperationStatus status = OperationStatus.determineFrom(operationDTO.status);
        Collection<Instrument> instruments = instrumentDTOAssembler.fromDTOs(operationDTO.instruments);

        Operation operation = operationFactory.create(type, description, surgeon, date, room, status, patient, number);
        operation.add(instruments);

        return operation;
    }
}
