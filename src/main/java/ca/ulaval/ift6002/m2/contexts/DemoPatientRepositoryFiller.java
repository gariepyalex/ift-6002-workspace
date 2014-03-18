package ca.ulaval.ift6002.m2.contexts;

import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;

public class DemoPatientRepositoryFiller {

    public void fill(PatientRepository repository) {
        repository.store(new Patient(1, "ABCD 4512 1213"));
        repository.store(new Patient(2, "QWER 7894 8798"));
    }
}
