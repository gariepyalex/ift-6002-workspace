package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers.DrugDTOAssembler;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.DrugDTO;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerProvider;

@RunWith(MockitoJUnitRunner.class)
public class DrugHibernateRepositoryTest {

    private static final Din TYLENOL_DIN = new Din("111111");
    private static final String TYLENOL_BRAND_NAME = "TYLENOL";
    private static final String TYLENOL_DESCRIPTOR = "ACETAMINOPHENE";

    private static final Drug TYLENOL = new Drug(TYLENOL_DIN, TYLENOL_BRAND_NAME, TYLENOL_DESCRIPTOR);

    private static final Collection<Drug> DRUGS = Arrays.asList(TYLENOL);

    private static final DrugDTO TYLENOL_DTO = new DrugDTO(TYLENOL_DIN.getValue(), TYLENOL_BRAND_NAME,
            TYLENOL_DESCRIPTOR);

    private static final String A_NAME = "A name";
    private static final Drug DRUG_WITH_NAME = Drug.fromName(A_NAME);

    private static final String KEYWORD = "keyword";

    @Mock
    private EntityManagerProvider entityManagerProvider;

    @Mock
    private DrugDTOAssembler drugDTOAssembler;

    @Mock
    private EntityManager entityManager;

    private DrugRepository drugRepository;

    @Before
    public void setUp() {
        willReturn(entityManager).given(entityManagerProvider).getEntityManager();
        drugRepository = new DrugHibernateRepository(entityManagerProvider, drugDTOAssembler);
    }

    /*
     * @Test public void whenGettingTylenolDinShouldVerifyFindHibernateCall() {
     * setUpEntityManagerWithTylenol(); drugRepository.get(TYLENOL_DIN);
     * verify(entityManager, times(1)).find(DrugDTO.class,
     * TYLENOL_DIN.getValue()); }
     * 
     * @Test public void
     * whenGettingTylenolDinShouldVerifyFromDTODrugAssemblerCall() {
     * setUpEntityManagerWithTylenol(); drugRepository.get(TYLENOL_DIN);
     * verify(drugDTOAssembler, times(1)).fromDTO(TYLENOL_DTO); }
     */
    @Test
    public void whenGettingDrugByNameShouldReturnDrugWithName() {
        Drug drugBuilt = drugRepository.get(A_NAME);

        assertEquals(DRUG_WITH_NAME, drugBuilt);
    }

    @Test
    public void whenStoreDrugsShouldVerifyToDTOsDrugAssemblerCall() {
        setUpEntityManagerWithTransaction();

        drugRepository.store(DRUGS);

        verify(drugDTOAssembler, times(1)).toDTOs(DRUGS);
    }

    @Test
    @Ignore
    // TODO Do something with mock and TypedQuery...
    public void whenFindByBrandNameOrDescriptionShouldVerifyFromDTOsAssemblerCall() {
        drugRepository.findByBrandNameOrDescriptor(KEYWORD);

        verify(drugDTOAssembler, times(1)).fromDTOs(anyCollectionOf(DrugDTO.class));
    }

    private void setUpEntityManagerWithTylenol() {
        willReturn(TYLENOL_DTO).given(entityManager).find(DrugDTO.class, TYLENOL_DIN.getValue());
    }

    private void setUpEntityManagerWithTransaction() {
        EntityTransaction transaction = mock(EntityTransaction.class);
        willReturn(transaction).given(entityManager).getTransaction();
    }
}
