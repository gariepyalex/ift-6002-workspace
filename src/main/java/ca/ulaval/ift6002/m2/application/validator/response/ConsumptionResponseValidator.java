package ca.ulaval.ift6002.m2.application.validator.response;

import ca.ulaval.ift6002.m2.application.responses.ConsumptionResponse;
import ca.ulaval.ift6002.m2.domain.date.DateFormatter;

public class ConsumptionResponseValidator implements ResponseValidator<ConsumptionResponse> {

    private static final String MISSING_INFORMATION = "PRES013";

    @Override
    public void validate(ConsumptionResponse response) throws InvalidResponseException {
        if (!DateFormatter.isValid(response.date)) {
            throw new InvalidResponseException(MISSING_INFORMATION,
                    "The date format is invalid. (yyyy-MM-dd'T'HH:mm:ss)");
        }
        if (isPharmacySet(response)) {
            throw new InvalidResponseException(MISSING_INFORMATION, "A pharmacy description must be set");
        }
        if (isConsumptionsCountSet(response)) {
            throw new InvalidResponseException(MISSING_INFORMATION, "A consumptions count must be set");
        }
    }

    private boolean isPharmacySet(ConsumptionResponse response) {
        return !response.pharmacy.trim().isEmpty();
    }

    private boolean isConsumptionsCountSet(ConsumptionResponse response) {
        return response.consumptions != null;
    }

}
