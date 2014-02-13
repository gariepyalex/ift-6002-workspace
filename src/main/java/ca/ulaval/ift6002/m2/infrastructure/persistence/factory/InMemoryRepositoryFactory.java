package ca.ulaval.ift6002.m2.infrastructure.persistence.factory;

import ca.ulaval.ift6002.m2.domain.drug.CSVDrugDataAdapter;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentRepository;
import ca.ulaval.ift6002.m2.domain.operation.OperationRepository;
import ca.ulaval.ift6002.m2.domain.prescription.PrescriptionRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.inmemory.repository.DrugInMemoryRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.inmemory.repository.InstrumentInMemoryRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.inmemory.repository.OperationInMemoryRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.inmemory.repository.PrescriptionInMemoryRepository;

public class InMemoryRepositoryFactory implements RepositoryFactory {

    @Override
    public DrugRepository createDrugRepository() {
        return new DrugInMemoryRepository(new CSVDrugDataAdapter());
    }

    @Override
    public InstrumentRepository createInstrumentRepository() {
        return new InstrumentInMemoryRepository();
    }

    @Override
    public OperationRepository createOperationRepository() {
        return new OperationInMemoryRepository();
    }

    @Override
    public PrescriptionRepository createPrescriptionRepository() {
        return new PrescriptionInMemoryRepository();
    }

}
