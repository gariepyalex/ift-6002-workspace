package ca.ulaval.ift6002.m2.infrastructure.persistence.factory;

import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentRepository;
import ca.ulaval.ift6002.m2.domain.operation.OperationRepository;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;

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
    public PatientRepository createPatientRepository() {
        return null;
    }

}
