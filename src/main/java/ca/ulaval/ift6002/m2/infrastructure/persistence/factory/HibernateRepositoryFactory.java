package ca.ulaval.ift6002.m2.infrastructure.persistence.factory;

import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentRepository;
import ca.ulaval.ift6002.m2.domain.operation.OperationRepository;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers.DrugDTOAssembler;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.HibernateDrugRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.provider.EntityManagerProvider;

public class HibernateRepositoryFactory implements RepositoryFactory {

    @Override
    public DrugRepository createDrugRepository() {
        EntityManagerProvider entityManager = new EntityManagerProvider();
        DrugDTOAssembler assembler = new DrugDTOAssembler();
        return new HibernateDrugRepository(entityManager.getEntityManager(), assembler);
    }

    @Override
    public InstrumentRepository createInstrumentRepository() {
        return null;
    }

    @Override
    public OperationRepository createOperationRepository() {
        return null;
    }

    @Override
    public PatientRepository createPatientRepository() {
        return null;
    }

}
