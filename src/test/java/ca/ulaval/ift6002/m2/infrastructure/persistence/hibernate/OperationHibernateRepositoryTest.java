package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import java.util.NoSuchElementException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.operation.Operation;
import ca.ulaval.ift6002.m2.domain.operation.OperationFactory;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.entities.OperationHibernateData;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerProvider;

@RunWith(MockitoJUnitRunner.class)
public class OperationHibernateRepositoryTest {

    private static final Integer OPERATION_NUMBER = 1;
    private static final Integer UNEXISTING_OPERATION_NUMBER = 0;

    @Mock
    private OperationHibernateData operationData;

    @Mock
    private OperationFactory operationFactory;

    @Mock
    private Operation operation;

    @Mock
    private EntityManagerProvider entityManagerProvider;

    @Mock
    private EntityManager entityManager;

    @Mock
    private EntityTransaction transaction;

    @InjectMocks
    private OperationHibernateRepository operationRepository;

    @Before
    public void setUp() {
        willReturn(entityManager).given(entityManagerProvider).getEntityManager();
        willReturn(transaction).given(entityManager).getTransaction();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGettingUnknownOperationShouldThowException() {
        willReturn(null).given(entityManager).find(OperationHibernateData.class, UNEXISTING_OPERATION_NUMBER);

        operationRepository.get(UNEXISTING_OPERATION_NUMBER);
    }

    @Test
    public void whenGettingOperationShouldCallEntityManagerFind() {
        willReturn(operationData).given(entityManager).find(OperationHibernateData.class, OPERATION_NUMBER);
        operationRepository.get(OPERATION_NUMBER);
        verify(entityManager).find(OperationHibernateData.class, OPERATION_NUMBER);
    }

    @Test
    public void whenStoreOperationShouldCallEntityManagerMerge() {
        willReturn(operationData).given(entityManager).merge(any(OperationHibernateData.class));
        operationRepository.store(operation);
        verify(entityManager).merge(any(OperationHibernateData.class));
    }

}
