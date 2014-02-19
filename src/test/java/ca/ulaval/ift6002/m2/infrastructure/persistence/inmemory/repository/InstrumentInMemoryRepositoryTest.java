package ca.ulaval.ift6002.m2.infrastructure.persistence.inmemory.repository;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.operation.Operation;

@RunWith(MockitoJUnitRunner.class)
public class InstrumentInMemoryRepositoryTest {

    private InstrumentInMemoryRepository instrumentRepository;

    @Mock
    private Operation operation;

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
        instrumentRepository.store(operation, instrument);
        assertFalse(instrumentRepository.isEmpty());
    }
}
