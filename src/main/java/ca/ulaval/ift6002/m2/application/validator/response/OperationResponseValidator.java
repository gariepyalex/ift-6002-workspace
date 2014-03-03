package ca.ulaval.ift6002.m2.application.validator.response;

import ca.ulaval.ift6002.m2.application.responses.OperationResponse;

public class OperationResponseValidator implements ResponseValidator<OperationResponse> {

    private static final String MISSING_INFORMATION_CODE = "INT001";
    private static final String MISSING_INFORMATION_MESSAGE = "Invalid or incomplete data";

    @Override
    public void validate(OperationResponse response) throws InvalidResponseException {
        if (response.description.isEmpty()) {
            throw new InvalidResponseException(MISSING_INFORMATION_CODE, MISSING_INFORMATION_MESSAGE);
        } else if (response.surgeon == null) {
            throw new InvalidResponseException(MISSING_INFORMATION_CODE, MISSING_INFORMATION_MESSAGE);
        } else if (response.date.isEmpty()) {
            throw new InvalidResponseException(MISSING_INFORMATION_CODE, MISSING_INFORMATION_MESSAGE);
        } else if (response.room.isEmpty()) {
            throw new InvalidResponseException(MISSING_INFORMATION_CODE, MISSING_INFORMATION_MESSAGE);
        } else if (response.type.isEmpty()) {
            throw new InvalidResponseException(MISSING_INFORMATION_CODE, MISSING_INFORMATION_MESSAGE);
        } else if (response.patientNumber == null) {
            throw new InvalidResponseException(MISSING_INFORMATION_CODE, MISSING_INFORMATION_MESSAGE);
        }
    }

}
