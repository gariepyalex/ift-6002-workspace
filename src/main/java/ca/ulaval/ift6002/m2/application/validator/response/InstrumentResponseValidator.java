package ca.ulaval.ift6002.m2.application.validator.response;

import ca.ulaval.ift6002.m2.application.responses.InstrumentResponse;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentStatus;

public class InstrumentResponseValidator implements ResponseValidator<InstrumentResponse> {

    @Override
    public void validate(InstrumentResponse response) throws InvalidResponseException {
        if (!isTypecodeValid(response)) {
            throw new InvalidResponseException("Typecode must not be empty");
        }
        if (!InstrumentStatus.isValid(response.status)) {
            throw new InvalidResponseException("The status value is not valid");
        }
    }

    private boolean isTypecodeValid(InstrumentResponse response) {
        return !response.typecode.isEmpty();
    }
}
