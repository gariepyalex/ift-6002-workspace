package ca.ulaval.ift6002.m2.infrastructure.persistence.inmemory.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;

@RunWith(MockitoJUnitRunner.class)
public class InstrumentInMemoryRepositoryTest {

    private static final String INSTRUMENT_NUMBER = "321654";
    private InstrumentInMemoryRepository instrumentRepository;

    @Mock
    private Instrument instrument;

    @Before
    public void setup() {
        instrumentRepository = new InstrumentInMemoryRepository();
    }

    @Test
    public void canCreateEmptyRepository() {
        assertTrue(instrumentRepository.isEmpty());
    }

    @Test
    public void canSaveInstrument() {
        instrumentRepository.save(INSTRUMENT_NUMBER, instrument);
        assertFalse(instrumentRepository.isEmpty());
    }
}
