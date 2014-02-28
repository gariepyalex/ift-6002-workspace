package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate;

import java.util.Collection;
import java.util.NoSuchElementException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerProvider;

public abstract class HibernateRepository<T> {

    private final EntityManagerProvider entityManagerProvider;
    private final Class<T> classType;

    public HibernateRepository(Class<T> classType) {
        this(new EntityManagerProvider(), classType);
    }

    public HibernateRepository(EntityManagerProvider entityManagerProvider, Class<T> classType) {
        this.entityManagerProvider = entityManagerProvider;
        this.classType = classType;
    }

    protected T find(Object value) {
        T element = getEntityManagerProvider().find(classType, value);

        if (element == null) {
            throw new NoSuchElementException("There is no element with value: " + value);
        }

        return element;
    }

    protected void merge(Collection<T> elements) {
        beginTransaction();

        for (T element : elements) {
            getEntityManagerProvider().merge(element);
        }

        commitTransaction();
    }

    protected void merge(T element) {
        beginTransaction();

        getEntityManagerProvider().merge(element);

        commitTransaction();
    }

    protected TypedQuery<T> createQuery(String query) {
        return getEntityManagerProvider().createQuery(query, classType);
    }

    private void beginTransaction() {
        getEntityManagerProvider().getTransaction().begin();
    }

    private void commitTransaction() {
        getEntityManagerProvider().getTransaction().commit();
    }

    private EntityManager getEntityManagerProvider() {
        return entityManagerProvider.getEntityManager();
    }

}
