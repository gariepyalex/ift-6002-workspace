package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.NoSuchElementException;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers.DrugDTOAssembler;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.DrugDTO;

@RunWith(MockitoJUnitRunner.class)
public class DrugHibernateRepositoryTest {

    private static final Din TYLENOL_DIN = new Din("111111");
    private static final Din UNEXISTING_DIN = new Din("abcde");

    private static final String TYLENOL_BRAND_NAME = "TYLENOL";
    private static final String TYLENOL_DESCRIPTOR = "ACETAMINOPHENE";

    private static final Drug TYLENOL = new Drug(TYLENOL_DIN, TYLENOL_BRAND_NAME, TYLENOL_DESCRIPTOR);

    private static final DrugDTO TYLENOL_DTO = new DrugDTO(TYLENOL_DIN.getValue(), TYLENOL_BRAND_NAME,
            TYLENOL_DESCRIPTOR);

    @Mock
    private EntityManager entityManager;

    @Mock
    private DrugDTOAssembler drugDTOAssembler;

    private DrugRepository drugRepository;

    @Before
    public void setUp() {
        drugRepository = new DrugHibernateRepository(entityManager, drugDTOAssembler);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGettingUnexistingDinShouldThrowException() {
        willReturn(null).given(entityManager).find(DrugDTO.class, UNEXISTING_DIN.getValue());

        drugRepository.get(UNEXISTING_DIN);
    }

    @Test
    public void whenGettingTylenolDinShouldVerifyFindHibernateCall() {
        setUpEntityManagerWithTylenol();
        drugRepository.get(TYLENOL_DIN);
        verify(entityManager, times(1)).find(DrugDTO.class, TYLENOL_DIN.getValue());
    }

    @Test
    public void whenGettingTylenolDinShouldVerifyFromDTODrugAssemblerCall() {
        setUpEntityManagerWithTylenol();
        drugRepository.get(TYLENOL_DIN);
        verify(drugDTOAssembler, times(1)).fromDTO(TYLENOL_DTO);
    }

    @Test
    public void whenStoreTylenolShouldVerifyToDTODrugAssemblerCall() {
        drugRepository.store(TYLENOL);

        verify(drugDTOAssembler, times(1)).toDTO(TYLENOL);
    }

    @Test
    public void whenStoreTylenolShouldVerifyPersistHibernateCall() {
        willReturn(TYLENOL_DTO).given(drugDTOAssembler).toDTO(TYLENOL);
        drugRepository.store(TYLENOL);
        verify(entityManager, times(1)).persist(TYLENOL_DTO);
    }

    private void setUpEntityManagerWithTylenol() {
        willReturn(TYLENOL_DTO).given(entityManager).find(DrugDTO.class, TYLENOL_DIN.getValue());
    }
}
