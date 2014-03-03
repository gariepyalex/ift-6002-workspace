package ca.ulaval.ift6002.m2.application.validator.response;

import ca.ulaval.ift6002.m2.application.responses.InstrumentResponse;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentStatus;

public class InstrumentResponseValidator implements ResponseValidator<InstrumentResponse> {

    private static final String MISSING_SERIAL_CODE = "INT012";
    private static final String MISSING_SERIAL_MESSAGE = "Requires serial number";

    private static final String INCOMPLETE_DATA_ERROR = "INT010";
    private static final String INCOMPLETE_DATA_MESSAGE = "Invalid or incomplete data";

    @Override
    public void validate(InstrumentResponse response) throws InvalidResponseException {
        if (response.typecode.isEmpty()) {
            throw new InvalidResponseException(INCOMPLETE_DATA_ERROR, INCOMPLETE_DATA_MESSAGE);
        }

        if (isStatusNotValid(response)) {
            throw new InvalidResponseException(INCOMPLETE_DATA_ERROR, INCOMPLETE_DATA_MESSAGE);
        }
    }

    public void validateNewStatus(InstrumentResponse response) throws InvalidResponseException {
        if (response.serial.isEmpty()) {
            throw new InvalidResponseException(MISSING_SERIAL_CODE, MISSING_SERIAL_MESSAGE);
        }

        if (isStatusNotValid(response)) {
            throw new InvalidResponseException(INCOMPLETE_DATA_ERROR, INCOMPLETE_DATA_MESSAGE);
        }
    }

    private boolean isStatusNotValid(InstrumentResponse response) {
        return !InstrumentStatus.isValid(response.status);
    }
}
