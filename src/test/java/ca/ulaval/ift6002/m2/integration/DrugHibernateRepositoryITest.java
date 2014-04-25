package ca.ulaval.ift6002.m2.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.ulaval.ift6002.m2.configuration.factory.HibernateFactoryConfiguration;
import ca.ulaval.ift6002.m2.contexts.IntegrationDrugRepositoryFiller;
import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.drug.DrugFactory;
import ca.ulaval.ift6002.m2.domain.drug.DrugRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.repositories.DrugHibernateRepository;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerFactoryProvider;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerProviderThreadSafe;
import ca.ulaval.ift6002.m2.locator.FactoryLocator;

public class DrugHibernateRepositoryITest {

    private static final String EXISTING_BRANDNAME_SEARCH_PATTERN = "advil";
    private static final String EXISTING_DESCRIPTOR_SEARCH_PATTERN = "descriptor";

    private static final String SIMPLE_SEARCH_PATTERN = "adv";
    private static final String WILDCARD_SEARCH_PATTERN = "adv gel";
    private static final String PATTERN_WITH_MULTIPLE_WORDS = "adv liq ge";

    private static final String INVALID_KEYWORD = "123" + WILDCARD_SEARCH_PATTERN + "123";

    private static final String EXISTING_BRANDNAME_CAMELCASE_SEARCH_PATTERN = "AdViL";
    private static final String EXISTING_DESCRIPTOR_CAMELCASE_SEARCH_PATTERN = "DeScRiPtOr";

    private static final String A_BRANDNAME = "A brand name";
    private static final String A_DESCRIPTOR = "A descripor";

    private static final Din AN_EXISTING_DIN = new Din("11111111");
    private static final Din UNEXISTING_DIN = new Din("-1");
    private static final Din A_NEW_DIN = new Din("StoringDin");

    private static EntityManager entityManager;

    private static DrugRepository drugRepository;

    private static DrugFactory drugFactory;

    @BeforeClass
    public static void oneTimeSetUp() {
        setupFactoryLocator();
        setUpEntityManager();

        drugFactory = FactoryLocator.getDrugFactory();
        drugRepository = new DrugHibernateRepository(new EntityManagerProviderThreadSafe());

        beginTransaction();

        new IntegrationDrugRepositoryFiller(drugRepository).fill();

        commitTransaction();
    }

    @AfterClass
    public static void closeEntityManager() {
        EntityManagerProviderThreadSafe.clearEntityManager();
        entityManager.close();
        EntityManagerFactoryProvider.closeFactory();
    }

    @Test
    public void givenValidDinWhenGettingDrugShouldReturnDrug() {
        Drug drug = drugRepository.get(AN_EXISTING_DIN);
        assertEquals(AN_EXISTING_DIN, drug.getDin());
    }

    @Test(expected = NoSuchElementException.class)
    public void givenUnexistingDinWhenGettingDrugShouldThrowException() {
        drugRepository.get(UNEXISTING_DIN);
    }

    @Test
    public void givenNewDrugWhenStoringShouldBeStored() {
        Drug newDrug = drugFactory.create(A_NEW_DIN, A_BRANDNAME, A_DESCRIPTOR);
        Collection<Drug> newDrugs = Arrays.asList(newDrug);

        beginTransaction();
        drugRepository.store(newDrugs);
        commitTransaction();

        Drug drugRetrieved = drugRepository.get(A_NEW_DIN);
        assertEquals(A_NEW_DIN, drugRetrieved.getDin());
    }

    @Test
    public void givenExistingBrandNameWhenFindByKeywordShouldNotBeEmpty() {
        Collection<Drug> drugsFound = drugRepository.findBy(EXISTING_BRANDNAME_SEARCH_PATTERN);
        boolean result = drugsFound.isEmpty();
        assertFalse(result);
    }

    @Test
    public void givenExistingDescriptorWhenFindByShouldNotBeEmpty() {
        Collection<Drug> drugsFound = drugRepository.findBy(EXISTING_DESCRIPTOR_SEARCH_PATTERN);
        boolean result = drugsFound.isEmpty();
        assertFalse(result);
    }

    @Test
    public void givenUnexistingKeyWordWhenFindByKeywordShouldReturnEmptyCollectionOfDrug() {
        Collection<Drug> drugsFound = drugRepository.findBy(INVALID_KEYWORD);
        boolean result = drugsFound.isEmpty();
        assertTrue(result);
    }

    @Test
    public void givenValidPatternWhenFindByKeywordShouldReturnTwoDrugs() {
        Collection<Drug> drugsFound = drugRepository.findBy(SIMPLE_SEARCH_PATTERN);
        int result = drugsFound.size();
        assertEquals(2, result);
    }

    @Test
    public void givenPatternWithTwoWordsWhenFindByKeywordShouldReturnDrugs() {
        Collection<Drug> drugsFound = drugRepository.findBy(WILDCARD_SEARCH_PATTERN);
        boolean result = drugsFound.isEmpty();
        assertFalse(result);
    }

    @Test
    public void givenPatternWithMoreThanThreeWordsWhenFindByKeywordShouldReturnDrugs() {
        Collection<Drug> drugsFound = drugRepository.findBy(PATTERN_WITH_MULTIPLE_WORDS);
        boolean result = drugsFound.isEmpty();
        assertFalse(result);
    }

    @Test
    public void givenCamelCaseBrandNameWhenFindByKeywordShouldReturnDrugs() {
        Collection<Drug> drugsFound = drugRepository.findBy(EXISTING_BRANDNAME_CAMELCASE_SEARCH_PATTERN);
        boolean result = drugsFound.isEmpty();
        assertFalse(result);
    }

    @Test
    public void givenCamelCaseDescriptorWhenFindByKeywordShouldReturnDrugs() {
        Collection<Drug> drugsFound = drugRepository.findBy(EXISTING_DESCRIPTOR_CAMELCASE_SEARCH_PATTERN);
        boolean result = drugsFound.isEmpty();
        assertFalse(result);
    }

    private static void setupFactoryLocator() {
        new HibernateFactoryConfiguration().configure();
    }

    private static void setUpEntityManager() {
        EntityManagerFactory entityManagerFactory = EntityManagerFactoryProvider.getFactory();
        entityManager = entityManagerFactory.createEntityManager();
        EntityManagerProviderThreadSafe.setEntityManager(entityManager);

    }

    private static void beginTransaction() {
        entityManager.getTransaction().begin();
    }

    private static void commitTransaction() {
        entityManager.getTransaction().commit();
    }
}
