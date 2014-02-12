package projectH.application.assemblers;

import java.util.Date;

import projectH.application.responses.OperationDTO;
import projectH.domain.date.DateException;
import projectH.domain.date.DateFormatter;
import projectH.domain.operation.InvalidOperationException;
import projectH.domain.operation.Operation;

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
