package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate;

import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.entities.PatientHibernate;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerProvider;

public class PatientHibernateRepository extends HibernateRepository<PatientHibernate> implements PatientRepository {

    public PatientHibernateRepository() {
        super(PatientHibernate.class);
    }

    @Override
    public Patient get(int number) {
        return find(number);
    }

    @Override
    public void store(Patient patient) {
        PatientHibernate patientHibernate = (PatientHibernate) patient;

        storeElement(patientHibernate);
    }

    protected PatientHibernateRepository(EntityManagerProvider entityManagerProvider) {
        super(entityManagerProvider, PatientHibernate.class);
    }
}
