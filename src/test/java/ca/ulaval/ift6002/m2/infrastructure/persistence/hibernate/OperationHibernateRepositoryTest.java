package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.operation.Operation;
import ca.ulaval.ift6002.m2.domain.operation.OperationData;
import ca.ulaval.ift6002.m2.domain.operation.OperationFactory;
import ca.ulaval.ift6002.m2.domain.operation.OperationType;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.entities.OperationHibernateData;

@RunWith(MockitoJUnitRunner.class)
public class OperationHibernateRepositoryTest {

    private static final Integer OPERATION_NUMBER = 1;

    @Mock
    private Operation operation;

    @Mock
    private OperationHibernateData operationData;

    @Mock
    private HibernateRepository<OperationHibernateData> hibernateRepository;

    @Mock
    private OperationFactory operationFactory;

    @InjectMocks
    private OperationHibernateRepository operationRepository;

    @Test
    public void whenGettingAnOperationShouldVerifyHibernateRepository() {
        willReturn(operationData).given(hibernateRepository).find(OPERATION_NUMBER);
        operationRepository.get(OPERATION_NUMBER);
        verify(hibernateRepository).find(OPERATION_NUMBER);
    }

    @Test
    public void whenGettingAnOperationShouldVerifyOperationFactory() {
        willReturn(operationData).given(hibernateRepository).find(OPERATION_NUMBER);
        operationRepository.get(OPERATION_NUMBER);
        verify(operationFactory).create(any(OperationType.class), any(OperationData.class));
    }

    @Test
    public void whenStoringAnOperationShouldVerifyHibernateRepository() {
        operationRepository.store(operation);

        verify(hibernateRepository).storeElement(any(OperationHibernateData.class));
    }

}
