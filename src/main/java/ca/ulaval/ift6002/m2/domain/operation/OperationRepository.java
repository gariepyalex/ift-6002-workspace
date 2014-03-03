package ca.ulaval.ift6002.m2.domain.operation;


public interface OperationRepository {

    Operation get(int operationId);

    void store(Operation operation);
}
