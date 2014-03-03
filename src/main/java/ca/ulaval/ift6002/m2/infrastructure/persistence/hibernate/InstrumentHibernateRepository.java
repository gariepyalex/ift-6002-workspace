package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.InstrumentDTO;

public class InstrumentHibernateRepository extends HibernateRepository<InstrumentDTO> implements InstrumentRepository {

    public InstrumentHibernateRepository(Class<InstrumentDTO> classType) {
        super(classType);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Instrument get(int serial) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void store(Instrument instrument) {
        // TODO Auto-generated method stub

    }

}
