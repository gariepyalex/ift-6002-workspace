package ca.ulaval.ift6002.m2.application.validator.response;

import ca.ulaval.ift6002.m2.application.responses.InstrumentResponse;

public class InstrumentResponseValidator implements ResponseValidator<InstrumentResponse> {

    @Override
    public void validate(InstrumentResponse response) throws InvalidResponseException {
        if (!isTypecodeValid(response)) {
            throw new InvalidResponseException("Typecode must not be empty");
        }
        if (!isStatusValid(response)) {
            throw new InvalidResponseException("The status value is not valid");
        }
    }

    private boolean isTypecodeValid(InstrumentResponse response) {
        return !response.typecode.isEmpty();
    }

    private boolean isStatusValid(InstrumentResponse response) {
        return (response.status.equals("SOILED") || response.status.equals("USED") || response.status.equals("UNUSED"));
    }

}
