package ca.ulaval.ift6002.m2.acceptance.fixtures;

import java.util.Date;

import ca.ulaval.ift6002.m2.acceptance.contexts.SurgeryContext;
import ca.ulaval.ift6002.m2.application.assemblers.InstrumentAssembler;
import ca.ulaval.ift6002.m2.application.requests.InstrumentRequest;
import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.room.Room;
import ca.ulaval.ift6002.m2.domain.surgeon.Surgeon;
import ca.ulaval.ift6002.m2.domain.surgery.Surgery;
import ca.ulaval.ift6002.m2.domain.surgery.SurgeryStatus;
import ca.ulaval.ift6002.m2.domain.surgery.SurgeryType;
import ca.ulaval.ift6002.m2.locator.FactoryLocator;
import ca.ulaval.ift6002.m2.locator.RepositoryLocator;

public class SurgeryFixture {

    private static final SurgeryType AN_SURGERY_TYPE = SurgeryType.ONCOLOGY;
    private static final SurgeryType A_RESTRICTED_SURGERY_TYPE = SurgeryType.EYE;
    private static final String A_DESCRIPTION = "A surgery description";
    private static final Surgeon A_SURGEON = new Surgeon(1);
    private static final Date A_DATE = new Date();
    private static final Room A_ROOM = new Room("A1");
    private static final SurgeryStatus AN_SURGERY_STATUS = SurgeryStatus.IN_PROGRESS;

    private final PatientFixture patientFixture = new PatientFixture();

    public void setupExistingSurgery() {
        Surgery surgery = createSurgery(AN_SURGERY_TYPE);

        SurgeryContext.setSurgery(surgery);
    }

    public void setupExistingRestrictedSurgery() {
        Surgery surgery = createSurgery(A_RESTRICTED_SURGERY_TYPE);

        SurgeryContext.setSurgery(surgery);
    }

    public Surgery getExistingSurgery() {
        return SurgeryContext.getSurgery();
    }

    private Surgery createSurgery(SurgeryType type) {
        Patient patient = patientFixture.getExistingPatient();

        Surgery surgery = FactoryLocator.getSurgeryFactory().create(type, A_DESCRIPTION, A_SURGEON, A_DATE, A_ROOM,
                AN_SURGERY_STATUS, patient);

        RepositoryLocator.getSurgeryRepository().store(surgery);

        return surgery;
    }

    public void addInstrumentToExistingSurgery(InstrumentRequest instrumentRequest) {
        InstrumentAssembler instrumentAssembler = new InstrumentAssembler();
        Instrument instrument = instrumentAssembler.fromRequest(instrumentRequest);

        Surgery surgery = SurgeryContext.getSurgery();
        surgery.add(instrument);

        RepositoryLocator.getSurgeryRepository().store(surgery);
    }
}
