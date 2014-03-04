package ca.ulaval.ift6002.m2.infrastructure.persistence.factory;

import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.domain.operation.OperationRepository;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.DrugHibernateRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.OperationHibernateRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.PatientHibernateRepository;

public class HibernateRepositoryFactory implements RepositoryFactory {

    @Override
    public DrugRepository createDrugRepository() {
        return new DrugHibernateRepository();
    }

    @Override
    public OperationRepository createOperationRepository() {
        return new OperationHibernateRepository();
    }

    @Override
    public PatientRepository createPatientRepository() {
        return new PatientHibernateRepository();
    }
}
