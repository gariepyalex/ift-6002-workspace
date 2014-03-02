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

        try {

            Patient aPatient = patientRepository.get(response.patientNumber);
            Date aDate = formatterDate.parse(response.date);
            Surgeon aSurgeon = new Surgeon(response.surgeon);
            Room aRoom = new Room(response.room);
            String aDescription = response.description;
            OperationType type = OperationType.determineFrom(response.type);
            OperationStatus status = OperationStatus.determineFrom(response.status);

            return operationFactory.create(type, aDescription, aSurgeon, aDate, aRoom, status, aPatient);

        } catch (IllegalArgumentException e) {
            throw new InvalidResponseException(e.getMessage());
        }
    }
}
