package ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers;

import ca.ulaval.ift6002.m2.domain.patient.Patient;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.PatientDTO;

public class PatientDTOAssembler {

    public Patient fromDTO(PatientDTO dto) {
        return new Patient(dto.number);
    }

    public PatientDTO toDTO(Patient patient) {
        return new PatientDTO(patient.getNumber());
    }
}
