//package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import ca.ulaval.ift6002.m2.domain.drug.Din;
//import ca.ulaval.ift6002.m2.domain.drug.Drug;
//
//public class DrugManagerTest {
//
//    private static final String PERSISTENCE_UNIT_NAME = "myApplication";
//
//    private static EntityManagerFactory emFactory;
//    private EntityManager em;
//
//    @BeforeClass
//    public static void setUpClass() {
//        // log.debug("creating entityManagerFactory");
//        emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
//    }
//
//    @Before
//    public void setUp() {
//        // log.debug("creating session");
//        em = emFactory.createEntityManager();
//        em.getTransaction().begin();
//    }
//
//    @After
//    public void tearDown() {
//        if (em != null) {
//            if (em.getTransaction().isActive()) {
//                em.getTransaction().commit();
//            }
//            em.close();
//        }
//    }
//
//    @AfterClass
//    public static void tearDownClass() {
//        if (emFactory != null) {
//            emFactory.close();
//        }
//    }
//
//    @Test
//    public void createAndStoreDrug() {
//        try {
//            try {
//                em.getTransaction().begin();
//                Drug drug = new Drug(new Din("aDIN"), "", "");
//                em.persist(drug);
//                em.getTransaction().commit();
//            } finally {
//                if (em.getTransaction().isActive()) {
//                    em.getTransaction().rollback();
//                }
//            }
//        } finally {
//            em.close();
//        }
//
//    }
//
// }
