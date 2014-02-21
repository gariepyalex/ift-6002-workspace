package ca.ulaval.ift6002.m2.domain.operation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.willReturn;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InvalidInstrumentException;

@RunWith(MockitoJUnitRunner.class)
public class OperationTest {

    @Mock
    private Instrument instrument;

    @Mock
    private Instrument anonymousInstrument;

    @Mock
    private Operation.Builder builder;

    // Minimal implementation of an eligible operation
    private final class EligibleOperation extends Operation {

        protected EligibleOperation() {
            super(builder);
        }

        @Override
        protected boolean isInstrumentElligibleToOperation(Instrument instrument) {
            return true;
        }
    }

    // Minimal implementation of an non eligible operation
    private final class NonEligibleOperation extends Operation {

        protected NonEligibleOperation() {
            super(builder);
        }

        @Override
        protected boolean isInstrumentElligibleToOperation(Instrument instrument) {
            return false;
        }
    }

    private Operation operation;

    private void buildAnOperation() {
        operation = new EligibleOperation();
    }

    private void buildEligibleOperation() {
        operation = new EligibleOperation();
    }

    private void buildNonEligibileOperation() {
        operation = new NonEligibleOperation();
    }

    @Before
    public void setupInstrument() {
        willReturn(true).given(anonymousInstrument).isAnonymous();
        willReturn(false).given(instrument).isAnonymous();
    }

    @Test
    public void givenOperationShouldHaveZeroInstrument() {
        buildAnOperation();

        assertEquals(0, operation.countInstruments());
    }

    @Test
    public void givenOperationShouldNotHaveAnyInstrument() {
        buildAnOperation();
        boolean hasInstrument = operation.hasInstrument(instrument);
        assertFalse(hasInstrument);
    }

    @Test(expected = InvalidInstrumentException.class)
    public void givenOperationWhenAddingSameInstrumentShouldThrowException() {
        buildAnOperation();

        operation.addInstrument(instrument);
        operation.addInstrument(instrument);
    }

    @Test(expected = InvalidInstrumentException.class)
    public void givenNonElligibleOperationWhenAddInstrumentShouldThrowException() {
        buildNonEligibileOperation();

        operation.addInstrument(instrument);
    }

    @Test
    public void givenElligibleOperationWhenAddInstrumentShouldHaveCountOfOne() {
        buildEligibleOperation();

        operation.addInstrument(instrument);
        int instrumentCount = operation.countInstruments();

        assertEquals(1, instrumentCount);
    }

    @Test
    public void givenElligibleOperationWhenAddInstrumentShouldContainsInstrument() {
        buildEligibleOperation();

        operation.addInstrument(instrument);
        boolean hasInstrument = operation.hasInstrument(instrument);

        assertTrue(hasInstrument);
    }

    @Test
    public void givenEyeTypeWhenBuildingOperationShouldReturnDangerousOperation() {
        Operation eyeOperation = new Operation.Builder(OperationType.EYE).build();

        assertDangerousOperation(eyeOperation);
    }

    @Test
    public void givenHeartTypeWhenBuildingOperationShouldReturnDangerousOperation() {
        Operation heartOperation = new Operation.Builder(OperationType.HEART).build();

        assertDangerousOperation(heartOperation);
    }

    @Test
    public void givenMarrowTypeWhenBuildingOperationShouldReturnDangerousOperation() {
        Operation marrowOperation = new Operation.Builder(OperationType.MARROW).build();

        assertDangerousOperation(marrowOperation);
    }

    @Test
    public void givenOncologyTypeWhenBuildingOperationShouldReturnRegularOperation() {
        Operation oncologyOperation = new Operation.Builder(OperationType.ONCOLOGY).build();

        assertRegularOperation(oncologyOperation);
    }

    @Test
    public void givenOtherTypeWhenBuildingOperationShouldReturnRegularOperation() {
        Operation otherOperation = new Operation.Builder(OperationType.OTHER).build();

        assertRegularOperation(otherOperation);
    }

    private void assertRegularOperation(Operation operation) {
        assertEquals(RegularOperation.class, operation.getClass());
    }

    private void assertDangerousOperation(Operation operation) {
        assertEquals(DangerousOperation.class, operation.getClass());
    }
}
