package ca.ulaval.ift6002.m2.application.assemblers;

import java.util.Date;

import ca.ulaval.ift6002.m2.application.responses.OperationResponse;
import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.operation.Description;
import ca.ulaval.ift6002.m2.domain.operation.Operation;
import ca.ulaval.ift6002.m2.domain.operation.OperationFactory;
import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.domain.room.Room;
import ca.ulaval.ift6002.m2.domain.room.RoomRepository;
import ca.ulaval.ift6002.m2.domain.surgeon.Surgeon;
import ca.ulaval.ift6002.m2.domain.surgeon.SurgeonRepository;

public class OperationResponseAssembler {

    private final OperationFactory operationFactory;
    private final PatientRepository patientRepository;
    private final SurgeonRepository surgeonRepository;
    private final RoomRepository roomRepository;
    private final DateFormatter formatterDate;

    public OperationResponseAssembler(OperationFactory operationfactory, PatientRepository patientRepository,
            SurgeonRepository surgeonRepository, RoomRepository roomRepository, DateFormatter formatterDate) {
        this.operationFactory = operationfactory;
        this.patientRepository = patientRepository;
        this.surgeonRepository = surgeonRepository;
        this.roomRepository = roomRepository;
        this.formatterDate = formatterDate;
    }

    public Operation fromResponse(OperationResponse response) {

        Patient aPatient = patientRepository.get(response.patientNumber);

        Date aDate = formatterDate.parse(response.date);

        Surgeon aSurgeon = surgeonRepository.get(response.surgeon);

        Room aRoom = roomRepository.get(response.room);

        Description aDescription = new Description(response.description);

        return operationFactory.create(response.type, aDescription, aSurgeon, aDate, aRoom, response.status, aPatient);

    }
}
