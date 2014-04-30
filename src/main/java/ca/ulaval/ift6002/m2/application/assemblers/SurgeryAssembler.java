package ca.ulaval.ift6002.m2.application.assemblers;

import java.util.Date;

import ca.ulaval.ift6002.m2.application.requests.SurgeryRequest;
import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.domain.room.Room;
import ca.ulaval.ift6002.m2.domain.surgeon.Surgeon;
import ca.ulaval.ift6002.m2.domain.surgery.Surgery;
import ca.ulaval.ift6002.m2.domain.surgery.SurgeryFactory;
import ca.ulaval.ift6002.m2.domain.surgery.SurgeryStatus;
import ca.ulaval.ift6002.m2.domain.surgery.SurgeryType;
import ca.ulaval.ift6002.m2.locator.FactoryLocator;
import ca.ulaval.ift6002.m2.locator.RepositoryLocator;

public class SurgeryAssembler {

    private final SurgeryFactory surgeryFactory;
    private final PatientRepository patientRepository;
    private final DateFormatter formatterDate;

    public SurgeryAssembler() {
        this.surgeryFactory = FactoryLocator.getSurgeryFactory();
        this.patientRepository = RepositoryLocator.getPatientRepository();
        this.formatterDate = new DateFormatter();
    }

    public Surgery fromRequest(SurgeryRequest request) {
        Patient patient = patientRepository.get(request.patientNumber);
        Date date = formatterDate.parse(request.date);
        Surgeon surgeon = new Surgeon(request.surgeon);
        Room room = new Room(request.room);
        String description = request.description;
        SurgeryType type = SurgeryType.determineFrom(request.type);
        SurgeryStatus status = SurgeryStatus.determineFrom(request.status);

        return surgeryFactory.create(type, description, surgeon, date, room, status, patient);
    }

    protected SurgeryAssembler(SurgeryFactory surgeryFactory, PatientRepository patientRepository,
            DateFormatter formatterDate) {
        this.surgeryFactory = surgeryFactory;
        this.patientRepository = patientRepository;
        this.formatterDate = formatterDate;
    }
}
