package ca.ulaval.ift6002.m2.acceptance.contexts;

import ca.ulaval.ift6002.m2.domain.operation.Operation;

public class OperationContext {

    private static Operation operationInstance;

    public static void setOperation(Operation operation) {
        operationInstance = operation;
    }

    public static Integer getOperationNumber() {
        return operationInstance.getNumber();
    }

    public static void reset() {
        operationInstance = null;
    }
}
