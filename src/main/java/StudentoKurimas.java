import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class StudentoKurimas {

    public SessionFactory factory;

    public void pridetiStudenta() {

        Scanner sc = new Scanner(System.in);
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        Session session = factory.openSession();

        Transaction tx = session.beginTransaction();

        Studentas studentas = new Studentas();
        System.out.println("-".repeat(30));
        System.out.println("Iveskite studento varda");
        System.out.println("-".repeat(30));
        String vardas = sc.nextLine();
        studentas.setVardas(vardas);


        session.save(studentas);
        tx.commit();

        session.close();
        factory.close();

    }
}
