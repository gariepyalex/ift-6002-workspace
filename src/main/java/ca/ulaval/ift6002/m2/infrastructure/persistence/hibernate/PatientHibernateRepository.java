package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate;

import java.util.NoSuchElementException;

import javax.persistence.EntityManager;

import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers.PatientDTOAssembler;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.PatientDTO;

public class PatientHibernateRepository implements PatientRepository {

    private final EntityManager entityManager;
    private final PatientDTOAssembler patientAssembler;

    public PatientHibernateRepository(EntityManager entityManager, PatientDTOAssembler patientAssembler) {
        this.entityManager = entityManager;
        this.patientAssembler = patientAssembler;
    }

    @Override
    public Patient get(int id) {
        PatientDTO dto = entityManager.find(PatientDTO.class, id);

        if (dto == null) {
            throw new NoSuchElementException("Cannot find a patient with number:" + id);
        }

        return patientAssembler.fromDTO(dto);
    }

    @Override
    public void store(Patient patient) {
        PatientDTO patientDTO = patientAssembler.toDTO(patient);
        entityManager.persist(patientDTO);
    }

}
