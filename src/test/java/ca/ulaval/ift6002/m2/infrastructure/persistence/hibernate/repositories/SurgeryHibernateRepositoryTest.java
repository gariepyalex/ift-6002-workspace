package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.repositories;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.surgery.Surgery;
import ca.ulaval.ift6002.m2.domain.surgery.SurgeryData;
import ca.ulaval.ift6002.m2.domain.surgery.SurgeryFactory;
import ca.ulaval.ift6002.m2.domain.surgery.SurgeryType;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.entities.SurgeryHibernateData;

@RunWith(MockitoJUnitRunner.class)
public class SurgeryHibernateRepositoryTest {

    private static final Integer SURGERY_NUMBER = 1;

    @Mock
    private Surgery surgery;

    @Mock
    private SurgeryHibernateData surgeryData;

    @Mock
    private HibernateRepository<SurgeryHibernateData> hibernateRepository;

    @Mock
    private SurgeryFactory surgeryFactory;

    @InjectMocks
    private SurgeryHibernateRepository surgeryRepository;

    @Test
    public void whenGettingAnSurgeryShouldVerifyHibernateRepository() {
        willReturn(surgeryData).given(hibernateRepository).find(SURGERY_NUMBER);
        surgeryRepository.get(SURGERY_NUMBER);
        verify(hibernateRepository).find(SURGERY_NUMBER);
    }

    @Test
    public void whenGettingAnSurgeryShouldVerifySurgeryFactory() {
        willReturn(surgeryData).given(hibernateRepository).find(SURGERY_NUMBER);
        surgeryRepository.get(SURGERY_NUMBER);
        verify(surgeryFactory).create(any(SurgeryType.class), any(SurgeryData.class));
    }

    @Test
    public void whenStoringAnSurgeryShouldVerifyHibernateRepository() {
        surgeryRepository.store(surgery);

        verify(hibernateRepository).storeElement(any(SurgeryHibernateData.class));
    }

}
