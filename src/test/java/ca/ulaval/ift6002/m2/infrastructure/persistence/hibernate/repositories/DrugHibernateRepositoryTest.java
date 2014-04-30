package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.repositories;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.anyCollectionOf;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.drug.DrugFactory;
import ca.ulaval.ift6002.m2.infrastructure.persistence.QueryBuilder;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.entities.DrugHibernate;

@RunWith(MockitoJUnitRunner.class)
public class DrugHibernateRepositoryTest {

    private static final Din A_DIN = new Din("111111");

    private static final String A_DRUG_NAME = "A name";

    private static final String KEYWORD = "keyword";

    @Mock
    private QueryBuilder<DrugHibernate> queryBuilder;

    @Mock
    private DrugFactory drugFactory;

    @Mock
    private HibernateRepository<DrugHibernate> hibernateRepository;

    @InjectMocks
    private DrugHibernateRepository drugRepository;

    @Test
    public void whenGettingDrugByNameShouldCallDrugFactoryCreate() {
        drugRepository.get(A_DRUG_NAME);

        verify(drugFactory).create(A_DRUG_NAME);
    }

    @Test
    public void whenGettingDrugWithDinShouldVerifyQueryBuilder() {
        setupQueryBuilder();
        drugRepository.get(A_DIN);
        verify(queryBuilder).get();
    }

    @Test
    public void whenFindingByKeywordShouldVerifyQueryBuilder() {
        setupQueryBuilder();
        drugRepository.findBy(KEYWORD);
        verify(queryBuilder).list();
    }

    @Test
    public void givenDrugsWhenStoringDrugsShouldVerifyHibernateRepository() {
        Collection<Drug> drugs = buildListOfDrugs();
        drugRepository.store(drugs);
        verify(hibernateRepository).storeElements(anyCollectionOf(DrugHibernate.class));
    }

    private void setupQueryBuilder() {
        willReturn(queryBuilder).given(hibernateRepository).getQueryBuilder();
        willReturn(queryBuilder).given(queryBuilder).query(anyString());
        willReturn(queryBuilder).given(queryBuilder).parameter(anyString(), anyObject());
    }

    private Collection<Drug> buildListOfDrugs() {
        DrugHibernate drug = mock(DrugHibernate.class);
        Collection<Drug> drugs = new ArrayList<Drug>(Arrays.asList(drug));
        return drugs;
    }
}
