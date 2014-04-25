package ca.ulaval.ift6002.m2.infrastructure.persistence.provider;

import javax.persistence.EntityManager;

public class EntityManagerProviderGlobal implements EntityManagerProvider {

    private static EntityManager entityManagerInstance;

    @Override
    public EntityManager getEntityManager() {
        return entityManagerInstance;
    }

    public static void setEntityManager(EntityManager entityManager) {
        entityManagerInstance = entityManager;
    }

    public static void clearEntityManager() {
        entityManagerInstance = null;
    }
}
