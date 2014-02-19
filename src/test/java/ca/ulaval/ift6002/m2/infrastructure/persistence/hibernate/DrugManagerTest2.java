package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class DrugManagerTest2 {

    private static final String A_DESCRIPTOR = "banane descriptor";
    private static final String A_NAME = "banane name";
    private static final int A_DIN = 111;

    private Session session;
    private Transaction transaction;

    public DrugManagerTest2() {
        beginTransaction();

        DrugPersistentClass firstDrug = new DrugPersistentClass(A_DIN, A_NAME, A_DESCRIPTOR);
        int id = (Integer) session.save(firstDrug);

        transaction.commit();
        session.close();

        beginTransaction();

        DrugPersistentClass drug = getDrugInBD(id);
        session.close();
        System.out.println(drug.getName() + " with DIN: " + drug.getDin());

    }

    private void beginTransaction() {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }

    private DrugPersistentClass getDrugInBD(int i) {
        return (DrugPersistentClass) session.load(DrugPersistentClass.class, i);
    }

    public static void main(String[] args) {
        new DrugManagerTest2();
    }
}
