package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate;

import ca.ulaval.ift6002.m2.application.responses.OperationResponse;
import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.operation.Operation;
import ca.ulaval.ift6002.m2.domain.operation.OperationRepository;

public class OperationHibernateRepository extends HibernateRepository<OperationResponse> implements OperationRepository {

    public OperationHibernateRepository(Class<OperationResponse> classType) {
        super(classType);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void store(Operation operation) {
        // TODO Auto-generated method stub

    }

    @Override
    public Operation getOperation(int operationId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void storeInstrument(Operation operation, Instrument instrument) {
        // TODO Auto-generated method stub

    }

    @Override
    public Instrument getInstrument(Integer valueOf) {
        // TODO Auto-generated method stub
        return null;
    }

}
