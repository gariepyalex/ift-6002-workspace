package ca.ulaval.ift6002.m2.application.validator.response;

import ca.ulaval.ift6002.m2.application.responses.OperationResponse;

public class OperationResponseValidator implements ResponseValidator<OperationResponse> {

    private static final String MISSING_INFORMATION = "INT001";

    @Override
    public void validate(OperationResponse response) throws InvalidResponseException {
        if (response.description.isEmpty()) {
            throw new InvalidResponseException(MISSING_INFORMATION, "Description is empty");
        } else if (response.surgeon == null) {
            throw new InvalidResponseException(MISSING_INFORMATION, "Surgeon number must not be null");
        } else if (response.date.isEmpty()) {
            throw new InvalidResponseException(MISSING_INFORMATION, "Date is empty");
        } else if (response.room.isEmpty()) {
            throw new InvalidResponseException(MISSING_INFORMATION, "Room is empty");
        } else if (response.type.isEmpty()) {
            throw new InvalidResponseException(MISSING_INFORMATION, "Type is empty");
        } else if (response.patientNumber == null) {
            throw new InvalidResponseException(MISSING_INFORMATION, "Patient number must not be null");
        }
    }

}
