package ca.ulaval.ift6002.m2.locator;

import java.util.HashMap;
import java.util.Map;

import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.domain.surgery.SurgeryRepository;

public class RepositoryLocator {

    private static RepositoryLocator instance;

    public static void load(RepositoryLocator repositoryLocator) {
        instance = repositoryLocator;
    }

    private final Map<Class<?>, Object> repositories = new HashMap<>();

    public void register(Class<?> interfaceClass, Object implementation) {
        repositories.put(interfaceClass, implementation);
    }

    public static DrugRepository getDrugRepository() {
        return (DrugRepository) instance.repositories.get(DrugRepository.class);
    }

    public static PatientRepository getPatientRepository() {
        return (PatientRepository) instance.repositories.get(PatientRepository.class);
    }

    public static SurgeryRepository getSurgeryRepository() {
        return (SurgeryRepository) instance.repositories.get(SurgeryRepository.class);
    }
}
