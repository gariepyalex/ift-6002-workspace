package ca.ulaval.ift6002.m2.application.validators.requests;

import ca.ulaval.ift6002.m2.application.requests.OperationRequest;
import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.operation.OperationStatus;
import ca.ulaval.ift6002.m2.domain.operation.OperationType;

public class OperationRequestValidator implements RequestValidator<OperationRequest> {

    private static final String MISSING_INFORMATION = "INT001";

    @Override
    public void validate(OperationRequest request) {
        if (request.description.isEmpty()) {
            throw new InvalidRequestException(MISSING_INFORMATION, "Description is empty");
        }

        if (request.surgeon == null) {
            throw new InvalidRequestException(MISSING_INFORMATION, "Surgeon number is missing");
        }

        if (!DateFormatter.isValid(request.date)) {
            throw new InvalidRequestException(MISSING_INFORMATION, "The date format is invalid.");
        }

        if (request.room.isEmpty()) {
            throw new InvalidRequestException(MISSING_INFORMATION, "Room is empty");
        }

        if (!OperationType.isValid(request.type)) {
            throw new InvalidRequestException(MISSING_INFORMATION, "Type is invalid");
        }

        if (!OperationStatus.isValid(request.status)) {
            throw new InvalidRequestException(MISSING_INFORMATION, "Status is invalid");
        }

        if (request.patientNumber == null) {
            throw new InvalidRequestException(MISSING_INFORMATION, "Patient number is missing");
        }
    }
}
