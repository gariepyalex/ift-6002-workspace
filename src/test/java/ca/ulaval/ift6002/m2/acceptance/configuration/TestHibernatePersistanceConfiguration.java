package ca.ulaval.ift6002.m2.acceptance.configuration;

import static org.mockito.Mockito.spy;
import ca.ulaval.ift6002.m2.configuration.Configurable;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.domain.surgery.SurgeryRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.repositories.DrugHibernateRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.repositories.SurgeryHibernateRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.repositories.PatientHibernateRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerProvider;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerProviderGlobal;
import ca.ulaval.ift6002.m2.locator.RepositoryLocator;

public class TestHibernatePersistanceConfiguration implements Configurable {

    @Override
    public void configure() {
        EntityManagerProvider entityManagerProvider = new EntityManagerProviderGlobal();
        DrugRepository drugRepository = new DrugHibernateRepository(entityManagerProvider);
        PatientRepository patientRepository = new PatientHibernateRepository(entityManagerProvider);
        SurgeryRepository surgeryRepository = new SurgeryHibernateRepository(entityManagerProvider);

        RepositoryLocator repositoryLocator = new RepositoryLocator();

        repositoryLocator.register(DrugRepository.class, spy(drugRepository));
        repositoryLocator.register(PatientRepository.class, spy(patientRepository));
        repositoryLocator.register(SurgeryRepository.class, spy(surgeryRepository));

        RepositoryLocator.load(repositoryLocator);
    }
}
