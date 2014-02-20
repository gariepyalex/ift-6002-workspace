package ca.ulaval.ift6002.m2.infrastructure.persistence.provider;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryProvider {

    private static EntityManagerFactory instance;

    public static EntityManagerFactory getFactory() {
        if (instance == null) {
            instance = Persistence.createEntityManagerFactory("hibernate-example");
        }
        return instance;
    }

}
