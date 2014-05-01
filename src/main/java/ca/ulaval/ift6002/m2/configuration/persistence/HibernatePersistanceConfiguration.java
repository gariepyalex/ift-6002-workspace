package ca.ulaval.ift6002.m2.configuration.persistence;

import ca.ulaval.ift6002.m2.configuration.Configurable;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.domain.surgery.SurgeryRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.repositories.DrugHibernateRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.repositories.SurgeryHibernateRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.repositories.PatientHibernateRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerProvider;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerProviderThreadSafe;
import ca.ulaval.ift6002.m2.locator.RepositoryLocator;

public class HibernatePersistanceConfiguration implements Configurable {

    @Override
    public void configure() {
        RepositoryLocator repositoryLocator = new RepositoryLocator();
        EntityManagerProvider entityManagerProvider = new EntityManagerProviderThreadSafe();
        repositoryLocator.register(DrugRepository.class, new DrugHibernateRepository(entityManagerProvider));
        repositoryLocator.register(PatientRepository.class, new PatientHibernateRepository(entityManagerProvider));
        repositoryLocator.register(SurgeryRepository.class, new SurgeryHibernateRepository(entityManagerProvider));

        RepositoryLocator.load(repositoryLocator);

    }

}
