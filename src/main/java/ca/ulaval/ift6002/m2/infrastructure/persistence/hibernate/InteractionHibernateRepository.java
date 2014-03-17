package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate;

import java.util.Collection;
import java.util.List;

import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.patient.Interaction;
import ca.ulaval.ift6002.m2.domain.patient.InteractionRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers.InteractionDTOAssembler;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.InteractionDTO;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerProvider;

public class InteractionHibernateRepository extends HibernateRepository<InteractionDTO> implements
        InteractionRepository {

    private final InteractionDTOAssembler assembler;

    public InteractionHibernateRepository() {
        super(InteractionDTO.class);
        this.assembler = new InteractionDTOAssembler();
    }

    public InteractionHibernateRepository(EntityManagerProvider entityManagerProvider, InteractionDTOAssembler assembler) {
        super(entityManagerProvider, InteractionDTO.class);
        this.assembler = assembler;
    }

    @Override
    public Interaction get(Din dinFromWhichInteractionAreChecked) {
        InteractionDTO dto = find(dinFromWhichInteractionAreChecked.getValue());
        return assembler.fromDTO(dto);
    }

    @Override
    public void store(List<Interaction> interactions) {
        Collection<InteractionDTO> dtos = assembler.toDTOs(interactions);

        merge(dtos);
    }

}
