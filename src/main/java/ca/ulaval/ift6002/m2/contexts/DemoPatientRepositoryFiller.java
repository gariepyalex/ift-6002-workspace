package ca.ulaval.ift6002.m2.contexts;

import ca.ulaval.ift6002.m2.domain.patient.PatientFactory;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.locator.FactoryLocator;
import ca.ulaval.ift6002.m2.locator.RepositoryLocator;

public class DemoPatientRepositoryFiller {

    private final PatientRepository patientRepository;
    private final PatientFactory patientFactory;

    public DemoPatientRepositoryFiller() {
        this.patientRepository = RepositoryLocator.getPatientRepository();
        this.patientFactory = FactoryLocator.getPatientFactory();
    }

    public void fill() {
        patientRepository.store(patientFactory.create(1, "ABCD 4512 1213"));
        patientRepository.store(patientFactory.create(2, "QWER 7894 8798"));
    }

    protected DemoPatientRepositoryFiller(PatientRepository patientRepository, PatientFactory patientFactory) {
        this.patientRepository = patientRepository;
        this.patientFactory = patientFactory;
    }
}
