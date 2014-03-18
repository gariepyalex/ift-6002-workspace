package ca.ulaval.ift6002.m2.domain.operation.restricted;

import ca.ulaval.ift6002.m2.domain.operation.OperationData;
import ca.ulaval.ift6002.m2.domain.operation.OperationType;

public class HeartOperation extends RestrictedOperation {

    public HeartOperation(OperationData operationData) {
        super(operationData);
    }

    @Override
    public OperationType getType() {
        return OperationType.HEART;
    }

}
