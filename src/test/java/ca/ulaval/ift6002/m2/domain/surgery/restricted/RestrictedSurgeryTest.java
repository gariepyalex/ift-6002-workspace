package ca.ulaval.ift6002.m2.domain.surgery.restricted;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.surgery.SurgeryData;
import ca.ulaval.ift6002.m2.domain.surgery.restricted.RestrictedSurgery;

@RunWith(MockitoJUnitRunner.class)
public class RestrictedSurgeryTest {

    @Mock
    private SurgeryData surgeryData;

    @Mock
    private Instrument instrument;

    @Mock
    private Instrument anonymousInstrument;

    private RestrictedSurgery restrictedSurgery;

    @Before
    public void setUp() {
        restrictedSurgery = mock(RestrictedSurgery.class, CALLS_REAL_METHODS);

        willReturn(false).given(instrument).isAnonymous();
        willReturn(true).given(instrument).hasASerial();

        willReturn(true).given(anonymousInstrument).isAnonymous();
    }

    @Test
    public void whenCheckingForInstrumentElligibilityWithAnonymousInstrumentShouldReturnFalse() {
        assertFalse(restrictedSurgery.isInstrumentElligible(anonymousInstrument));
    }

    @Test
    public void whenCheckingForInstrumentElligibilityWithInstrumentShouldReturnTrue() {
        assertTrue(restrictedSurgery.isInstrumentElligible(instrument));
    }
}
