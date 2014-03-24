package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.drug.DrugFactory;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.factory.FactoryLocator;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.entities.DrugHibernate;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerProvider;

public class DrugHibernateRepository extends HibernateRepository<DrugHibernate> implements DrugRepository {

    private final DrugFactory drugFactory;

    public DrugHibernateRepository() {
        super(DrugHibernate.class);
        this.drugFactory = FactoryLocator.getDrugFactory();
    }

    @Override
    public Drug get(Din din) {
        return getQueryBuilder().query("FROM tbl_drug WHERE din = :din").parameter("din", din.getValue()).get();
    }

    @Override
    public Drug get(String name) {
        return drugFactory.create(name);
    }

    @Override
    public Collection<Drug> findBy(String keyword) {
        String query = "FROM tbl_drug WHERE LOWER(brandName) LIKE LOWER(:keyword) OR LOWER(descriptor) LIKE LOWER(:keyword)";

        // TODO do wildcard with spaces
        List<DrugHibernate> drugs = getQueryBuilder().query(query).parameter("keyword", '%' + keyword + '%').list();

        return new ArrayList<Drug>(drugs);
    }

    @Override
    public void store(Collection<Drug> drugs) {
        for (Drug drug : drugs) {
            DrugHibernate drugHibernate = (DrugHibernate) drug;
            storeElement(drugHibernate);
        }
    }

    protected DrugHibernateRepository(EntityManagerProvider entityManagerProvider, DrugFactory drugFactory) {
        super(entityManagerProvider, DrugHibernate.class);
        this.drugFactory = drugFactory;
    }
}
