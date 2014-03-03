package ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers;

import java.util.Collection;
import java.util.Date;

import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.operation.Operation;
import ca.ulaval.ift6002.m2.domain.operation.OperationFactory;
import ca.ulaval.ift6002.m2.domain.operation.OperationStatus;
import ca.ulaval.ift6002.m2.domain.operation.OperationType;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.room.Room;
import ca.ulaval.ift6002.m2.domain.surgeon.Surgeon;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.InstrumentDTO;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.OperationDTO;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.PatientDTO;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.RoomDTO;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.SurgeonDTO;

public class OperationDTOAssembler {

    private final InstrumentDTOAssembler instrumentDTOAssembler;
    private final RoomDTOAssembler roomDTOAssembler;
    private final SurgeonDTOAssembler surgeonDTOAssembler;
    private final DateFormatter dateFormatter;
    private final PatientDTOAssembler patientDTOAssembler;
    private final OperationFactory operationFactory;

    public OperationDTOAssembler(InstrumentDTOAssembler instrumentDTOAssembler,
            SurgeonDTOAssembler surgeonDTOAssembler, RoomDTOAssembler roomDTOAssembler,
            PatientDTOAssembler patientDTOAssembler, DateFormatter dateFormatter, OperationFactory operationFactory) {
        this.instrumentDTOAssembler = instrumentDTOAssembler;
        this.surgeonDTOAssembler = surgeonDTOAssembler;
        this.roomDTOAssembler = roomDTOAssembler;
        this.dateFormatter = dateFormatter;
        this.operationFactory = operationFactory;
        this.patientDTOAssembler = patientDTOAssembler;
    }

    public OperationDTO toDTO(Operation anOperation) {

        Collection<InstrumentDTO> instruments = instrumentDTOAssembler.toDTOs(anOperation.getInstruments());
        RoomDTO room = roomDTOAssembler.toDTO(anOperation.getRoom());
        SurgeonDTO surgeon = surgeonDTOAssembler.toDTO(anOperation.getSurgeon());
        PatientDTO patient = patientDTOAssembler.toDTO(anOperation.getPatient());

        String description = anOperation.getDescription();
        String date = dateFormatter.dateToString(anOperation.getDate());

        String operationType = OperationType.convertToString(anOperation.getType());
        String operationStatus = OperationStatus.convertToString(anOperation.getStatus());

        OperationDTO operationDTO = new OperationDTO(date, operationStatus, description, patient, instruments, surgeon,
                room, operationType);
        return operationDTO;
    }

    public Operation fromDTO(OperationDTO anOperationDTO) {

        Patient patient = patientDTOAssembler.fromDTO(anOperationDTO.patient);
        Date date = dateFormatter.parse(anOperationDTO.date);
        Surgeon surgeon = surgeonDTOAssembler.fromDTO(anOperationDTO.surgeon);
        Room room = roomDTOAssembler.fromDTO(anOperationDTO.room);
        String description = anOperationDTO.description;
        OperationType type = OperationType.determineFrom(anOperationDTO.type);
        OperationStatus status = OperationStatus.determineFrom(anOperationDTO.status);

        return operationFactory.create(type, description, surgeon, date, room, status, patient);

    }
}
