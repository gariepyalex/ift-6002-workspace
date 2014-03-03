package ca.ulaval.ift6002.m2.infrastructure.persistence.locator;

import java.util.HashMap;
import java.util.Map;

import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.domain.operation.OperationRepository;
import ca.ulaval.ift6002.m2.domain.operation.patient.PatientRepository;

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

    public static OperationRepository getOperationRepository() {
        return (OperationRepository) soleInstance.repositories.get(OperationRepository.class);
    }
}
