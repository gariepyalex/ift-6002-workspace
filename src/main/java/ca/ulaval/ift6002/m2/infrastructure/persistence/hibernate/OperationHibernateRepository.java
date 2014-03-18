package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate;

import ca.ulaval.ift6002.m2.domain.operation.Operation;
import ca.ulaval.ift6002.m2.domain.operation.OperationFactory;
import ca.ulaval.ift6002.m2.domain.operation.OperationRepository;
import ca.ulaval.ift6002.m2.factory.hibernate.OperationHibernateFactory;
import ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers.OperationDTOAssembler;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.entities.OperationHibernateData;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerProvider;

public class OperationHibernateRepository extends HibernateRepository<OperationHibernateData> implements
        OperationRepository {

    private final OperationDTOAssembler operationAssembler;
    private final OperationFactory operationFactory;

    public OperationHibernateRepository() {
        super(OperationHibernateData.class);
        this.operationAssembler = new OperationDTOAssembler();
        // TODO call factory locator here
        this.operationFactory = new OperationHibernateFactory();
    }

    public OperationHibernateRepository(EntityManagerProvider entityManagerProvider,
            OperationDTOAssembler operationAssembler, OperationFactory operationFactory) {
        super(entityManagerProvider, OperationHibernateData.class);
        this.operationAssembler = operationAssembler;
        this.operationFactory = operationFactory;
    }

    @Override
    public Operation get(int number) {
        OperationHibernateData operationData = find(number);

        return operationFactory.create(operationData.getType(), operationData);
    }

    @Override
    public void store(Operation operation) {

        OperationHibernateData operationData = (OperationHibernateData) operation.getData();

        merge(operationData);
    }

}
