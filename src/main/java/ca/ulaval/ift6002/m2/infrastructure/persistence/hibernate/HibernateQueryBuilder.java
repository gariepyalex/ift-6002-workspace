package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import ca.ulaval.ift6002.m2.infrastructure.persistence.QueryBuilder;

public class HibernateQueryBuilder<E> extends QueryBuilder<E> {

    private final EntityManager entityManager;
    private final Class<E> classType;
    private String query = null;
    private Map<String, Object> parameters = new HashMap<>();

    public HibernateQueryBuilder(EntityManager entityManager, Class<E> classType) {
        this.entityManager = entityManager;
        this.classType = classType;
    }

    @Override
    public QueryBuilder<E> query(String query) {
        this.query = query;
        return this;
    }

    @Override
    public QueryBuilder<E> parameter(String name, Object value) {
        parameters.put(name, value);
        return this;
    }

    @Override
    public int perform() {
        TypedQuery<E> typedQuery = entityManager.createQuery(query, classType);
        assignParametersTo(typedQuery);

        return typedQuery.executeUpdate();
    }

    @Override
    public E get() {
        try {
            TypedQuery<E> typedQuery = entityManager.createQuery(query, classType);
            assignParametersTo(typedQuery);

            return typedQuery.getSingleResult();
        } catch (NoResultException e) {
            throw new NoSuchElementException("There are no element found.");
        }
    }

    @Override
    public List<E> list() {
        TypedQuery<E> typedQuery = entityManager.createQuery(query, classType);
        assignParametersTo(typedQuery);

        return typedQuery.getResultList();
    }

    private void assignParametersTo(TypedQuery<E> typedQuery) {
        for (Entry<String, Object> parameter : parameters.entrySet()) {
            typedQuery.setParameter(parameter.getKey(), parameter.getValue());
        }
    }

}
