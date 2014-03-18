package ca.ulaval.ift6002.m2.application.validator.request;

import ca.ulaval.ift6002.m2.application.requests.InstrumentRequest;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentStatus;

public class InstrumentRequestValidator implements RequestValidator<InstrumentRequest> {

    private static final String INCOMPLETE_DATA_ERROR = "INT010";
    private static final String MISSING_SERIAL_ERROR = "INT012";

    @Override
    public void validate(InstrumentRequest request) {
        if (request.typecode.isEmpty()) {
            throw new InvalidRequestException(INCOMPLETE_DATA_ERROR, "Typecode must not be empty");
        }

        if (!InstrumentStatus.isValid(request.status)) {
            throw new InvalidRequestException(INCOMPLETE_DATA_ERROR, "The status value is not valid");
        }
    }

    public void validateNewStatus(InstrumentRequest request) {
        if (request.serial.isEmpty()) {
            throw new InvalidRequestException(MISSING_SERIAL_ERROR, "Serial must not be empty");
        }

        if (!InstrumentStatus.isValid(request.status)) {
            throw new InvalidRequestException(INCOMPLETE_DATA_ERROR, "The status value is not valid");
        }
    }
}
