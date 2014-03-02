package ca.ulaval.ift6002.m2.domain.drug;

import java.util.Collection;

public interface DrugRepository {

    Drug get(Din din);

    Drug get(String name);

    Collection<Drug> findByBrandNameOrDescriptor(String keyword);

    void store(Collection<Drug> drugs);

    void store(Drug drug);
}
