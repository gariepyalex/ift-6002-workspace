package ca.ulaval.ift6002.m2.infrastructure.persistence.factory;

import ca.ulaval.ift6002.m2.domain.date.DateFormatter;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.domain.operation.OperationRepository;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers.DrugDTOAssembler;
import ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers.PatientDTOAssembler;
import ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers.PrescriptionDTOAssembler;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.DrugHibernateRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.PatientHibernateRepository;

public class HibernateRepositoryFactory implements RepositoryFactory {

    @Override
    public DrugRepository createDrugRepository() {
        DrugDTOAssembler drugAssembler = new DrugDTOAssembler();

        return new DrugHibernateRepository(drugAssembler);
    }

    @Override
    public OperationRepository createOperationRepository() {
        return null;
    }

    @Override
    public PatientRepository createPatientRepository() {
        PrescriptionDTOAssembler prescriptionDTOAssembler = new PrescriptionDTOAssembler(new DateFormatter(),
                new DrugDTOAssembler());
        PatientDTOAssembler patientAssembler = new PatientDTOAssembler(prescriptionDTOAssembler);

        return new PatientHibernateRepository(patientAssembler);
    }
}
