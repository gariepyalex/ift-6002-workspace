package ca.ulaval.ift6002.m2.application.assemblers;

import java.util.Date;

import ca.ulaval.ift6002.m2.application.responses.OperationDTO;
import ca.ulaval.ift6002.m2.domain.date.DateException;
import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.operation.InvalidOperationException;
import ca.ulaval.ift6002.m2.domain.operation.Operation;

public class OperationDTOAssembler {
    private DateFormatter formaterDat;
    private Date theDate;

    public Operation toOperation(OperationDTO operationDto) {

        try {
            theDate = formaterDat.parse(operationDto.date);
        } catch (DateException e) {
            throw new InvalidOperationException(e.getMessage());
        }

        return new Operation(operationDto.description, operationDto.surgeon, theDate, operationDto.room,
                operationDto.type, operationDto.patientNumber);
    }
}
