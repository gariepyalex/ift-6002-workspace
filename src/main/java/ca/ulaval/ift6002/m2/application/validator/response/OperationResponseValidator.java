package ca.ulaval.ift6002.m2.application.validator.response;

import ca.ulaval.ift6002.m2.application.responses.OperationResponse;
import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.operation.OperationStatus;
import ca.ulaval.ift6002.m2.domain.operation.OperationType;

public class OperationResponseValidator implements ResponseValidator<OperationResponse> {

    private static final String MISSING_INFORMATION = "INT001";

    @Override
    public void validate(OperationResponse response) {
        if (response.description.isEmpty()) {
            throw new InvalidResponseException(MISSING_INFORMATION, "Description is empty");
        }

        if (response.surgeon == null) {
            throw new InvalidResponseException(MISSING_INFORMATION, "Surgeon number is missing");
        }

        if (!DateFormatter.isValid(response.date)) {
            throw new InvalidResponseException(MISSING_INFORMATION,
                    "The date format is invalid. (yyyy-MM-dd'T'HH:mm:ss)");
        }

        if (response.room.isEmpty()) {
            throw new InvalidResponseException(MISSING_INFORMATION, "Room is empty");
        }

        if (isTypeInvalid(response.type)) {
            throw new InvalidResponseException(MISSING_INFORMATION, "Type is invalid");
        }

        if (isStatusInvalid(response.status)) {
            throw new InvalidResponseException(MISSING_INFORMATION, "Status is invalid");
        }

        if (response.patientNumber == null) {
            throw new InvalidResponseException(MISSING_INFORMATION, "Patient number is missing");
        }
    }

    private boolean isStatusInvalid(String status) {
        return !OperationStatus.isValid(status);
    }

    private boolean isTypeInvalid(String type) {
        return !OperationType.isValid(type);
    }

}
