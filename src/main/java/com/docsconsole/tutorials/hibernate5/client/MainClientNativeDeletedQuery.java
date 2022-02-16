package com.docsconsole.tutorials.hibernate5.client;

import com.docsconsole.tutorials.hibernate5.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class MainClientNativeDeletedQuery {

    public static void main(String[] args) {

        System.out.println("Main method@MainClient");
        try {

            // Get Session
            Session session = HibernateUtil.getSessionFactory().openSession();
            System.out.println("Session is created");

            // start transaction
            Transaction tx = session.beginTransaction();

            // delete book without param
            Query query = session.createSQLQuery("delete from Book where book_id=43  ");
            Integer count = query.executeUpdate();
            System.out.println("Number of records deleted: " + count);

            // delete book with param
            query = session.createSQLQuery("delete from Book where book_id=?1");
            query.setParameter(1, 44);
            count = query.executeUpdate();
            System.out.println("Number of records deleted: " + count);

            tx.commit();
            session.close();
            System.out.println("Session is closed");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}