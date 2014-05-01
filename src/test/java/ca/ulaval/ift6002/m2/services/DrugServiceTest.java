package ca.ulaval.ift6002.m2.services;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;

@RunWith(MockitoJUnitRunner.class)
public class DrugServiceTest {

    private static final String LESS_THAN_THREE_CHARACTERS_KEYWORD = "ab";
    private static final String VALID_KEYWORD = "John Smith";

    @Mock
    private DrugRepository drugRepository;

    @InjectMocks
    private DrugService drugService;

    @Test(expected = IllegalArgumentException.class)
    public void givenLessThanThreeCharactersKeywordsWhenFindingDrugsShouldThrowException() {
        drugService.findBy(LESS_THAN_THREE_CHARACTERS_KEYWORD);
    }

    @Test
    public void givenValidKeywordWhenFindingDrugsShouldCallDrugRepository() {
        drugService.findBy(VALID_KEYWORD);

        verify(drugRepository).findBy(VALID_KEYWORD);
    }
}
