package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate;

import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers.PatientDTOAssembler;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.PatientDTO;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerProvider;

public class PatientHibernateRepository extends HibernateRepository<PatientDTO> implements PatientRepository {

    private final PatientDTOAssembler patientAssembler;

    public PatientHibernateRepository() {
        super(PatientDTO.class);
        this.patientAssembler = new PatientDTOAssembler();
    }

    public PatientHibernateRepository(EntityManagerProvider entityManagerProvider, PatientDTOAssembler patientAssembler) {
        super(entityManagerProvider, PatientDTO.class);
        this.patientAssembler = patientAssembler;
    }

    @Override
    public Patient get(int number) {
        PatientDTO dto = find(number);

        return patientAssembler.fromDTO(dto);
    }

    @Override
    public void store(Patient patient) {
        PatientDTO patientDTO = patientAssembler.toDTO(patient);

        merge(patientDTO);
    }

}
