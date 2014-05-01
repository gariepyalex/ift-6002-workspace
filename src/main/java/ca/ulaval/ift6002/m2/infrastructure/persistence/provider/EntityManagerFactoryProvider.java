package ca.ulaval.ift6002.m2.infrastructure.persistence.provider;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryProvider {

    private static final String PERSISTENCE_UNIT = "myApplication";
    private static EntityManagerFactory instance;

    public static EntityManagerFactory getFactory() {
        if (instance == null) {
            instance = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        }
        return instance;
    }

    public static void closeFactory() {
        instance.close();
        instance = null;
    }

}
