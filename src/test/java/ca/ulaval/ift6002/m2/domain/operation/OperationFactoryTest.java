package ca.ulaval.ift6002.m2.domain.operation;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.operation.regular.OncologicalOperation;
import ca.ulaval.ift6002.m2.domain.operation.restricted.EyeOperation;
import ca.ulaval.ift6002.m2.domain.operation.restricted.HeartOperation;
import ca.ulaval.ift6002.m2.domain.operation.restricted.MarrowOperation;

@RunWith(MockitoJUnitRunner.class)
public class OperationFactoryTest {

    @Mock
    private OperationData operationData;

    private OperationFactory factory;

    @Before
    public void setUp() {
        factory = mock(OperationFactory.class, CALLS_REAL_METHODS);
    }

    @Test
    public void whenTypeIsEyeShouldReturnEyeOperation() {
        Operation operation = factory.create(OperationType.EYE, operationData);

        assertEquals(EyeOperation.class, operation.getClass());
    }

    @Test
    public void whenTypeIsHeartShouldReturnHeartOperation() {
        Operation operation = factory.create(OperationType.HEART, operationData);

        assertEquals(HeartOperation.class, operation.getClass());
    }

    @Test
    public void whenTypeIsMarrowShouldReturnMarrowOperation() {
        Operation operation = factory.create(OperationType.MARROW, operationData);

        assertEquals(MarrowOperation.class, operation.getClass());
    }

    @Test
    public void whenTypeIsOncolyShouldReturnOncologicalOperation() {
        Operation operation = factory.create(OperationType.ONCOLOGY, operationData);

        assertEquals(OncologicalOperation.class, operation.getClass());
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCreateWithUnknownTypeShouldThrowException() {
        factory.create(OperationType.OTHER, operationData);
    }

}
