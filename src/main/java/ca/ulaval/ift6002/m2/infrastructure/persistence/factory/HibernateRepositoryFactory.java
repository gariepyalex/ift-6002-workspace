package ca.ulaval.ift6002.m2.infrastructure.persistence.factory;

import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentRepository;
import ca.ulaval.ift6002.m2.domain.operation.OperationRepository;
import ca.ulaval.ift6002.m2.domain.prescription.PrescriptionRepository;

public class HibernateRepositoryFactory implements RepositoryFactory {

    @Override
    public DrugRepository createDrugRepository() {
        return null;
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
    public PrescriptionRepository createPrescriptionRepository() {
        return null;
    }

}
