package ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6002.m2.domain.surgeon.Surgeon;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.SurgeonDTO;

public class SurgeonDTOAssemblerTest {

    private static final Integer SURGEON_LICENSE = 123;
    private static final SurgeonDTO SURGEON_DTO = new SurgeonDTO(SURGEON_LICENSE);
    private static final Surgeon SURGEON = new Surgeon(SURGEON_LICENSE);

    private SurgeonDTOAssembler SurgeonAssembler;

    @Before
    public void setUp() {
        SurgeonAssembler = new SurgeonDTOAssembler();
    }

    @Test
    public void givenDTOWhenAssemblingSurgeonShouldReturnCorrespondingSurgeon() {
        Surgeon SurgeonBuilt = SurgeonAssembler.fromDTO(SURGEON_DTO);

        assertEquals(SURGEON, SurgeonBuilt);
    }

    @Test
    public void givenSurgeonWhenAssemblingDTOShouldReturnCorrespondingDTO() {
        SurgeonDTO dtoBuilt = SurgeonAssembler.toDTO(SURGEON);

        assertSurgeonDTOEquals(SURGEON_DTO, dtoBuilt);
    }

    private void assertSurgeonDTOEquals(SurgeonDTO expected, SurgeonDTO actual) {
        assertEquals(expected.surgeon, actual.surgeon);
    }
}
