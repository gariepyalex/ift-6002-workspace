package ca.ulaval.ift6002.m2.domain.operation;

public interface OperationRepository {

    void store(Operation operation);

    Operation get(int operationId);
}
