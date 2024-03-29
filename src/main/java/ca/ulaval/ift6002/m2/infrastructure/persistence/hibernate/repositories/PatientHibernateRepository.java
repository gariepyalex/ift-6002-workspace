package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.repositories;

import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.entities.PatientHibernate;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerProvider;

public class PatientHibernateRepository implements PatientRepository {

    private final HibernateRepository<PatientHibernate> hibernateRepository;

    public PatientHibernateRepository(EntityManagerProvider entityManagerProvider) {
        hibernateRepository = new HibernateRepository<>(entityManagerProvider, PatientHibernate.class);
    }

    @Override
    public Patient get(int number) {
        return hibernateRepository.find(number);
    }

    @Override
    public void store(Patient patient) {
        PatientHibernate patientHibernate = (PatientHibernate) patient;

        hibernateRepository.storeElement(patientHibernate);
    }

    protected PatientHibernateRepository(HibernateRepository<PatientHibernate> hibernateRepository) {
        this.hibernateRepository = hibernateRepository;
    }
}
