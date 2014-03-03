package ca.ulaval.ift6002.m2.application.assemblers;

import java.util.Date;

import ca.ulaval.ift6002.m2.application.responses.OperationResponse;
import ca.ulaval.ift6002.m2.application.validator.response.InvalidResponseException;
import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.operation.Operation;
import ca.ulaval.ift6002.m2.domain.operation.OperationFactory;
import ca.ulaval.ift6002.m2.domain.operation.OperationStatus;
import ca.ulaval.ift6002.m2.domain.operation.OperationType;
import ca.ulaval.ift6002.m2.domain.operation.room.Room;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.domain.surgeon.Surgeon;
import ca.ulaval.ift6002.m2.infrastructure.persistence.locator.RepositoryLocator;

public class OperationResponseAssembler {

    private final OperationFactory operationFactory;
    private final PatientRepository patientRepository;
    private final DateFormatter formatterDate;

    protected OperationResponseAssembler(OperationFactory operationfactory, PatientRepository patientRepository,
            DateFormatter formatterDate) {
        this.operationFactory = operationfactory;
        this.patientRepository = patientRepository;
        this.formatterDate = formatterDate;
    }

    public OperationResponseAssembler() {
        this.operationFactory = new OperationFactory();
        this.patientRepository = RepositoryLocator.getPatientRepository();
        this.formatterDate = new DateFormatter();
    }

    public Operation fromResponse(OperationResponse response) throws InvalidResponseException {
        try {
            Patient patient = patientRepository.get(response.patientNumber);
            Date date = formatterDate.parse(response.date);
            Surgeon surgeon = new Surgeon(response.surgeon);
            Room room = new Room(response.room);
            String description = response.description;
            OperationType type = OperationType.determineFrom(response.type);
            OperationStatus status = OperationStatus.determineFrom(response.status);

            return operationFactory.create(type, description, surgeon, date, room, status, patient);

        } catch (IllegalArgumentException e) {
            throw new InvalidResponseException(e.getMessage());
        }
    }
}
