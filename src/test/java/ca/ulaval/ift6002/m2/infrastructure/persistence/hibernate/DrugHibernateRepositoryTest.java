package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyCollectionOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers.DrugDTOAssembler;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.DrugDTO;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerProvider;

@RunWith(MockitoJUnitRunner.class)
public class DrugHibernateRepositoryTest {

    private static final Din TYLENOL_DIN = new Din("111111");
    private static final String TYLENOL_BRAND_NAME = "TYLENOL";
    private static final String TYLENOL_DESCRIPTOR = "ACETAMINOPHENE";

    private static final Din UNKNOWN_DIN = new Din("Unknown");

    private static final Drug TYLENOL = new Drug(TYLENOL_DIN, TYLENOL_BRAND_NAME, TYLENOL_DESCRIPTOR);
    private static final DrugDTO TYLENOL_DTO = new DrugDTO(TYLENOL_DIN.getValue(), TYLENOL_BRAND_NAME,
            TYLENOL_DESCRIPTOR);

    private static final Collection<Drug> DRUGS = Arrays.asList(TYLENOL);
    private static final Collection<DrugDTO> DRUG_DTOS = Arrays.asList(TYLENOL_DTO);

    private static final String A_DRUG_NAME = "A name";

    private static final String AN_UNKOWN_NAME = "Unknown";

    private static final String KEYWORD = "keyword";

    @Mock
    private EntityManagerProvider entityManagerProvider;

    @Mock
    private DrugDTOAssembler drugDTOAssembler;

    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery<DrugDTO> query;
    @Mock
    private EntityTransaction transaction;

    @InjectMocks
    private DrugHibernateRepository drugRepository;

    @Before
    public void setUp() {
        willReturn(entityManager).given(entityManagerProvider).getEntityManager();
        willReturn(transaction).given(entityManager).getTransaction();
        willReturn(DRUG_DTOS).given(drugDTOAssembler).toDTOs(DRUGS);
        willReturn(query).given(entityManager).createQuery(anyString(), eq(DrugDTO.class));
    }

    @Test
    public void whenGettingDrugByNameShouldCallDrugAssemblerFromDTO() {
        willReturn(TYLENOL_DTO).given(query).getSingleResult();
        drugRepository.get(A_DRUG_NAME);
        verify(drugDTOAssembler).fromDTO(TYLENOL_DTO);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGettingUnknownDrugNameShouldThowException() {
        willThrow(new RuntimeException()).given(query).getSingleResult();
        drugRepository.get(AN_UNKOWN_NAME);
    }

    @Test
    public void whenGettingDrugByNameShouldCallQueryGetSingleResult() {
        drugRepository.get(A_DRUG_NAME);
        verify(query).getSingleResult();
    }

    @Test
    public void whenStoreDrugsShouldCallDrugAssemblerToDTOs() {
        drugRepository.store(DRUGS);

        verify(drugDTOAssembler).toDTOs(DRUGS);
    }

    @Test
    public void whenStoreDrugsShouldCallEntityManagerMerge() {
        drugRepository.store(DRUGS);

        verify(entityManager).persist(any(DrugDTO.class));
    }

    @Test
    public void whenGettingDrugShouldCallDrugAssemblerFromDTO() {
        willReturn(TYLENOL_DTO).given(query).getSingleResult();
        drugRepository.get(TYLENOL_DIN);
        verify(drugDTOAssembler).fromDTO(TYLENOL_DTO);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGettingUnknownDrugShouldThowException() {
        willThrow(new RuntimeException()).given(query).getSingleResult();
        drugRepository.get(UNKNOWN_DIN);
    }

    @Test
    public void whenGettingDrugShouldCallQueryGetSingleResult() {
        drugRepository.get(TYLENOL_DIN);
        verify(query).getSingleResult();
    }

    @Test
    public void whenFindByBrandNameOrDescriptionShouldCallDrugFromDTOs() {
        drugRepository.findByBrandNameOrDescriptor(KEYWORD);

        verify(drugDTOAssembler).fromDTOs(anyCollectionOf(DrugDTO.class));
    }

    @Test
    public void whenFindByBrandNameOrDescriptionShouldCallTypedQueryGetResultList() {
        drugRepository.findByBrandNameOrDescriptor(KEYWORD);

        verify(query).getResultList();
    }

}
