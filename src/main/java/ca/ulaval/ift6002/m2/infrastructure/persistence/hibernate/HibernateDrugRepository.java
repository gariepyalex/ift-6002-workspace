package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate;

import java.util.Collection;

import javax.persistence.EntityManager;

import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.provider.EntityManagerProvider;

public class HibernateDrugRepository implements DrugRepository {

    private final EntityManager entityManager;

    public HibernateDrugRepository() {
        entityManager = new EntityManagerProvider().getEntityManager();
    }

    public HibernateDrugRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Drug get(Din din) {
        return null;
    }

    @Override
    public Drug get(String name) {
        return null;
    }

    @Override
    public Collection<Drug> findByBrandNameOrDescriptor(String keyword) {
        // return entityManager.find(Drug.class, keyword);
        return null;
    }

}
