package com.docsconsole.tutorials.hibernate5.client;

import com.docsconsole.tutorials.hibernate5.entity.Author;
import com.docsconsole.tutorials.hibernate5.entity.Book;
import com.docsconsole.tutorials.hibernate5.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

import java.util.List;

public class MainClientNativeSelectQuery {

    public static void main(String[] args) {

        System.out.println("Main method@MainClient");
        try {

            // Get Session
            Session session = HibernateUtil.getSessionFactory().openSession();
            System.out.println("Session is created");

            // start transaction
            Transaction tx = session.beginTransaction();

            // Get All books
            Query query = session.createSQLQuery("select book_id, book_name, book_price from Book");
            List<Object[]> bookList = query.list();
            for (Object[] row : bookList) {
                Book book = new Book();
                book.setBookId(Long.parseLong(row[0].toString()));
                book.setBookName(row[1].toString());
                book.setBookPrice(Double.parseDouble(row[2].toString()));
                System.out.println(book);
            }

            // Get All books with setting up hibernate data types
            query = session.createSQLQuery("select book_id, book_name, book_price from Book")
                    .addScalar("book_id", new LongType())
                    .addScalar("book_name", new StringType())
                    .addScalar("book_price", new DoubleType());
            bookList = query.list();
            for (Object[] row : bookList) {
                Book book = new Book();
                book.setBookId(Long.parseLong(row[0].toString()));
                book.setBookName(row[1].toString());
                book.setBookPrice(Double.parseDouble(row[2].toString()));
                System.out.println(book);
            }

            // Get multiple tables like Book and Author
            query = session.createSQLQuery("select {b.*}, {a.*} from Book b join Author a ON b.author_id=a.author_id")
                    .addEntity("b", Book.class)
                    .addJoin("a", "b.author");
            bookList = query.list();
            for (Object[] row : bookList) {
                for (Object obj : row) {
                    System.out.print(obj + "::");
                }
                System.out.println("\n");
            }

            for (Object[] row : bookList) {
                Book e = (Book) row[0];
                System.out.println("Employee Info::" + e);
                Author a = (Author) row[1];
                System.out.println("Address Info::" + a);
            }


            query = session
                    .createSQLQuery("select book_id, book_name, book_price from Book where book_id = ?1");
            List<Object[]> bookData = query.setLong(1, 33L).list();
            for (Object[] row : bookData) {
                Book book = new Book();
                book.setBookId(Long.parseLong(row[0].toString()));
                book.setBookName(row[1].toString());
                book.setBookPrice(Double.parseDouble(row[2].toString()));
                System.out.println(book);
            }

            query = session
                    .createSQLQuery("select book_id, book_name, book_price from Book where book_id = :id");
            bookData = query.setLong("id", 35L).list();
            for (Object[] row : bookData) {
                Book book = new Book();
                book.setBookId(Long.parseLong(row[0].toString()));
                book.setBookName(row[1].toString());
                book.setBookPrice(Double.parseDouble(row[2].toString()));
                System.out.println(book);
            }
            tx.commit();
            session.close();
            System.out.println("Session is closed");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}