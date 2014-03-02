package ca.ulaval.ift6002.m2.infrastructure.persistence.locator;

import java.util.HashMap;
import java.util.Map;

import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentRepository;
import ca.ulaval.ift6002.m2.domain.operation.OperationRepository;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.domain.room.RoomRepository;
import ca.ulaval.ift6002.m2.domain.surgeon.SurgeonRepository;

public class RepositoryLocator {

    private static RepositoryLocator soleInstance;

    public static void load(RepositoryLocator instance) {
        soleInstance = instance;
    }

    private final Map<Class<?>, Object> repositories = new HashMap<>();

    public void register(Class<?> interfaceClass, Object implementation) {
        repositories.put(interfaceClass, implementation);
    }

    public static DrugRepository getDrugRepository() {
        return (DrugRepository) soleInstance.repositories.get(DrugRepository.class);
    }

    public static PatientRepository getPatientRepository() {
        return (PatientRepository) soleInstance.repositories.get(PatientRepository.class);
    }

    public static InstrumentRepository getInstrumentRepository() {
        return (InstrumentRepository) soleInstance.repositories.get(InstrumentRepository.class);
    }

    public static OperationRepository getOperationRepository() {
        return (OperationRepository) soleInstance.repositories.get(OperationRepository.class);
    }

    public static SurgeonRepository getSurgeonRepository() {
        return (SurgeonRepository) soleInstance.repositories.get(SurgeonRepository.class);
    }

    public static RoomRepository getRoomRepository() {
        return (RoomRepository) soleInstance.repositories.get(RoomRepository.class);
    }
}
