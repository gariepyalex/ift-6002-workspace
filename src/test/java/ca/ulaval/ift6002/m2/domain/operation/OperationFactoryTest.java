package ca.ulaval.ift6002.m2.domain.operation;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OperationFactoryTest {

    private OperationFactory factory;

    @Before
    public void setUp() {
        factory = new OperationFactory();
    }

    @Test
    public void testRandom() {
        assertTrue(true);
    }

}
