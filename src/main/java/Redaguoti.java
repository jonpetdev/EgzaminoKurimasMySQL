import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Scanner;

public class Redaguoti {

    public SessionFactory factory;

    public void redaguoti() {
        Scanner sc = new Scanner(System.in);
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        Session session = factory.openSession();


        System.out.println("Pasirinkite kuri egzamina norite redaguoti, irasydami jo ID:");
        System.out.println("-".repeat(30));
        org.hibernate.query.Query qryE = session.createQuery("From Egzaminai");
        List<Egzaminai> egzaminais = qryE.list();
        for (Egzaminai e : egzaminais) {
            System.out.println("ID: " + e.getId() + " | Pavadinimas: " + e.getPavadinimas());
        }
        System.out.println("-".repeat(30));
        int egzaminoId = sc.nextInt();

        org.hibernate.query.Query qry = session.createQuery("FROM Klausimai Where egzaminai_id=" + egzaminoId);
        List<Klausimai> klausimaiList = qry.list();

        int sk = 1;
        while (sk != 0) {
            System.out.println("-".repeat(30));
            for (Klausimai k : klausimaiList) {
                System.out.println("klausimo ID: " + k.getId() + " | Klausimas: " + k.getKlausimas());
            }
            System.out.println("-".repeat(30));
            System.out.println("Pasirinkite klausimo ID kuri norite redaguoti: arba irasykite \"0\" baigti darba");

            sk = sc.nextInt();

            for (Klausimai k : klausimaiList) {
                if (sk == k.getId()) {
                    System.out.println(k.getKlausimas());
                    Transaction tx = session.beginTransaction();
                    sc.nextLine();
                    String klKoreg = sc.nextLine();
                    k.setKlausimas(klKoreg);
                    tx.commit();
                    int skA = 1;
                    while (skA != 0) {
                        for (Atsakymai a : k.getAtsakymai()) {
                            System.out.println("ID:" + a.getId() + " | " + a.getAtsakymas() + " | Ar teisingas: " + a.getTeisingas_neteisingas());
                        }
                        System.out.println("Pasirinkite atsakymo ID kuri norite koreaguoti:");
                        skA = sc.nextInt();
                        for (Atsakymai a : k.getAtsakymai()) {
                            if (a.getId() == skA) {
                                System.out.println(a.getAtsakymas());
                                Transaction tx2 = session.beginTransaction();
                                sc.nextLine();
                                String aKoreg = sc.nextLine();
                                a.setAtsakymas(aKoreg);
                                tx2.commit();
                            }
                        }
                    }

                }
            }


        }

        session.close();
        factory.close();
    }
}
