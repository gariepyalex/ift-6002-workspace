package ca.ulaval.ift6002.m2.application.rest.filters;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerFactoryProvider;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerProviderThreadSafe;

public class EntityManagerContextFilter implements Filter {

    private EntityManagerFactory entityManagerFactory;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        entityManagerFactory = EntityManagerFactoryProvider.getFactory();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        EntityManager entityManager = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            EntityManagerProviderThreadSafe.setEntityManager(entityManager);
            entityManager.getTransaction().begin();
            chain.doFilter(request, response);
        } finally {
            if (entityManager != null) {
                entityManager.getTransaction().commit();
                entityManager.close();
            }
            EntityManagerProviderThreadSafe.clearEntityManager();
        }

    }

    @Override
    public void destroy() {
        entityManagerFactory.close();
    }

}
