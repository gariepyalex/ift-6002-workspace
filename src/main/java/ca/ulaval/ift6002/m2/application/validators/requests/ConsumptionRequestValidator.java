package ca.ulaval.ift6002.m2.application.validators.requests;

import ca.ulaval.ift6002.m2.application.requests.ConsumptionRequest;
import ca.ulaval.ift6002.m2.domain.date.DateFormatter;

public class ConsumptionRequestValidator {

    private static final String MISSING_INFORMATION = "PRES013";

    public void validate(ConsumptionRequest request) {
        if (!DateFormatter.isValid(request.date)) {
            throw new InvalidRequestException(MISSING_INFORMATION, "The date format is invalid.");
        }
        if (isPharmacyNotSet(request)) {
            throw new InvalidRequestException(MISSING_INFORMATION, "A pharmacy description must be set");
        }
        if (isConsumptionsCountNotSet(request)) {
            throw new InvalidRequestException(MISSING_INFORMATION, "A consumptions count must be set");
        }
    }

    private boolean isPharmacyNotSet(ConsumptionRequest request) {
        return request.pharmacy.trim().isEmpty();
    }

    private boolean isConsumptionsCountNotSet(ConsumptionRequest request) {
        return request.consumptions == null || request.consumptions <= 0;
    }

}
