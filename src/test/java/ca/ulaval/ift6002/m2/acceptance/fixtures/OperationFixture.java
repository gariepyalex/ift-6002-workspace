package ca.ulaval.ift6002.m2.acceptance.fixtures;

import java.util.Date;

import ca.ulaval.ift6002.m2.acceptance.contexts.OperationContext;
import ca.ulaval.ift6002.m2.domain.operation.Operation;
import ca.ulaval.ift6002.m2.domain.operation.OperationStatus;
import ca.ulaval.ift6002.m2.domain.operation.OperationType;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.room.Room;
import ca.ulaval.ift6002.m2.domain.surgeon.Surgeon;
import ca.ulaval.ift6002.m2.locator.FactoryLocator;
import ca.ulaval.ift6002.m2.locator.RepositoryLocator;

public class OperationFixture {

    private static final OperationType EYE_OPERATION = OperationType.EYE;
    private static final String OPERATION_DESCRIPTION = "An operation description";
    private static final Surgeon SURGEON = new Surgeon(1);
    private static final Date OPERATION_DATE = new Date();
    private static final Room OPERATION_ROOM = new Room("A1");
    private static final OperationStatus OPERATION_STATUS = OperationStatus.IN_PROGRESS;

    private PatientFixture patientFixture = new PatientFixture();

    public void setupExistingOperation() {
        OperationContext.setOperation(getExistingOperation());
    }

    public Operation getExistingOperation() {

        return createOperation();
    }

    private Operation createOperation() {
        Patient patient = patientFixture.getExistingPatient();

        Operation operation = FactoryLocator.getOperationFactory().create(EYE_OPERATION, OPERATION_DESCRIPTION,
                SURGEON, OPERATION_DATE, OPERATION_ROOM, OPERATION_STATUS, patient);

        RepositoryLocator.getOperationRepository().store(operation);

        return operation;
    }

}
