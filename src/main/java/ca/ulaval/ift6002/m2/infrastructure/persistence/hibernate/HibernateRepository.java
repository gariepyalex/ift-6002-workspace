package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate;

import java.util.NoSuchElementException;

import javax.persistence.EntityManager;

import ca.ulaval.ift6002.m2.infrastructure.persistence.QueryBuilder;
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
        T element = getEntityManager().find(classType, value);

        if (element == null) {
            throw new NoSuchElementException("There is no element with value: " + value);
        }

        return element;
    }

    protected void storeElement(T element) {
        if (!getEntityManager().contains(element)) {
            getEntityManager().persist(element);
        }
    }

    protected QueryBuilder<T> getQueryBuilder() {
        // TODO cant mock this... composition over inheritance?
        return new HibernateQueryBuilder<>(getEntityManager(), classType);
    }

    private EntityManager getEntityManager() {
        return entityManagerProvider.getEntityManager();
    }

}
