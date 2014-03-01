package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.EntityDTO;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerProvider;

public abstract class HibernateRepository<T extends EntityDTO> {

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

    protected void merge(Collection<T> elements) {
        beginTransaction();

        for (T element : elements) {
            putKey(element);
            getEntityManager().merge(element);
        }

        commitTransaction();
    }

    protected void merge(T element) {
        beginTransaction();

        putKey(element);
        getEntityManager().merge(element);

        commitTransaction();
    }

    protected TypedQuery<T> createQuery(String query) {
        return getEntityManager().createQuery(query, classType);
    }

    private void beginTransaction() {
        getEntityManager().getTransaction().begin();
    }

    private void commitTransaction() {
        getEntityManager().getTransaction().commit();
    }

    private EntityManager getEntityManager() {
        return entityManagerProvider.getEntityManager();
    }

    private void putKey(T element) {
        element.id = hashKeys(getKeys(element));
    }

    protected final int hashKeys(Object[] keys) {
        return Objects.hash(keys);
    }

    protected abstract Object[] getKeys(T element);

}