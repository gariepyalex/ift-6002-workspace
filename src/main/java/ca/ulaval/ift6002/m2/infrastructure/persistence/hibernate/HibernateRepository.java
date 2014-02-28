package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate;

import java.util.Collection;
import java.util.NoSuchElementException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public abstract class HibernateRepository<T> {

    private final EntityManager entityManager;
    private final Class<T> classType;

    public HibernateRepository(EntityManager entityManager, Class<T> classType) {
        this.entityManager = entityManager;
        this.classType = classType;
    }

    protected T find(Object value) {
        T element = entityManager.find(classType, value);

        if (element == null) {
            throw new NoSuchElementException("There is no element with value: " + value);
        }

        return element;
    }

    protected void merge(Collection<T> elements) {
        entityManager.getTransaction().begin();

        for (T element : elements) {
            entityManager.merge(element);
        }

        entityManager.getTransaction().commit();
    }

    protected TypedQuery<T> createQuery(String query) {
        return entityManager.createQuery(query, classType);
    }
}
