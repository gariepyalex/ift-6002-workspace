package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.drug.DrugFactory;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.DrugDTO;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.entities.DrugHibernate;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerProvider;

@RunWith(MockitoJUnitRunner.class)
public class DrugHibernateRepositoryTest {

    private static final Din A_DIN = new Din("111111");
    private static final Din UNKNOWN_DIN = new Din("Unknown");

    private static final String A_DRUG_NAME = "A name";

    private static final String KEYWORD = "keyword";

    @Mock
    private DrugHibernate drug;

    @Mock
    private EntityManagerProvider entityManagerProvider;

    @Mock
    private DrugFactory drugFactory;

    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery<DrugHibernate> query;

    @InjectMocks
    private DrugHibernateRepository drugRepository;

    @Before
    public void setUp() {
        willReturn(entityManager).given(entityManagerProvider).getEntityManager();
    }

    @Test
    public void whenGettingDrugByNameShouldCallDrugFactoryCreate() {
        drugRepository.get(A_DRUG_NAME);

        verify(drugFactory).create(A_DRUG_NAME);
    }

    @Test
    public void whenStoreDrugsNotContainShouldCallEntityManagerPersist() {
        Collection<Drug> drugs = new ArrayList<Drug>(Arrays.asList(drug));
        willReturn(false).given(entityManager).contains(any(Drug.class));

        drugRepository.store(drugs);

        verify(entityManager, times(drugs.size())).persist(any(DrugDTO.class));
    }

    @Test
    public void whenStoreDrugsContainShouldNotCallEntityManagerPersist() {
        Collection<Drug> drugs = new ArrayList<Drug>(Arrays.asList(drug));
        willReturn(true).given(entityManager).contains(any(DrugHibernate.class));

        drugRepository.store(drugs);

        verify(entityManager, never()).persist(any(DrugDTO.class));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGettingUnknownDrugShouldThowException() {
        willReturn(query).given(entityManager).createQuery(anyString(), eq(DrugHibernate.class));
        willThrow(new RuntimeException()).given(query).getSingleResult();

        drugRepository.get(UNKNOWN_DIN);
    }

    @Test
    public void whenGettingDrugShouldCallTypedQueryGetSingleResult() {
        willReturn(query).given(entityManager).createQuery(anyString(), eq(DrugHibernate.class));
        drugRepository.get(A_DIN);
        verify(query).getSingleResult();
    }

    @Test
    public void whenFindByKeywordShouldCallTypedQueryGetResultList() {
        willReturn(query).given(entityManager).createQuery(anyString(), eq(DrugHibernate.class));
        drugRepository.findBy(KEYWORD);
        verify(query).getResultList();
    }

}
