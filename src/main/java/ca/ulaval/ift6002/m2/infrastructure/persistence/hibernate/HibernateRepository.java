package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate;

import java.util.Collection;
import java.util.NoSuchElementException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerProvider;

public abstract class HibernateRepository<T> {

    private final EntityManagerProvider entityManagerProvider;
    private final Class<T> classType;

    public HibernateRepository(EntityManagerProvider entityManagerProvider, Class<T> classType) {
        this.entityManagerProvider = entityManagerProvider;
        this.classType = classType;
    }

    private EntityManager getEntityManagerProvider() {
        return entityManagerProvider.getEntityManager();
    }

    protected T find(Object value) {
        T element = getEntityManagerProvider().find(classType, value);

        if (element == null) {
            throw new NoSuchElementException("There is no element with value: " + value);
        }

        return element;
    }

    protected void merge(Collection<T> elements) {
        getEntityManagerProvider().getTransaction().begin();

        for (T element : elements) {
            getEntityManagerProvider().merge(element);
        }

        getEntityManagerProvider().getTransaction().commit();
    }

    protected void merge(T element) {
        getEntityManagerProvider().getTransaction().begin();

        getEntityManagerProvider().merge(element);

        getEntityManagerProvider().getTransaction().commit();
    }

    protected TypedQuery<T> createQuery(String query) {
        return getEntityManagerProvider().createQuery(query, classType);
    }
}
