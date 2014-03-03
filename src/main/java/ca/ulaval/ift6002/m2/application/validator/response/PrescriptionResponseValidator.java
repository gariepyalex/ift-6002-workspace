package ca.ulaval.ift6002.m2.application.validator.response;

import ca.ulaval.ift6002.m2.application.responses.PrescriptionResponse;

public class PrescriptionResponseValidator implements ResponseValidator<PrescriptionResponse> {

    private static final String ERROR_CODE = "PRES001";
    private static final String NOT_ENOUGH_RENEWALS_MESSAGE = "The number of renewals must be greater than or equals to zero";
    private static final String NO_DIN_OR_NAME_MESSAGE = "A din or name must be set";
    private static final String DIN_AND_NAME_MESSAGE = "You cannot set din and name at the same time";

    @Override
    public void validate(PrescriptionResponse response) throws InvalidResponseException {
        if (!hasEnoughRenewals(response)) {
            throw new InvalidResponseException(ERROR_CODE, NOT_ENOUGH_RENEWALS_MESSAGE);
        }

        if (hasNotSetDinOrName(response)) {
            throw new InvalidResponseException(ERROR_CODE, NO_DIN_OR_NAME_MESSAGE);
        }

        if (hasSetBothDinAndName(response)) {
            throw new InvalidResponseException(ERROR_CODE, DIN_AND_NAME_MESSAGE);
        }
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
