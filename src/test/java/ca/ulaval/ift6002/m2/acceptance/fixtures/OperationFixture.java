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

    private PatientFixture patientFixture = new PatientFixture();

    public void setupOperationContext() {
        OperationContext.setOperation(getExistingOperation());
    }

    public Operation getExistingOperation() {

        return createOperation();
    }

    private Operation createOperation() {
        Patient patient = patientFixture.getExistingPatient();

        Operation operation = FactoryLocator.getOperationFactory().create(OperationType.EYE, "Yeux", new Surgeon(1),
                new Date(), new Room("A1"), OperationStatus.IN_PROGRESS, patient);

        RepositoryLocator.getOperationRepository().store(operation);

        return operation;
    }

}
