package nachren;

import nachren.models.Book;
import nachren.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main( String[] args ) {
        Configuration config = new Configuration().addAnnotatedClass(Person.class).addAnnotatedClass(Book.class);

        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            Person person = session.get(Person.class, 2);
            System.out.println(person);
            session.getTransaction().commit();
        }finally{
            //session.close();
            sessionFactory.close();
        }
    }
}
