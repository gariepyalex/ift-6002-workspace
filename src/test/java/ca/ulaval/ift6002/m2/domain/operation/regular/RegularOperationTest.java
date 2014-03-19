package ca.ulaval.ift6002.m2.domain.operation.regular;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.operation.OperationData;
import ca.ulaval.ift6002.m2.domain.operation.OperationType;

@RunWith(MockitoJUnitRunner.class)
public class RegularOperationTest {

    @Mock
    private OperationData operationData;

    @Mock
    private Instrument instrument;

    private RegularOperation regularOperation;

    @Before
    public void setUp() {
        buildAnOperation();
    }

    @Test
    public void whenCheckingForInstrumentElligibilityShouldAlwaysReturnTrue() {
        assertTrue(regularOperation.isInstrumentElligible(instrument));
    }

    private void buildAnOperation() {
        regularOperation = new RegularOperation(operationData) {

            @Override
            public OperationType getType() {
                return OperationType.OTHER;
            }

        };
    }

}
