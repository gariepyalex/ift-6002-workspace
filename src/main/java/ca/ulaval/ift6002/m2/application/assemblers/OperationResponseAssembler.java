package ca.ulaval.ift6002.m2.application.assemblers;

import ca.ulaval.ift6002.m2.application.responses.OperationResponse;
import ca.ulaval.ift6002.m2.domain.operation.Operation;

public class OperationResponseAssembler {

    // private final OperationFactory operationFactory = new OperationFactory();
    Operation monOperation = new Operation();

    public Operation toResponse(OperationResponse response) {
        // operationFactory.create(response.type, builder);

    }
}
