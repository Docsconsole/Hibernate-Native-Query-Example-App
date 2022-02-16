package com.docsconsole.tutorials.hibernate5.client;

import com.docsconsole.tutorials.hibernate5.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class MainClientNativeUpdateQuery {

    public static void main(String[] args) {

        System.out.println("Main method@MainClient");
        try {

            // Get Session
            Session session = HibernateUtil.getSessionFactory().openSession();
            System.out.println("Session is created");

            // start transaction
            Transaction tx = session.beginTransaction();

            // update the book
            Query query = session.createSQLQuery("update Book b set b.book_name = 'testBook' where b.book_id=32");
            Integer count = query.executeUpdate();
            System.out.println("Number of records updated: " + count);

            // update the book
            query = session.createSQLQuery("update Book b set b.book_name = 'testBook' where b.book_id=?1");
            query.setParameter(1, 33);
            count = query.executeUpdate();
            System.out.println("Number of records updated: " + count);

            tx.commit();
            session.close();
            System.out.println("Session is closed");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}