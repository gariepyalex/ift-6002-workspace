package ca.ulaval.ift6002.m2.application.validator.response;

import ca.ulaval.ift6002.m2.application.responses.PrescriptionResponse;
import ca.ulaval.ift6002.m2.domain.date.DateFormatter;

public class PrescriptionResponseValidator implements ResponseValidator<PrescriptionResponse> {

    private static final String ERROR_CODE = "PRES001";

    @Override
    public void validate(PrescriptionResponse response) {
        if (!hasEnoughRenewals(response)) {
            throw new InvalidResponseException(ERROR_CODE,
                    "The number of renewals must be greater than or equals to zero");
        }

        if (hasNotSetDinOrName(response)) {
            throw new InvalidResponseException(ERROR_CODE, "A din or name must be set");
        }

        if (hasSetBothDinAndName(response)) {
            throw new InvalidResponseException(ERROR_CODE, "You cannot set din and name at the same time");
        }

        if (!hasValidDateFormat(response)) {
            throw new InvalidResponseException(ERROR_CODE, "The date format is invalid. (yyyy-MM-dd'T'HH:mm:ss)");
        }
    }

    private boolean hasValidDateFormat(PrescriptionResponse response) {
        return DateFormatter.isValid(response.date);
    }

    private boolean hasEnoughRenewals(PrescriptionResponse response) {
        return response.renewals != null && response.renewals >= 0;
    }

    private boolean isDinSet(PrescriptionResponse response) {
        return !response.din.trim().isEmpty();
    }

    private boolean isNameSet(PrescriptionResponse response) {
        return !response.name.trim().isEmpty();
    }

    private boolean hasNotSetDinOrName(PrescriptionResponse response) {
        return !isDinSet(response) && !isNameSet(response);
    }

    private boolean hasSetBothDinAndName(PrescriptionResponse response) {
        return isDinSet(response) && isNameSet(response);
    }

}
