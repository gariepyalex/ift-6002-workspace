package ca.ulaval.ift6002.m2.application.assemblers;

import java.util.Date;

import ca.ulaval.ift6002.m2.application.requests.OperationRequest;
import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.operation.Operation;
import ca.ulaval.ift6002.m2.domain.operation.OperationFactory;
import ca.ulaval.ift6002.m2.domain.operation.OperationStatus;
import ca.ulaval.ift6002.m2.domain.operation.OperationType;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.domain.room.Room;
import ca.ulaval.ift6002.m2.domain.surgeon.Surgeon;
import ca.ulaval.ift6002.m2.factory.FactoryLocator;
import ca.ulaval.ift6002.m2.infrastructure.persistence.locator.RepositoryLocator;

public class OperationAssembler {

    private final OperationFactory operationFactory;
    private final PatientRepository patientRepository;
    private final DateFormatter formatterDate;

    protected OperationAssembler(OperationFactory operationfactory, PatientRepository patientRepository,
            DateFormatter formatterDate) {
        this.operationFactory = operationfactory;
        this.patientRepository = patientRepository;
        this.formatterDate = formatterDate;
    }

    public OperationAssembler() {
        this.operationFactory = FactoryLocator.getOperationFactory();
        this.patientRepository = RepositoryLocator.getPatientRepository();
        this.formatterDate = new DateFormatter();
    }

    public Operation fromRequest(OperationRequest request) {
        Patient patient = patientRepository.get(request.patientNumber);
        Date date = formatterDate.parse(request.date);
        Surgeon surgeon = new Surgeon(request.surgeon);
        Room room = new Room(request.room);
        String description = request.description;
        OperationType type = OperationType.determineFrom(request.type);
        OperationStatus status = OperationStatus.determineFrom(request.status);

        return operationFactory.create(type, description, surgeon, date, room, status, patient);
    }
}
