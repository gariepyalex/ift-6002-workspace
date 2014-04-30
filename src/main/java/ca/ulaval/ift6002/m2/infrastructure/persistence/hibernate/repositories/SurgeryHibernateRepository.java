package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.repositories;

import ca.ulaval.ift6002.m2.domain.surgery.Surgery;
import ca.ulaval.ift6002.m2.domain.surgery.SurgeryFactory;
import ca.ulaval.ift6002.m2.domain.surgery.SurgeryRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.entities.SurgeryHibernateData;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerProvider;
import ca.ulaval.ift6002.m2.locator.FactoryLocator;

public class SurgeryHibernateRepository implements SurgeryRepository {

    private final HibernateRepository<SurgeryHibernateData> hibernateRepository;
    private final SurgeryFactory surgeryFactory;

    public SurgeryHibernateRepository(EntityManagerProvider entityManagerProvider) {
        hibernateRepository = new HibernateRepository<>(entityManagerProvider, SurgeryHibernateData.class);
        surgeryFactory = FactoryLocator.getSurgeryFactory();
    }

    @Override
    public Surgery get(int number) {
        SurgeryHibernateData surgeryData = hibernateRepository.find(number);

        return surgeryFactory.create(surgeryData.getType(), surgeryData);
    }

    @Override
    public void store(Surgery surgery) {
        SurgeryHibernateData surgeryData = (SurgeryHibernateData) surgery.getData();

        hibernateRepository.storeElement(surgeryData);
    }

    protected SurgeryHibernateRepository(HibernateRepository<SurgeryHibernateData> hibernateRepository,
            SurgeryFactory surgeryFactory) {
        this.hibernateRepository = hibernateRepository;
        this.surgeryFactory = surgeryFactory;
    }

}
