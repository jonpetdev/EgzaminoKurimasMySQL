import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EgzaminoSprendimas {
    public SessionFactory factory;

    public void sprendimas() {

        Scanner sc = new Scanner(System.in);
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        Session session = factory.openSession();

        List<Sprendimas> sprendimasListas = new ArrayList<>();

        System.out.println("Iveskite Studento varda");
        String studentoVardas = sc.nextLine();
        org.hibernate.query.Query qryVardas = session.createQuery("FROM Studentas");
        List<Studentas> studentasList = qryVardas.list();
        Studentas studentas = new Studentas();
        boolean arStudentas = false;

        for (Studentas s : studentasList) {
            if (s.getVardas().equals(studentoVardas)) {
                System.out.println(s.getVardas());
                arStudentas = true;
                studentas = s;
            }
        }

        if (arStudentas == true) {


            System.out.println("-".repeat(30));
            System.out.println("Pasirinkite egzamina kuri norite laikyti irasydami jo ID:");
            System.out.println("-".repeat(30));

            org.hibernate.query.Query qry = session.createQuery("From Egzaminai");
            List<Egzaminai> egzam = qry.list();
            for (Egzaminai e : egzam) {
                System.out.println("id: " + e.getId() + " Pavadinimas: " + e.getPavadinimas());
            }
            System.out.println("-".repeat(30));
            int id = sc.nextInt();
            org.hibernate.query.Query qry2 = session.createQuery("From Klausimai WHERE egzaminai_id=" + id + "");
            List<Klausimai> kl = qry2.list();


            int fix = 0;
            for (Klausimai k : kl) {
                Sprendimas sprendimas = new Sprendimas();
                Atsakymai atsakymai1;
                Atsakymai atsakymai2;
                Atsakymai atsakymai3;

                atsakymai1 = k.getAtsakymai().get(0);
                atsakymai2 = k.getAtsakymai().get(1);
                atsakymai3 = k.getAtsakymai().get(2);
                System.out.println(k.getKlausimas());
                System.out.println("   " + k.getAtsakymai().get(0).getAtsakymas());
                System.out.println("   " + k.getAtsakymai().get(1).getAtsakymas());
                System.out.println("   " + k.getAtsakymai().get(2).getAtsakymas());

                if (fix == 0) {
                    sc.nextLine();
                }
                String atsakymoPasirinkimas = sc.nextLine();
                sprendimas.setStudentas(studentas);
                switch (atsakymoPasirinkimas) {
                    case "A":
                        sprendimas.setAtsakymai(atsakymai1);
                        sprendimas.setPasirinktas_atsakymas(atsakymoPasirinkimas);
                        break;
                    case "B":
                        sprendimas.setAtsakymai(atsakymai2);
                        sprendimas.setPasirinktas_atsakymas(atsakymoPasirinkimas);
                        break;
                    case "C":
                        sprendimas.setAtsakymai(atsakymai3);
                        sprendimas.setPasirinktas_atsakymas(atsakymoPasirinkimas);
                        break;
                    default:
                        System.out.println("nera tokio pasitinkimo");

                }

                fix = 1;

                sprendimasListas.add(sprendimas);
            }


        }


        for (Sprendimas s : sprendimasListas) {
            Transaction tx = session.beginTransaction();
            session.save(s);
            session.flush();
            tx.commit();


        }


        session.close();

        factory.close();


    }
}
