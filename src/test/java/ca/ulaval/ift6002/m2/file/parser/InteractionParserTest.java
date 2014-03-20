package ca.ulaval.ift6002.m2.file.parser;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.anyString;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.file.FileReader;

@RunWith(MockitoJUnitRunner.class)
public class InteractionParserTest {

    private static final String LINE_1 = "00000000=>11111111,22222222";
    private static final String LINE_2 = "11111111=>22222222,33333333";
    private static final List<String> LINES = Arrays.asList(LINE_1, LINE_2);

    private static final Din FIRST_LINE_DIN = new Din("00000000");
    private static final List<Din> FIRST_LINE_DIN_VALUES = Arrays.asList(new Din("11111111"), new Din("22222222"));

    private static final Din SECOND_LINE_DIN = new Din("11111111");
    private static final List<Din> SECOND_LINE_DIN_VALUES = Arrays.asList(new Din("22222222"), new Din("33333333"));

    @Mock
    private FileReader<String> fileReader;

    @InjectMocks
    private InteractionParser interactionParser;

    @Before
    public void setUp() {
        willReturn(LINES).given(fileReader).readAll(anyString());
    }

    @Test
    public void givenInteractionFileWhenParsingShouldReturnAMapOfInteractingDins() {
        Map<Din, List<Din>> interactingDins = interactionParser.parse();

        assertEquals(expectedMapOfInteractingDins(), interactingDins);
    }

    private Map<Din, List<Din>> expectedMapOfInteractingDins() {
        Map<Din, List<Din>> interactingDins = new HashMap<>();

        interactingDins.put(FIRST_LINE_DIN, FIRST_LINE_DIN_VALUES);
        interactingDins.put(SECOND_LINE_DIN, SECOND_LINE_DIN_VALUES);

        return interactingDins;
    }
}
