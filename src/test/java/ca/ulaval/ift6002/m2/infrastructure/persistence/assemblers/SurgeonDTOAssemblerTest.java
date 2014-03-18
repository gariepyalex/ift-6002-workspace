package ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6002.m2.domain.operation.surgeon.Surgeon;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.SurgeonDTO;

public class SurgeonDTOAssemblerTest {

    private static final Integer SURGEON_LICENSE = 123;
    private static final SurgeonDTO SURGEON_DTO = new SurgeonDTO(SURGEON_LICENSE);
    private static final Surgeon SURGEON = new Surgeon(SURGEON_LICENSE);

    private SurgeonDTOAssembler surgeonAssembler;

    @Before
    public void setUp() {
        surgeonAssembler = new SurgeonDTOAssembler();
    }

    @Test
    public void givenDTOWhenAssemblingSurgeonShouldReturnCorrespondingSurgeon() {
        Surgeon surgeonBuilt = surgeonAssembler.fromDTO(SURGEON_DTO);

        assertEquals(SURGEON, surgeonBuilt);
    }

    @Test
    public void givenSurgeonWhenAssemblingDTOShouldReturnCorrespondingDTO() {
        SurgeonDTO dtoBuilt = surgeonAssembler.toDTO(SURGEON);

        assertSurgeonDTOEquals(SURGEON_DTO, dtoBuilt);
    }

    private void assertSurgeonDTOEquals(SurgeonDTO expected, SurgeonDTO actual) {
        assertEquals(expected.surgeon, actual.surgeon);
    }
}
