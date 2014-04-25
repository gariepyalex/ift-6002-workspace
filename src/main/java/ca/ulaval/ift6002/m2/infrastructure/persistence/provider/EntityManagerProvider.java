package ca.ulaval.ift6002.m2.infrastructure.persistence.provider;

import javax.persistence.EntityManager;

public interface EntityManagerProvider {

    EntityManager getEntityManager();
}
