package ca.ulaval.ift6002.m2.application.assemblers;

import java.util.Date;

import ca.ulaval.ift6002.m2.application.responses.OperationResponse;
import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.operation.OperationFactory;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.domain.surgeon.SurgeonRepository;

public class OperationResponseAssembler {

    private OperationFactory operationFactory;
    private PatientRepository patientRepository;
    private SurgeonRepository surgeonRepository;

    Date aDate;
    private final DateFormatter formatterDate = new DateFormatter();

    public void toResponse(OperationResponse response) {

        patientRepository.get(response.patientNumber);

        formatterDate.parse(response.date);

        // Surgeon aSurgeon = sur
        //
        // operationFactory.create(response.type, response.description,
        // aSurgeon, aDate, aRoom, response.status, aPatient);

    }
}
