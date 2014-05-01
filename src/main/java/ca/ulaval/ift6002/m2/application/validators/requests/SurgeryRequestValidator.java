package ca.ulaval.ift6002.m2.application.validators.requests;

import ca.ulaval.ift6002.m2.application.requests.SurgeryRequest;
import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.surgery.SurgeryStatus;
import ca.ulaval.ift6002.m2.domain.surgery.SurgeryType;

public class SurgeryRequestValidator {

    private static final String MISSING_INFORMATION = "INT001";

    public void validate(SurgeryRequest request) {
        if (request.description.isEmpty()) {
            throw new InvalidRequestException(MISSING_INFORMATION, "Description is empty");
        }

        if (request.surgeon == null) {
            throw new InvalidRequestException(MISSING_INFORMATION, "Surgeon number is missing");
        }

        if (!DateFormatter.isValid(request.date)) {
            throw new InvalidRequestException(MISSING_INFORMATION, "The date format is invalid.");
        }

        if (request.room.isEmpty()) {
            throw new InvalidRequestException(MISSING_INFORMATION, "Room is empty");
        }

        if (!SurgeryType.isValid(request.type)) {
            throw new InvalidRequestException(MISSING_INFORMATION, "Type is invalid");
        }

        if (!SurgeryStatus.isValid(request.status)) {
            throw new InvalidRequestException(MISSING_INFORMATION, "Status is invalid");
        }

        if (request.patientNumber == null) {
            throw new InvalidRequestException(MISSING_INFORMATION, "Patient number is missing");
        }
    }
}
