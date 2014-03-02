package ca.ulaval.ift6002.m2.application.assemblers;

import java.util.Date;

import ca.ulaval.ift6002.m2.application.responses.OperationResponse;
import ca.ulaval.ift6002.m2.application.validator.response.InvalidResponseException;
import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.operation.Operation;
import ca.ulaval.ift6002.m2.domain.operation.OperationFactory;
import ca.ulaval.ift6002.m2.domain.operation.OperationStatus;
import ca.ulaval.ift6002.m2.domain.operation.OperationType;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.domain.room.Room;
import ca.ulaval.ift6002.m2.domain.room.RoomRepository;
import ca.ulaval.ift6002.m2.domain.surgeon.Surgeon;
import ca.ulaval.ift6002.m2.domain.surgeon.SurgeonRepository;

public class OperationResponseAssembler {

    private final OperationFactory operationFactory;
    private final PatientRepository patientRepository;
    private final SurgeonRepository surgeonRepository;
    private final RoomRepository roomRepository;
    private final DateFormatter formatterDate;

    public OperationResponseAssembler(OperationFactory operationfactory, PatientRepository patientRepository,
            SurgeonRepository surgeonRepository, RoomRepository roomRepository, DateFormatter formatterDate) {
        this.operationFactory = operationfactory;
        this.patientRepository = patientRepository;
        this.surgeonRepository = surgeonRepository;
        this.roomRepository = roomRepository;
        this.formatterDate = formatterDate;
    }

    public Operation fromResponse(OperationResponse response) throws InvalidResponseException {

        Patient aPatient = patientRepository.get(response.patientNumber);
        Date aDate = formatterDate.parse(response.date);
        Surgeon aSurgeon = surgeonRepository.get(response.surgeon);
        Room aRoom = roomRepository.get(response.room);
        String aDescription = response.description;
        OperationType type = convertType(response.type);
        OperationStatus status = convertStatus(response.status);

        return operationFactory.create(type, aDescription, aSurgeon, aDate, aRoom, status, aPatient);

    }

    private OperationType convertType(String type) throws InvalidResponseException {
        if (type.equalsIgnoreCase("OEIL")) {
            return OperationType.EYE;
        } else if (type.equalsIgnoreCase("COEUR")) {
            return OperationType.HEART;
        } else if (type.equalsIgnoreCase("MOELLE")) {
            return OperationType.MARROW;
        } else if (type.equalsIgnoreCase("ONCOLOGIQUE")) {
            return OperationType.ONCOLOGY;
        } else if (type.equalsIgnoreCase("AUTRE")) {
            return OperationType.OTHER;
        }

        throw new InvalidResponseException("Operation type is not defined");
    }

    private OperationStatus convertStatus(String status) throws InvalidResponseException {
        if (status.equalsIgnoreCase("PLANIFIEE") || status.isEmpty()) {
            return OperationStatus.PLANNED;
        } else if (status.equalsIgnoreCase("EN_COURS")) {
            return OperationStatus.IN_PROGRESS;
        } else if (status.equalsIgnoreCase("TERMINE")) {
            return OperationStatus.FINISH;
        } else if (status.equalsIgnoreCase("ANNULEE")) {
            return OperationStatus.CANCELLED;
        } else if (status.equalsIgnoreCase("REPORTEE")) {
            return OperationStatus.POSTPONED;
        }

        throw new InvalidResponseException("Operation status is not defined");
    }
}
