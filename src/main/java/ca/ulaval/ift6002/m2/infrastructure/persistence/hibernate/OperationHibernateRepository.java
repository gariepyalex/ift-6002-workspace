package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate;

import ca.ulaval.ift6002.m2.domain.operation.Operation;
import ca.ulaval.ift6002.m2.domain.operation.OperationFactory;
import ca.ulaval.ift6002.m2.domain.operation.OperationRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.entities.OperationHibernateData;
import ca.ulaval.ift6002.m2.locator.FactoryLocator;

public class OperationHibernateRepository implements OperationRepository {

    private final HibernateRepository<OperationHibernateData> hibernateRepository;
    private final OperationFactory operationFactory;

    public OperationHibernateRepository() {
        hibernateRepository = new HibernateRepository<>(OperationHibernateData.class);
        operationFactory = FactoryLocator.getOperationFactory();
    }

    @Override
    public Operation get(int number) {
        OperationHibernateData operationData = hibernateRepository.find(number);

        return operationFactory.create(operationData.getType(), operationData);
    }

    @Override
    public void store(Operation operation) {
        OperationHibernateData operationData = (OperationHibernateData) operation.getData();

        hibernateRepository.storeElement(operationData);
    }

    protected OperationHibernateRepository(HibernateRepository<OperationHibernateData> hibernateRepository,
            OperationFactory operationFactory) {
        this.hibernateRepository = hibernateRepository;
        this.operationFactory = operationFactory;
    }

}
