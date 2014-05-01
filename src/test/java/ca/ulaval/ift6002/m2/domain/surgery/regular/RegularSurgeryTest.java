package ca.ulaval.ift6002.m2.domain.surgery.regular;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.surgery.SurgeryData;
import ca.ulaval.ift6002.m2.domain.surgery.regular.RegularSurgery;

@RunWith(MockitoJUnitRunner.class)
public class RegularSurgeryTest {

    @Mock
    private SurgeryData surgeryData;

    @Mock
    private Instrument instrument;

    private RegularSurgery regularSurgery;

    @Before
    public void setUp() {
        regularSurgery = mock(RegularSurgery.class, CALLS_REAL_METHODS);
    }

    @Test
    public void whenCheckingForInstrumentElligibilityShouldAlwaysReturnTrue() {
        assertTrue(regularSurgery.isInstrumentElligible(instrument));
    }

}
