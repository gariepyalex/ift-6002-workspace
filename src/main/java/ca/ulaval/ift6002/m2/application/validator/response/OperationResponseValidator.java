package ca.ulaval.ift6002.m2.application.validator.response;

import ca.ulaval.ift6002.m2.application.responses.OperationResponse;

public class OperationResponseValidator implements ResponseValidator<OperationResponse> {

    @Override
    public void validate(OperationResponse response) throws InvalidResponseException {
        if (response.description.isEmpty()) {
            throw new InvalidResponseException("Description is empty!");
        } else if (response.room.isEmpty()) {
            throw new InvalidResponseException("Room is empty!");
        }
    }

}
