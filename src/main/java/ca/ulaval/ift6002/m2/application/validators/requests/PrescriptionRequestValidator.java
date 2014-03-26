package ca.ulaval.ift6002.m2.application.validators.requests;

import ca.ulaval.ift6002.m2.application.requests.PrescriptionRequest;
import ca.ulaval.ift6002.m2.domain.date.DateFormatter;

public class PrescriptionRequestValidator {

    private static final String ERROR_CODE = "PRES001";

    public void validate(PrescriptionRequest request) {
        if (!hasEnoughRenewals(request)) {
            throw new InvalidRequestException(ERROR_CODE,
                    "The number of renewals must be greater than or equals to zero");
        }

        if (hasNotSetDinOrName(request)) {
            throw new InvalidRequestException(ERROR_CODE, "A din or name must be set");
        }

        if (hasSetBothDinAndName(request)) {
            throw new InvalidRequestException(ERROR_CODE, "You cannot set din and name at the same time");
        }

        if (!hasValidDateFormat(request)) {
            throw new InvalidRequestException(ERROR_CODE, "The date format is invalid.");
        }
    }

    private boolean hasValidDateFormat(PrescriptionRequest request) {
        return DateFormatter.isValid(request.date);
    }

    private boolean hasEnoughRenewals(PrescriptionRequest request) {
        return request.renewals != null && request.renewals >= 0;
    }

    private boolean isDinSet(PrescriptionRequest request) {
        return !request.din.trim().isEmpty();
    }

    private boolean isNameSet(PrescriptionRequest request) {
        return !request.name.trim().isEmpty();
    }

    private boolean hasNotSetDinOrName(PrescriptionRequest request) {
        return !isDinSet(request) && !isNameSet(request);
    }

    private boolean hasSetBothDinAndName(PrescriptionRequest request) {
        return isDinSet(request) && isNameSet(request);
    }

}
