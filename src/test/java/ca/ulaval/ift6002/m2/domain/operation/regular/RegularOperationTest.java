package ca.ulaval.ift6002.m2.domain.operation.regular;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.operation.OperationData;

@RunWith(MockitoJUnitRunner.class)
public class RegularOperationTest {

    @Mock
    private OperationData operationData;

    @Mock
    private Instrument instrument;

    private RegularOperation regularOperation;

    @Before
    public void setUp() {
        regularOperation = mock(RegularOperation.class, CALLS_REAL_METHODS);
    }

    @Test
    public void whenCheckingForInstrumentElligibilityShouldAlwaysReturnTrue() {
        assertTrue(regularOperation.isInstrumentElligible(instrument));
    }

}
