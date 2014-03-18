package ca.ulaval.ift6002.m2.domain.operation.restricted;

import ca.ulaval.ift6002.m2.domain.operation.OperationData;
import ca.ulaval.ift6002.m2.domain.operation.OperationType;

public class EyeOperation extends RestrictedOperation {

    public EyeOperation(OperationData operationData) {
        super(operationData);
    }

    @Override
    public OperationType getType() {
        return OperationType.EYE;
    }
}
