package ca.ulaval.ift6002.m2.acceptance.configuration;

import static org.mockito.Mockito.mock;
import ca.ulaval.ift6002.m2.configuration.Configurable;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.domain.operation.OperationRepository;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.locator.RepositoryLocator;

public class TestHibernatePersistanceConfiguration implements Configurable {

    @Override
    public void configure() {
        RepositoryLocator repositoryLocator = new RepositoryLocator();

        repositoryLocator.register(DrugRepository.class, mock(DrugRepository.class));
        repositoryLocator.register(PatientRepository.class, mock(PatientRepository.class));
        repositoryLocator.register(OperationRepository.class, mock(OperationRepository.class));

        RepositoryLocator.load(repositoryLocator);

    }
}
