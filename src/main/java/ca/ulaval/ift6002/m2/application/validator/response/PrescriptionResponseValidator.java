package ca.ulaval.ift6002.m2.application.validator.response;

import ca.ulaval.ift6002.m2.application.responses.PrescriptionResponse;

public class PrescriptionResponseValidator implements ResponseValidator<PrescriptionResponse> {

    @Override
    public void validate(PrescriptionResponse response) throws InvalidResponseException {
        if (!hasEnoughRenewals(response)) {
            throw new InvalidResponseException("The number of renewals must be greater than or equals to zero");
        }

        if (!hasSetDinOrName(response)) {
            throw new InvalidResponseException("A din or name must be set");
        }

        if (hasSetBothDinAndName(response)) {
            throw new InvalidResponseException("You cannot set din and name at the same time");
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

    private boolean hasSetDinOrName(PrescriptionResponse response) {
        return isDinSet(response) || isNameSet(response);
    }

    private boolean hasSetBothDinAndName(PrescriptionResponse response) {
        return isDinSet(response) && isNameSet(response);
    }

}
