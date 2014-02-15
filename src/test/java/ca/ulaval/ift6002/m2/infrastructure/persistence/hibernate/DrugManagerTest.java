package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate;

import org.hibernate.Session;
import org.junit.Test;

import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;

public class DrugManagerTest {

    @Test
    public void createAndStoreEvent() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Drug drug = new Drug(new Din("aDIN"), "", "");
        session.save(drug);

        session.getTransaction().commit();
    }

}
