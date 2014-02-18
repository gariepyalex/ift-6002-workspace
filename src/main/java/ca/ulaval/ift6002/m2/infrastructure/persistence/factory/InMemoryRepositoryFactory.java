package ca.ulaval.ift6002.m2.infrastructure.persistence.factory;

import ca.ulaval.ift6002.m2.domain.drug.CSVDrugParser;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.domain.file.CSVFileReader;
import ca.ulaval.ift6002.m2.domain.file.FileParser;
import ca.ulaval.ift6002.m2.domain.file.FileReader;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentRepository;
import ca.ulaval.ift6002.m2.domain.operation.OperationRepository;
import ca.ulaval.ift6002.m2.domain.patient.PatientRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.filler.DrugRepositoryFiller;
import ca.ulaval.ift6002.m2.infrastructure.persistence.inmemory.repository.DrugInMemoryRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.inmemory.repository.InstrumentInMemoryRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.inmemory.repository.OperationInMemoryRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.inmemory.repository.PatientInMemoryRepository;

public class InMemoryRepositoryFactory implements RepositoryFactory {

    @Override
    public DrugRepository createDrugRepository() {
        FileReader<String[]> fileReader = new CSVFileReader();
        FileParser<Drug> drugParser = new CSVDrugParser(fileReader);
        DrugRepositoryFiller drugFiller = new DrugRepositoryFiller(drugParser);

        return new DrugInMemoryRepository(drugFiller);
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
    public PatientRepository createPatientRepository() {
        return new PatientInMemoryRepository();
    }

}
