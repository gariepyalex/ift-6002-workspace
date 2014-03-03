package ca.ulaval.ift6002.m2.infrastructure.persistence.factory;

import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.domain.operation.OperationRepository;
import ca.ulaval.ift6002.m2.domain.operation.patient.PatientRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers.DrugDTOAssembler;
import ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers.OperationDTOAssembler;
import ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers.PatientDTOAssembler;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.DrugHibernateRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.OperationHibernateRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.PatientHibernateRepository;

public class HibernateRepositoryFactory implements RepositoryFactory {

    @Override
    public DrugRepository createDrugRepository() {
        DrugDTOAssembler drugAssembler = new DrugDTOAssembler();

        return new DrugHibernateRepository(drugAssembler);
    }

    @Override
    public OperationRepository createOperationRepository() {
        OperationDTOAssembler operationAssembler = new OperationDTOAssembler();

        return new OperationHibernateRepository(operationAssembler);
    }

    @Override
    public PatientRepository createPatientRepository() {
        PatientDTOAssembler patientAssembler = new PatientDTOAssembler();

        return new PatientHibernateRepository(patientAssembler);
    }
}
