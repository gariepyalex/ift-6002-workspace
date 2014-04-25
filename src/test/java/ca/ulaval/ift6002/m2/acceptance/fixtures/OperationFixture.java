package ca.ulaval.ift6002.m2.acceptance.fixtures;

import java.util.Date;

import ca.ulaval.ift6002.m2.acceptance.contexts.OperationContext;
import ca.ulaval.ift6002.m2.application.assemblers.InstrumentAssembler;
import ca.ulaval.ift6002.m2.application.requests.InstrumentRequest;
import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.operation.Operation;
import ca.ulaval.ift6002.m2.domain.operation.OperationStatus;
import ca.ulaval.ift6002.m2.domain.operation.OperationType;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.room.Room;
import ca.ulaval.ift6002.m2.domain.surgeon.Surgeon;
import ca.ulaval.ift6002.m2.locator.FactoryLocator;
import ca.ulaval.ift6002.m2.locator.RepositoryLocator;

public class OperationFixture {

    private static final OperationType AN_OPERATION_TYPE = OperationType.ONCOLOGY;
    private static final String A_DESCRIPTION = "An operation description";
    private static final Surgeon A_SURGEON = new Surgeon(1);
    private static final Date A_DATE = new Date();
    private static final Room A_ROOM = new Room("A1");
    private static final OperationStatus AN_OPERATION_STATUS = OperationStatus.IN_PROGRESS;

    private final PatientFixture patientFixture = new PatientFixture();

    public void setupExistingOperation() {
        Operation operation = createOperation();

        OperationContext.setOperation(operation);
    }

    public Operation getExistingOperation() {
        return OperationContext.getOperation();
    }

    private Operation createOperation() {
        Patient patient = patientFixture.getExistingPatient();

        Operation operation = FactoryLocator.getOperationFactory().create(AN_OPERATION_TYPE, A_DESCRIPTION, A_SURGEON,
                A_DATE, A_ROOM, AN_OPERATION_STATUS, patient);

        RepositoryLocator.getOperationRepository().store(operation);

        return operation;
    }

    public void addInstrumentToExistingOperation(InstrumentRequest instrumentRequest) {
        InstrumentAssembler instrumentAssembler = new InstrumentAssembler();

        Instrument instrument = instrumentAssembler.fromRequest(instrumentRequest);

        getExistingOperation().add(instrument);
    }

}
