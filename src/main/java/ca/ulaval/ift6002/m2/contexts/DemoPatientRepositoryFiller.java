package ca.ulaval.ift6002.m2.contexts;

import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.locator.RepositoryLocator;

public class DemoPatientRepositoryFiller {

    private final PatientRepository patientRepository;

    public DemoPatientRepositoryFiller() {
        this.patientRepository = RepositoryLocator.getPatientRepository();
    }

    public DemoPatientRepositoryFiller(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public void fill() {
        patientRepository.store(new Patient(1, "ABCD 4512 1213"));
        patientRepository.store(new Patient(2, "QWER 7894 8798"));
    }
}
