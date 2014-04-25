package ca.ulaval.ift6002.m2.infrastructure.persistence.provider;

import javax.persistence.EntityManager;

public class EntityManagerProviderThreadSafe implements EntityManagerProvider {

    private static ThreadLocal<EntityManager> localEntityManager = new ThreadLocal<>();

    @Override
    public EntityManager getEntityManager() {
        return localEntityManager.get();
    }

    public static void setEntityManager(EntityManager entityManager) {
        localEntityManager.set(entityManager);
    }

    public static void clearEntityManager() {
        localEntityManager.set(null);
    }
}
