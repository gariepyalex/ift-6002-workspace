package ca.ulaval.ift6002.m2.application.validator.response;

import ca.ulaval.ift6002.m2.application.responses.InstrumentResponse;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentStatus;

public class InstrumentResponseValidator implements ResponseValidator<InstrumentResponse> {

    @Override
    public void validate(InstrumentResponse response) throws InvalidResponseException {
        if (response.typecode.isEmpty()) {
            throw new InvalidResponseException("Typecode must not be empty");
        }

        if (isStatusNotValid(response)) {
            throw new InvalidResponseException("The status value is not valid");
        }
    }

    public void validateNewStatus(InstrumentResponse response) throws InvalidResponseException {
        if (response.serial.isEmpty()) {
            throw new InvalidResponseException("Serial must not be empty");
        }

        if (isStatusNotValid(response)) {
            throw new InvalidResponseException("The status value is not valid");
        }
    }

    private boolean isStatusNotValid(InstrumentResponse response) {
        return !InstrumentStatus.isValid(response.status);
    }
}
