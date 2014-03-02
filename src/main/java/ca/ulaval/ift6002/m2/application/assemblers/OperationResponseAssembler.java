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
import ca.ulaval.ift6002.m2.domain.surgeon.Surgeon;

public class OperationResponseAssembler {

    private final OperationFactory operationFactory;
    private final PatientRepository patientRepository;
    private final DateFormatter formatterDate;

    public OperationResponseAssembler(OperationFactory operationfactory, PatientRepository patientRepository,
            DateFormatter formatterDate) {
        this.operationFactory = operationfactory;
        this.patientRepository = patientRepository;
        this.formatterDate = formatterDate;
    }

    public Operation fromResponse(OperationResponse response) throws InvalidResponseException {

        Patient aPatient = patientRepository.get(response.patientNumber);
        Date aDate = formatterDate.parse(response.date);
        Surgeon aSurgeon = new Surgeon(response.surgeon);
        Room aRoom = new Room(response.room);
        String aDescription = response.description;
        OperationType type = convertType(response.type);
        OperationStatus status = convertStatus(response.status);

        return operationFactory.create(type, aDescription, aSurgeon, aDate, aRoom, status, aPatient);

    }

    private OperationType convertType(String type) throws InvalidResponseException {
        type = type.toUpperCase();
        OperationType resultOperationType;
        if (type.equals("OEIL")) {
            resultOperationType = OperationType.EYE;
        } else if (type.equals("COEUR")) {
            resultOperationType = OperationType.HEART;
        } else if (type.equals("MOELLE")) {
            resultOperationType = OperationType.MARROW;
        } else if (type.equals("ONCOLOGIQUE")) {
            resultOperationType = OperationType.ONCOLOGY;
        } else if (type.equals("AUTRE")) {
            resultOperationType = OperationType.OTHER;
        } else {
            throw new InvalidResponseException("Operation type is not defined");
        }
        return resultOperationType;

    }

    private OperationStatus convertStatus(String status) throws InvalidResponseException {
        status = status.toUpperCase();
        OperationStatus resultOperationStatus;

        if (status.equals("PLANIFIEE") || status.equals("")) {
            resultOperationStatus = OperationStatus.PLANNED;
        } else if (status.equals("EN_COURS")) {
            resultOperationStatus = OperationStatus.IN_PROGRESS;
        } else if (status.equals("TERMINE")) {
            resultOperationStatus = OperationStatus.FINISH;
        } else if (status.equals("ANNULEE")) {
            resultOperationStatus = OperationStatus.CANCELLED;
        } else if (status.equals("REPORTEE")) {
            resultOperationStatus = OperationStatus.POSTPONED;
        } else {
            throw new InvalidResponseException("Operation status is not defined");
        }

        return resultOperationStatus;
    }
}
