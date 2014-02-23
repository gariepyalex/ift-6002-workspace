package ca.ulaval.ift6002.m2.application.validator.dto;

import ca.ulaval.ift6002.m2.application.responses.PrescriptionDTO;

public class PrescriptionDTOValidator implements DTOValidator<PrescriptionDTO> {

    @Override
    public void validate(PrescriptionDTO dto) throws InvalidDTOException {
        if (!hasEnoughRenewals(dto)) {
            throw new InvalidDTOException("The number of renewals must be greater than or equals to zero");
        }

        if (!hasSetDinOrName(dto)) {
            throw new InvalidDTOException("A din or name must be set");
        }

        if (hasSetBothDinAndName(dto)) {
            throw new InvalidDTOException("You cannot set din and name at the same time");
        }
    }

    private boolean hasEnoughRenewals(PrescriptionDTO dto) {
        return (dto.renewals != null && dto.renewals >= 0);
    }

    private boolean isDinSet(PrescriptionDTO dto) {
        return (!dto.din.trim().isEmpty());
    }

    private boolean isNameSet(PrescriptionDTO dto) {
        return (!dto.name.trim().isEmpty());
    }

    private boolean hasSetDinOrName(PrescriptionDTO dto) {
        return (isDinSet(dto) || isNameSet(dto));
    }

    private boolean hasSetBothDinAndName(PrescriptionDTO dto) {
        return (isDinSet(dto) && isNameSet(dto));
    }

}
