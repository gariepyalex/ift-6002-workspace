package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.TypedQuery;

import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers.DrugDTOAssembler;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.DrugDTO;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerProvider;

public class DrugHibernateRepository extends HibernateRepository<DrugDTO> implements DrugRepository {

    private final DrugDTOAssembler drugDTOAssembler;

    public DrugHibernateRepository(DrugDTOAssembler drugAssembler) {
        super(DrugDTO.class);
        this.drugDTOAssembler = drugAssembler;
    }

    public DrugHibernateRepository(EntityManagerProvider entityManagerProvider, DrugDTOAssembler drugAssembler) {
        super(entityManagerProvider, DrugDTO.class);
        this.drugDTOAssembler = drugAssembler;
    }

    @Override
    public Drug get(Din din) {

        String query = "FROM DrugDTO WHERE din = :din";
        TypedQuery<DrugDTO> typedQuery = createQuery(query);

        typedQuery.setParameter("din", din.getValue());

        DrugDTO dto;
        try {
            dto = typedQuery.getSingleResult();
        } catch (Exception e) {
            throw new NoSuchElementException("There is no drug with din: " + din.getValue());
        }

        return drugDTOAssembler.fromDTO(dto);
    }

    @Override
    public Drug get(String name) {
        String query = "FROM DrugDTO WHERE brandName = :brandName";
        TypedQuery<DrugDTO> typedQuery = createQuery(query);

        typedQuery.setParameter("brandName", name);

        DrugDTO dto;
        try {
            dto = typedQuery.getSingleResult();
        } catch (Exception e) {
            throw new NoSuchElementException("There is no drug with name: " + name);
        }

        return drugDTOAssembler.fromDTO(dto);
    }

    @Override
    public Collection<Drug> findByBrandNameOrDescriptor(String keyword) {
        String query = "FROM DrugDTO WHERE LOWER(brandName) LIKE :keyword OR LOWER(descriptor) LIKE :keyword";
        TypedQuery<DrugDTO> typedQuery = createQuery(query);

        typedQuery.setParameter("keyword", '%' + keyword.toLowerCase() + '%');

        List<DrugDTO> dtos = typedQuery.getResultList();

        return drugDTOAssembler.fromDTOs(dtos);
    }

    @Override
    public void store(Collection<Drug> drugs) {
        Collection<DrugDTO> dtos = drugDTOAssembler.toDTOs(drugs);
        persist(dtos);
    }
}
