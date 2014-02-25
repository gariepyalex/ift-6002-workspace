package ca.ulaval.ift6002.m2.domain.operation;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OperationFactoryTest {

    @Mock
    private Operation.Builder builder;

    private OperationFactory factory;

    @Before
    public void setUp() {
        factory = new OperationFactory();
    }

    @Test
    public void givenRegularTypeWhenCreatingOperationShouldReturnRegularOperationInstance() {
        OperationType regularType = createRegularType();
        willReturn(new RegularOperation(builder)).given(builder).build();

        Operation createdOperation = factory.create(regularType, builder);

        assertEquals(RegularOperation.class, createdOperation.getClass());
    }

    @Test
    public void givenDangerousTypeWhenCreatingOperationShouldReturnDangerousOperationInstance() {
        OperationType dangerousType = createDangerousType();
        willReturn(new DangerousOperation(builder)).given(builder).build();

        Operation createdOperation = factory.create(dangerousType, builder);

        assertEquals(DangerousOperation.class, createdOperation.getClass());
    }

    // TODO we cant mock enums... what should we do?
    private OperationType createRegularType() {
        return OperationType.OTHER;
    }

    private OperationType createDangerousType() {
        return OperationType.EYE;
    }
}
