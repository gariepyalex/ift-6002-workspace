package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.repositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.drug.DrugFactory;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.entities.DrugHibernate;
import ca.ulaval.ift6002.m2.locator.FactoryLocator;

public class DrugHibernateRepository implements DrugRepository {

    private final HibernateRepository<DrugHibernate> hibernateRepository;
    private final DrugFactory drugFactory;
    private final String tableName = "DrugHibernate";

    public DrugHibernateRepository() {
        hibernateRepository = new HibernateRepository<>(DrugHibernate.class);
        this.drugFactory = FactoryLocator.getDrugFactory();
    }

    @Override
    public Drug get(Din din) {
        return hibernateRepository.getQueryBuilder().query("FROM " + tableName + " WHERE din = :din")
                .parameter("din", din.getValue()).get();
    }

    @Override
    public Drug get(String name) {
        return drugFactory.create(name);
    }

    @Override
    public Collection<Drug> findBy(String keyword) {
        String query = "FROM " + tableName
                + " WHERE LOWER(brandName) LIKE LOWER(:keyword) OR LOWER(descriptor) LIKE LOWER(:keyword)";

        String keywordWildCard = '%' + keyword.replace(" ", "%") + '%';
        List<DrugHibernate> drugs = hibernateRepository.getQueryBuilder().query(query)
                .parameter("keyword", keywordWildCard).list();

        return new ArrayList<Drug>(drugs);
    }

    @Override
    public void store(Collection<Drug> drugs) {
        for (Drug drug : drugs) {
            DrugHibernate drugHibernate = (DrugHibernate) drug;
            hibernateRepository.storeElement(drugHibernate);
        }
    }

    protected DrugHibernateRepository(HibernateRepository<DrugHibernate> hibernateRepository, DrugFactory drugFactory) {
        this.hibernateRepository = hibernateRepository;
        this.drugFactory = drugFactory;
    }
}
