package ca.ulaval.ift6002.m2.infrastructure.persistence.inmemory.repository;

import ca.ulaval.ift6002.m2.domain.operation.Operation;
import ca.ulaval.ift6002.m2.domain.operation.OperationRepository;

public class OperationInMemoryRepository implements OperationRepository {

    @Override
    public void store(Operation operation) {

    }

    @Override
    public Operation get(Integer operationId) {
        return null;
    }

}
