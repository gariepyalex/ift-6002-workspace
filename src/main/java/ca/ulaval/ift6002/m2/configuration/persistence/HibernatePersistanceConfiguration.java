package ca.ulaval.ift6002.m2.configuration.persistence;

import ca.ulaval.ift6002.m2.configuration.Configurable;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.domain.operation.OperationRepository;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.repositories.DrugHibernateRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.repositories.OperationHibernateRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.repositories.PatientHibernateRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerProviderThreadSafe;
import ca.ulaval.ift6002.m2.locator.RepositoryLocator;

public class HibernatePersistanceConfiguration implements Configurable {

    @Override
    public void configure() {
        RepositoryLocator repositoryLocator = new RepositoryLocator();
        EntityManagerProviderThreadSafe entityManagerProvider = new EntityManagerProviderThreadSafe();
        repositoryLocator.register(DrugRepository.class, new DrugHibernateRepository(entityManagerProvider));
        repositoryLocator.register(PatientRepository.class, new PatientHibernateRepository(entityManagerProvider));
        repositoryLocator.register(OperationRepository.class, new OperationHibernateRepository(entityManagerProvider));

        RepositoryLocator.load(repositoryLocator);

    }

}
