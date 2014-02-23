package ca.ulaval.ift6002.m2.domain.operation;

public class OperationFactory {

    public Operation create(OperationType type, Operation.Builder builder) {
        if (type.isDangerous()) {
            return createDangerousOperation(builder);
        } else {
            return createRegularOperation(builder);
        }
    }

    private Operation createDangerousOperation(Operation.Builder builder) {
        return new DangerousOperation(builder);
    }

    private Operation createRegularOperation(Operation.Builder builder) {
        return new RegularOperation(builder);
    }

}
