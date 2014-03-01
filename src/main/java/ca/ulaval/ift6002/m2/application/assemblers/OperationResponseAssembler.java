package ca.ulaval.ift6002.m2.application.assemblers;

import java.util.Date;

import ca.ulaval.ift6002.m2.application.responses.OperationResponse;
import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
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
    private final DateFormatter formatterDate = new DateFormatter();

    public OperationResponseAssembler(OperationFactory operationfactory, PatientRepository patientRepository,
            SurgeonRepository surgeoRepository, RoomRepository roomRepository) {
        this.operationFactory = operationfactory;
        this.patientRepository = patientRepository;
        this.surgeonRepository = surgeoRepository;
        this.roomRepository = roomRepository;
    }

    public Operation toOperation(OperationResponse response) {

        Patient aPatient = patientRepository.get(response.patientNumber);

        Date aDate = formatterDate.parse(response.date);

        Surgeon aSurgeon = surgeonRepository.get(response.surgeon);

        Room aRoom = roomRepository.get(response.room);

        return operationFactory.create(response.type, response.description, aSurgeon, aDate, aRoom, response.status,
                aPatient);

    }
}
