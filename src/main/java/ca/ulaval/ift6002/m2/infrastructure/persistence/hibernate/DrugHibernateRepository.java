package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate;

import java.util.Collection;
import java.util.NoSuchElementException;

import javax.persistence.EntityManager;

import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers.DrugDTOAssembler;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.DrugDTO;

public class DrugHibernateRepository implements DrugRepository {

    private final EntityManager entityManager;

    private final DrugDTOAssembler drugDTOAssembler;

    public DrugHibernateRepository(EntityManager entityManager, DrugDTOAssembler assembler) {
        this.entityManager = entityManager;
        this.drugDTOAssembler = assembler;
    }

    @Override
    public Drug get(Din din) {
        DrugDTO dto = entityManager.find(DrugDTO.class, din.getValue());
        if (dto == null) {
            throw new NoSuchElementException("There is no drug with din: " + din.getValue());
        }
        return drugDTOAssembler.fromDTO(dto);
    }

    @Override
    public Drug get(String name) {
        return new Drug(new Din(""), name, "");
    }

    @Override
    public Collection<Drug> findByBrandNameOrDescriptor(String keyword) {
        // return entityManager.find(Drug.class, keyword);
        return null;
    }

    @Override
    public void store(Drug drug) {
        DrugDTO drugDTO = drugDTOAssembler.toDTO(drug);
        entityManager.persist(drugDTO);
    }
}
