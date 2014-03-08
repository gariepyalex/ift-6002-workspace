package ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.patient.Interaction;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.InteractionDTO;

public class InteractionDTOAssemblerTest {
    private static final Din DIN_1 = new Din("11111111");
    private static final Din DIN_2 = new Din("22222222");
    private static final Din DIN_3 = new Din("33333333");
    private static final Din[] INTERACTING_DINS = { DIN_1, DIN_2, DIN_3 };
    private static final Interaction INTERACTION = new Interaction(new Din("00000000"), Arrays.asList(INTERACTING_DINS));

    private static final String[] INTERACTING_STRINGS = { "11111111", "22222222", "33333333" };
    private static final InteractionDTO INTERACTION_DTO = new InteractionDTO("00000000",
            Arrays.asList(INTERACTING_STRINGS));

    private InteractionDTOAssembler assembler;

    @Before
    public void setUp() {
        assembler = new InteractionDTOAssembler();
    }

    @Test
    public void givenInteractionWhenConvertToDTOShouldReturnGivenInteractionDTO() {
        InteractionDTO dtoBuilt = assembler.toDTO(INTERACTION);

        assertInteractionDTOEquals(INTERACTION_DTO, dtoBuilt);
    }

    @Test
    public void givenInteractionDTOWhenConvertToInteractionShouldReturnGivenInteraction() {
        Interaction interaction = assembler.fromDTO(INTERACTION_DTO);

        assertEquals(INTERACTION, interaction);
    }

    private void assertInteractionDTOEquals(InteractionDTO expected, InteractionDTO actual) {
        assertEquals(expected.dinFromWhichInteractionsAreChecked, actual.dinFromWhichInteractionsAreChecked);
        assertEquals(expected.interactingDins, actual.interactingDins);
    }
}
