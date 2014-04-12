package ca.ulaval.ift6002.m2.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6002.m2.configuration.factory.HibernateFactoryConfiguration;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.repositories.DrugHibernateRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerFactoryProvider;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerProvider;
import ca.ulaval.ift6002.m2.integration.contexts.IntegrationDrugRepositoryFiller;

public class DrugHibernateRepositoryITest {
    private static final String EXISTING_BRANDNAME_SEARCH_PATTERN = "advil";
    private static final String EXISTING_DESCRIPTOR_SEARCH_PATTERN = "descriptor";
    private static final String SIMPLE_SEARCH_PATTERN = "adv";
    private static final String SEARCH_PATTERN_WILDCARD = "adv gel";
    private static final String PATTERN_WITH_MULTIPLE_WORDS = "adv liq ge";
    private static final String INVALID_KEYWORD = "123" + SEARCH_PATTERN_WILDCARD + "123";
    private static final String EXISTING_BRANDNAME_CAMELCASE_SEARCH_PATTERN = "AdViL";
    private static final String EXISTING_DESCRIPTOR_CAMELCASE_SEARCH_PATTERN = "DeScRiPtOr";

    private IntegrationDrugRepositoryFiller drugRepositoryFiller;

    private DrugRepository drugRepository;

    private EntityManager entityManager;

    @Before
    public void setUp() {
        setupFactoryLocator();
        setUpEntityManager();

        drugRepository = new DrugHibernateRepository();

        entityManager.getTransaction().begin();

        drugRepositoryFiller = new IntegrationDrugRepositoryFiller(drugRepository);
        drugRepositoryFiller.fill();
    }

    @After
    public void closeEntityManager() {

        entityManager.getTransaction().rollback();

        EntityManagerProvider.clearEntityManager();
        entityManager.close();
        EntityManagerFactoryProvider.closeFactory();
    }

    @Test
    public void findByWithExistingBrandNameShouldNotBeEmpty() {
        Collection<Drug> drugsFound = drugRepository.findBy(EXISTING_BRANDNAME_SEARCH_PATTERN);
        boolean result = drugsFound.isEmpty();
        assertFalse(result);
    }

    @Test
    public void findByWithExistingDescriptorShouldNotBeEmpty() {
        Collection<Drug> drugsFound = drugRepository.findBy(EXISTING_DESCRIPTOR_SEARCH_PATTERN);
        boolean result = drugsFound.isEmpty();
        assertFalse(result);
    }

    @Test
    public void findByWithUnexistingKeyWordShouldReturnEmptyCollectionOfDrug() {
        Collection<Drug> drugsFound = drugRepository.findBy(INVALID_KEYWORD);
        boolean result = drugsFound.isEmpty();
        assertTrue(result);
    }

    @Test
    public void findByWhenUsingValidPatternShouldReturnTwoDrugs() {
        Collection<Drug> drugsFound = drugRepository.findBy(SIMPLE_SEARCH_PATTERN);
        int result = drugsFound.size();
        assertEquals(2, result);
    }

    @Test
    public void findByWhenUsingPatternWithTwoWordsShouldReturnDrugs() {
        Collection<Drug> drugsFound = drugRepository.findBy(SEARCH_PATTERN_WILDCARD);
        boolean result = drugsFound.isEmpty();
        assertFalse(result);
    }

    @Test
    public void findByWhenUsingPatternWithMoreThanThreeWordsShouldReturnDrugs() {
        Collection<Drug> drugsFound = drugRepository.findBy(PATTERN_WITH_MULTIPLE_WORDS);
        boolean result = drugsFound.isEmpty();
        assertFalse(result);
    }

    @Test
    public void findByWithCamelCaseBrandNameKeywordShouldReturnDrugs() {
        Collection<Drug> drugsFound = drugRepository.findBy(EXISTING_BRANDNAME_CAMELCASE_SEARCH_PATTERN);
        boolean result = drugsFound.isEmpty();
        assertFalse(result);
    }

    @Test
    public void findByWithCamelCaseDescriptorKeywordShouldReturnDrugs() {
        Collection<Drug> drugsFound = drugRepository.findBy(EXISTING_DESCRIPTOR_CAMELCASE_SEARCH_PATTERN);
        boolean result = drugsFound.isEmpty();
        assertFalse(result);
    }

    private void setupFactoryLocator() {
        new HibernateFactoryConfiguration().configure();
    }

    private void setUpEntityManager() {
        EntityManagerFactory entityManagerFactory = EntityManagerFactoryProvider.getFactory();
        entityManager = entityManagerFactory.createEntityManager();
        EntityManagerProvider.setEntityManager(entityManager);

    }
}