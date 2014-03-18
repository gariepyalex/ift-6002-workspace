package ca.ulaval.ift6002.m2.domain.operation.regular;

import ca.ulaval.ift6002.m2.domain.operation.OperationData;
import ca.ulaval.ift6002.m2.domain.operation.OperationType;

public class OncologicalOperation extends RegularOperation {

    public OncologicalOperation(OperationData operationData) {
        super(operationData);
    }

    @Override
    public OperationType getType() {
        return OperationType.ONCOLOGY;
    }
}
