package ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers;

import ca.ulaval.ift6002.m2.domain.surgeon.Surgeon;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.SurgeonDTO;

public class SurgeonDTOAssembler extends DTOAssembler<Surgeon, SurgeonDTO> {

    @Override
    public SurgeonDTO toDTO(Surgeon surgeon) {
        return new SurgeonDTO(surgeon.license);
    }

    @Override
    public Surgeon fromDTO(SurgeonDTO dto) {
        return new Surgeon(dto.surgeon);
    }

}
