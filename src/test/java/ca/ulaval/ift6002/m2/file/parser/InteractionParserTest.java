package ca.ulaval.ift6002.m2.file.parser;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.anyString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.patient.Interaction;
import ca.ulaval.ift6002.m2.file.FileReader;

@RunWith(MockitoJUnitRunner.class)
public class InteractionParserTest {

    private static final Din DIN_0 = new Din("00000000");
    private static final Din DIN_1 = new Din("11111111");
    private static final Din DIN_2 = new Din("22222222");
    private static final Din DIN_3 = new Din("33333333");

    private static final List<Din> INTERACTING_WITH_DIN_0 = buildListOfElements(DIN_1, DIN_2);
    private static final List<Din> INTERACTING_WITH_DIN_1 = buildListOfElements(DIN_2, DIN_3);

    private static final Interaction INTERACTION_0 = new Interaction(DIN_0, INTERACTING_WITH_DIN_0);
    private static final Interaction INTERACTION_1 = new Interaction(DIN_1, INTERACTING_WITH_DIN_1);

    private static final List<Interaction> INTERACTIONS = buildListOfElements(INTERACTION_0, INTERACTION_1);

    private static final String LINE_0 = "00000000=>11111111,22222222";
    private static final String LINE_1 = "11111111=>22222222,33333333";
    private static final String[] LINES = { LINE_0, LINE_1 };
    private static final List<String> EXAMPLE_CONTENT_INTERACTIONFILE = Arrays.asList(LINES);

    @Mock
    FileReader<String> reader;

    @InjectMocks
    InteractionParser parser;

    @Before
    public void setUp() {
        willReturn(EXAMPLE_CONTENT_INTERACTIONFILE).given(reader).readAll(anyString());
    }

    @Test
    public void givenAListOfInteractionLinesWhenParseShouldReturnAListOfInteraction() {
        List<Interaction> parsedInteractions = new ArrayList<Interaction>();

        // TODO Correct this compilation error
        // parsedInteractions = parser.parse();
        //
        // assertEquals(INTERACTIONS, parsedInteractions);
    }

    @SafeVarargs
    private static <T> List<T> buildListOfElements(T... elements) {
        List<T> listOfElements = new ArrayList<T>();

        for (T element : elements) {
            listOfElements.add(element);
        }

        return listOfElements;
    }
}
