package ca.ulaval.ift6002.m2.contexts;

import ca.ulaval.ift6002.m2.domain.operation.patient.Patient;
import ca.ulaval.ift6002.m2.domain.operation.patient.PatientRepository;

public class DemoPatientRepositoryFiller {

    public void fill(PatientRepository repository) {
        repository.store(new Patient(1));
        repository.store(new Patient(2));
    }
}
