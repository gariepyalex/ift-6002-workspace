package ca.ulaval.ift6002.m2.domain.operation.restricted;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.willReturn;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.operation.OperationData;
import ca.ulaval.ift6002.m2.domain.operation.OperationType;

@RunWith(MockitoJUnitRunner.class)
public class RestrictedOperationTest {

    @Mock
    private OperationData operationData;

    @Mock
    private Instrument instrument;

    @Mock
    private Instrument anonymousInstrument;

    private RestrictedOperation restrictedOperation;

    @Before
    public void setUp() {
        createRestrictedOperation();

        willReturn(false).given(instrument).isAnonymous();
        willReturn(true).given(instrument).hasASerial();

        willReturn(true).given(anonymousInstrument).isAnonymous();
    }

    @Test
    public void whenCheckingForInstrumentElligibilityWithAnonymousInstrumentShouldReturnFalse() {
        assertFalse(restrictedOperation.isInstrumentElligible(anonymousInstrument));
    }

    @Test
    public void whenCheckingForInstrumentElligibilityWithInstrumentShouldReturnTrue() {
        assertTrue(restrictedOperation.isInstrumentElligible(instrument));
    }

    private void createRestrictedOperation() {
        restrictedOperation = new RestrictedOperation(operationData) {

            @Override
            public OperationType getType() {
                return OperationType.OTHER;
            }

        };
    }
}
