package ca.ulaval.ift6002.m2.domain.operation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.willReturn;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;

@RunWith(MockitoJUnitRunner.class)
public class RegularOperationTest {

    @Mock
    private Operation.Builder builder;

    @InjectMocks
    private RegularOperation operation;

    @Mock
    private Instrument instrument;

    @Mock
    private Instrument anonymousInstrument;

    @Before
    public void setup() {
        willReturn(false).given(instrument).isAnonymous();
        willReturn(true).given(anonymousInstrument).isAnonymous();
    }

    @Test
    public void givenInstrumentWhenTestingElligibilityShouldReturnTrue() {
        boolean isElligible = operation.isInstrumentElligibleToOperation(instrument);

        assertTrue(isElligible);
    }

    @Test
    public void givenAnonymousInstrumentWhenTestingElligibilityShouldReturnFalse() {
        boolean isElligible = operation.isInstrumentElligibleToOperation(anonymousInstrument);

        assertFalse(isElligible);
    }

}
