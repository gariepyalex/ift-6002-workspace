package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;
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
public class HibernateDrugRepositoryTest {

    private static final Din TYLENOL_DIN = new Din("111111");
    private static final Din TYLANETOL_DIN = new Din("222222");
    private static final Din UNEXISTING_DIN = new Din("abcde");

    private static final String UNEXISTING_BRAND_NAME = "UNEXISTING_BRAND_NAME";
    private static final String UNEXISTING_DESCRIPTOR = "UNEXISTING_DESCRIPTOR";

    private static final String TYLENOL_BRAND_NAME = "TYLENOL";
    private static final String TYLENOL_DESCRIPTOR_NAME = "ACETAMINOPHENE";

    private static final Drug TYLENOL = new Drug(TYLENOL_DIN, TYLENOL_BRAND_NAME, TYLENOL_DESCRIPTOR_NAME);
    private static final Drug TYLANETOL = new Drug(TYLANETOL_DIN, "TYLANETOL PRIME", "IBUPROPHENE");

    private static final DrugDTO TYLENOL_DTO = new DrugDTO(TYLENOL_DIN.getValue(), TYLENOL_BRAND_NAME,
            TYLENOL_DESCRIPTOR_NAME);

    private static final List<Drug> ALL_DRUGS = Arrays.asList(TYLENOL, TYLANETOL);

    private static final String CAMEL_CASE_TYLENOL_BRAND_NAME = "TyLeNoL";

    private static final String SIMPLE_SEARCH_PATTERN = "TYL";
    private static final String SEARCH_PATTERN_WILDCARD = "TYL PRI";
    private static final String PATTERN_WITH_MULTIPLE_WILDCARDS = "TY NE OL PRI ME";
    private static final String INVALID_KEYWORD = "123" + SEARCH_PATTERN_WILDCARD + "123";

    @Mock
    private EntityManager entityManager;

    @Mock
    private DrugDTOAssembler drugDTOAssembler;

    private DrugRepository drugRepository;

    @Before
    public void setup() {
        willReturn(TYLENOL_DTO).given(entityManager).find(DrugDTO.class, TYLENOL_DIN.getValue());
        willReturn(TYLENOL_DTO).given(drugDTOAssembler).toDTO(TYLENOL);
        willReturn(TYLENOL).given(drugDTOAssembler).fromDTO(TYLENOL_DTO);

        drugRepository = new HibernateDrugRepository(entityManager, drugDTOAssembler);
    }

    @Test
    public void givenRepositoryWhenGetByDinWithExistingDinShouldReturnCorrespondingDrug() {
        Drug drugFound = drugRepository.get(TYLENOL_DIN);

        assertEquals(TYLENOL, drugFound);
    }

    @Test(expected = NoSuchElementException.class)
    public void givenRepositoryWhenGetByDinWithUnexistingDinShouldThrowException() {
        drugRepository.get(UNEXISTING_DIN);
    }

    @Test
    public void givenRepositoryWhenStoreDrugShouldPersistDrug() {
        drugRepository.store(TYLENOL);
        verify(entityManager, times(1)).persist(TYLENOL_DTO);
    }
}
