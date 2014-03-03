package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate;

import ca.ulaval.ift6002.m2.domain.operation.Operation;
import ca.ulaval.ift6002.m2.domain.operation.OperationRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.OperationDTO;

public class OperationHibernateRepository extends HibernateRepository<OperationDTO> implements OperationRepository {

    public OperationHibernateRepository(Class<OperationDTO> classType) {
        super(classType);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Operation get(int operationId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void store(Operation operation) {
        // TODO Auto-generated method stub

    }

}
