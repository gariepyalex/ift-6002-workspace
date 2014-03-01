package ca.ulaval.ift6002.m2.domain.operation;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;

@RunWith(MockitoJUnitRunner.class)
public class OperationTest {

    @Mock
    private Instrument instrument;

    @Mock
    private Instrument anonymousInstrument;

    @Mock
    private Operation.Builder builder;

    private Operation operation;

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
        boolean hasInstrument = operation.has(instrument);
        assertFalse(hasInstrument);
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

    private void buildAnOperation() {
        // It could be any type of operation
        operation = createEligibleOperation();
    }

    private Operation createEligibleOperation() {
        Operation eligibleOperation;

        eligibleOperation = new Operation(builder) {

            @Override
            public void add(Instrument instrument) {
            }
        };

        return eligibleOperation;
    }

    private Operation createNonEligibleOperation() {
        Operation nonEligibleOperation;

        nonEligibleOperation = new Operation(builder) {

            @Override
            public void add(Instrument instrument) {
            }
        };

        return nonEligibleOperation;
    }
}
