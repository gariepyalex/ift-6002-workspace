package ca.ulaval.ift6002.m2.application.validator.response;

import ca.ulaval.ift6002.m2.application.responses.ConsumptionResponse;
import ca.ulaval.ift6002.m2.domain.date.DateFormatter;

public class ConsumptionResponseValidator implements ResponseValidator<ConsumptionResponse> {

    private static final String MISSING_INFORMATION = "PRES013";

    @Override
    public void validate(ConsumptionResponse response) {
        if (!DateFormatter.isValid(response.date)) {
            throw new InvalidResponseException(MISSING_INFORMATION,
                    "The date format is invalid. (yyyy-MM-dd'T'HH:mm:ss)");
        }
        if (isPharmacyNotSet(response)) {
            throw new InvalidResponseException(MISSING_INFORMATION, "A pharmacy description must be set");
        }
        if (isConsumptionsCountNotSet(response)) {
            throw new InvalidResponseException(MISSING_INFORMATION, "A consumptions count must be set");
        }
    }

    private boolean isPharmacyNotSet(ConsumptionResponse response) {
        return response.pharmacy.trim().isEmpty();
    }

    private boolean isConsumptionsCountNotSet(ConsumptionResponse response) {
        return response.consumptions == null || response.consumptions <= 0;
    }

}
